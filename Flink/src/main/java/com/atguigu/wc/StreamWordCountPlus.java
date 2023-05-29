package com.atguigu.wc;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class StreamWordCountPlus {
    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment ex = StreamExecutionEnvironment.getExecutionEnvironment();
        ex.setParallelism(1);

        //socker 中读取流数据
        DataStreamSource<String> dataSteam = ex.socketTextStream("locahost", 7777);

        SingleOutputStreamOperator<Tuple2<String, Integer>> resultStream= dataSteam.flatMap(new WordCount.MyFlatMapper()).
                keyBy(0).
                sum(1);

        resultStream.print();

        //start work
        ex.execute();
    }
}
