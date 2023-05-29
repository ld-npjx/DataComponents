package com.atguigu.TransformTest;

//keyBy
//DataStream--> KeyedStream

import com.atguigu.apitest.beans.SensorReading;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class transform_keyBy {
    //key need Tuple
    public static void main(String[] args) {
        StreamExecutionEnvironment ev = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStream<String> inputStream = ev.readTextFile("C:\\JavaCode\\Flink\\src\\main\\resources\\Sensor.txt");

        DataStream<SensorReading> dataStream=inputStream.map(new MyMap());
        KeyedStream<SensorReading, Tuple> keyedStream = dataStream.keyBy("id");

        SingleOutputStreamOperator<SensorReading> max = keyedStream.max(0);
        SingleOutputStreamOperator<SensorReading> temperature = keyedStream.max("temperature");

        max.print("max:");
        temperature.print("temperature:");
    }

    //implement MayFunctions
    public static class MyMap implements MapFunction<String,SensorReading>{

        @Override
        public SensorReading map(String s) throws Exception {
            String[] split = s.split(",");
            return new SensorReading(split[0],new Long(split[1]),new Double(split[2]));
        }
    }
}
