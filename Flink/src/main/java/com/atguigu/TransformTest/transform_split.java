package com.atguigu.TransformTest;

import com.atguigu.apitest.beans.SensorReading;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class transform_split {
    public static void main(String[] args) {
        StreamExecutionEnvironment ev = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> inputStream = ev.readTextFile("C:\\JavaCode\\Flink\\src\\main\\resources\\Sensor.txt");

        DataStream<SensorReading> dataStream = inputStream.map(line -> {
            String[] fields = line.split(",");
            return new SensorReading(fields[0], new Long(fields[1]), new Double(fields[2]));
        });

        //split
        //dataStream.split();
    }
}
