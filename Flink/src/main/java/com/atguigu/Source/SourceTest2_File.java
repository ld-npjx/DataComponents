package com.atguigu.Source;

import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class SourceTest2_File {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment ex = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> stringDataStreamSource = ex.readTextFile("C:\\JavaCode\\Flink\\src\\main\\resources\\Sensor.txt");

        stringDataStreamSource.print();

        ex.execute();
    }
}
