package com.ld;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * @author ld
 *
 */
public class HbaseConnection {
    public static Connection connection=null;
    static {
        Configuration conf=new Configuration();

        conf.set("hbase.zookeeper.quorum","hadoop200,hadoop201,hadoop202");

        try {
            connection=ConnectionFactory.createConnection(conf);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void closeConnection() throws IOException{
        if(connection!=null){
            connection.close();
        }
    }

    public static void main(String[] args) throws IOException {
        Configuration configuration = new Configuration();
        configuration.set("hbase.zookeeper.quorum","hadoop200,hadoop201,hadoop202");
        //创建连接
        Connection connection = ConnectionFactory.createConnection();

        System.out.println(connection);

        connection.close();
    }
}
