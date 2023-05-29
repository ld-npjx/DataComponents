package com.atguigu.wc;

import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;



public class StreamWordCount {
    public static void main(String[] args) throws Exception{
        StreamExecutionEnvironment ev = StreamExecutionEnvironment.getExecutionEnvironment();
        ev.setParallelism(8);


        String inputPath="C:\\JavaCode\\Flink\\src\\main\\resources\\test.txt";
        DataStream<String> dataSource = ev.readTextFile(inputPath);

        SingleOutputStreamOperator<Tuple2<String, Integer>> resultStream= dataSource.flatMap(new WordCount.MyFlatMapper()).
                keyBy(0).
                sum(1);

        resultStream.print();

        //start work
        ev.execute();


    }

}
