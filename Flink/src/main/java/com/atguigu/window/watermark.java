package com.atguigu.window;

import com.atguigu.apitest.beans.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.timestamps.BoundedOutOfOrdernessTimestampExtractor;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.OutputTag;

public class watermark {
//        StreamExecutionEnvironment env=StreamExecutionEnvironment.getExecutionEnvironment();
//        env.setParallelism(1);
//        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//
//        DataStreamSource<String> inputStream = env.socketTextStream("localhost", 7777);
//
//        //转换成SensorReading类型
//        DataStream<SensorReading> dataStream=inputStream.map(line->{
//            String[] fields = line.split(",");
//            return new SensorReading(fields[0],new Long(fields[1],new Double(fields[2])));
//        });
        public static void main(String[] args) throws Exception {
            StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

            // Flink1.12.X 已经默认就是使用EventTime了，所以不需要这行代码
            //        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
            env.getConfig().setAutoWatermarkInterval(100);

            // socket文本流
            DataStream<String> inputStream = env.socketTextStream("localhost", 7777);

            // 转换成SensorReading类型，分配时间戳和watermark
            DataStream<SensorReading> dataStream = inputStream.map(line -> {
                        String[] fields = line.split(",");
                        return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
                    })
                    //
                    //                // 旧版 (新版官方推荐用assignTimestampsAndWatermarks(WatermarkStrategy) )
                    // 升序数据设置事件时间和watermark
                    //.assignTimestampsAndWatermarks(new AscendingTimestampExtractor<SensorReading>() {
                    //  @Override
                    //  public long extractAscendingTimestamp(SensorReading element) {
                    //    return element.getTimestamp() * 1000L;
                    //  }
                    //})

                    // 旧版 (新版官方推荐用assignTimestampsAndWatermarks(WatermarkStrategy) )
                    // 乱序数据设置时间戳和watermark

                    .assignTimestampsAndWatermarks(new BoundedOutOfOrdernessTimestampExtractor<SensorReading>(Time.seconds(2)) {
                        @Override
                        public long extractTimestamp(SensorReading element) {
                            return element.getTimestamp() * 1000L;
                        }
                    });

            OutputTag<SensorReading> outputTag = new OutputTag<SensorReading>("late") {
            };

            // 基于事件时间的开窗聚合，统计15秒内温度的最小值
            SingleOutputStreamOperator<SensorReading> minTempStream = dataStream.keyBy("id")
                    .timeWindow(Time.seconds(15))
                    .allowedLateness(Time.minutes(1))
                    .sideOutputLateData(outputTag)
                    .minBy("temperature");

            minTempStream.print("minTemp");

            minTempStream.getSideOutput(outputTag).print("late");

            env.execute();
        }
    }

