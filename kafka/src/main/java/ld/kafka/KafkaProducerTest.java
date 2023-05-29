package ld.kafka;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/*
* kafka的生产者程序
* */
public class KafkaProducerTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //创建用于连接的kafka的properties配置
        Properties props = new Properties();
        props.put("bootstrap.servers","192.168.255.200:9092");
        props.put("acks","all");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        //创建一个生产者对象kafkaProducer
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(props);

        //发送1-100到指定的topic中
        for (int i=0;i<100;i++){
            ProducerRecord<String,String> test = new ProducerRecord<>("test", null, i + "");
            Future<RecordMetadata> future = kafkaProducer.send(test);
            future.get();
            System.out.println("第"+i+"消息");
        }

        kafkaProducer.close();
    }
}
