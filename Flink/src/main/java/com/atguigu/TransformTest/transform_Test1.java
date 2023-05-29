package com.atguigu.TransformTest;

import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class transform_Test1 {
    public static void main(String[] args) {
        StreamExecutionEnvironment ev = StreamExecutionEnvironment.getExecutionEnvironment();
        ev.setParallelism(1);

        DataStreamSource<String> inputStream = ev.readTextFile("C:\\JavaCode\\Flink\\src\\main\\resources\\Sensor.txt");
        //map
        SingleOutputStreamOperator<Integer> mapStream = inputStream.map(new MapFunction<String, Integer>() {
            @Override
            public Integer map(String value) throws Exception {
                return value.length();
            }
        });
        //flatmap
        DataStream<String> flatMapStream = inputStream.flatMap(new FlatMapFunction<String, String>() {
            @Override
            public void flatMap(String value, Collector<String> collector) throws Exception {
                String[] split = value.split(",");
                for(String field:split)
                    collector.collect(field);
            }
        });

        //filter,筛选sensor_1开头的数据
        DataStream<String> filter = inputStream.filter(new FilterFunction<String>() {
            @Override
            public boolean filter(String value) throws Exception {
                return value.startsWith("Sensor_1");
            }
        });

        filter.print("filter");
        flatMapStream.print("flatMap");
        mapStream.print("map");
    }
}
