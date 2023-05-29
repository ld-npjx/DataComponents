//package com.atguigu.TableApi;
//
//import com.atguigu.apitest.beans.SensorReading;
//import org.apache.flink.streaming.api.datastream.DataStreamSource;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.table.api.Table;
//import org.apache.flink.table.api.java.StreamTableEnvironment;
//
//import java.util.stream.Stream;
//
//public class Table_test1 {
//    public static void main(String[] args) {
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//        DataStreamSource<String> StreamData = env.readTextFile("C:\\JavaCode\\Flink\\src\\main\\resources\\Sensor.txt");
//
//        StreamData.map(line -> {
//            String[] fields = line.split(",");
//            return new SensorReading(fields[0],new Long(fields[1]),new Double(fields[2]));
//        });
//
//        //StreamTableEnvironment.creat
//        StreamTableEnvironment TableEnv = StreamTableEnvironment.create(env);
//
//        Table table = TableEnv.fromDataStream(StreamData);
//
//        table.select("id,temperature").where("id='sensor_1'");
//
//
//
//
//    }
//}
