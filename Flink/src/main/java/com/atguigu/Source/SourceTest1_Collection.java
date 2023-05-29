package com.atguigu.Source;

import com.atguigu.apitest.beans.SensorReading;
import com.atguigu.wc.WordCount;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

public class SourceTest1_Collection {
    public static void main(String[] args) {

        StreamExecutionEnvironment ex = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<SensorReading> dataStreamSource = ex.fromCollection(Arrays.asList(
                new SensorReading("Sensor_1", 15353536456L, 35.6),
                new SensorReading("Sensor_2", 15353536458L, 34.4),
                new SensorReading("Sensor_2", 15353536468L, 34.4)
        ));

        DataStreamSource<Integer> integerDataStreamSource = ex.fromElements(2, 11, 23, 112, 323);


        dataStreamSource.print();
        integerDataStreamSource.print("int").setParallelism(1);
    }
}
