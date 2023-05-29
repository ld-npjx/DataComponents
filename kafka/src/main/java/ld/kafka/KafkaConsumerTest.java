package ld.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;

public class KafkaConsumerTest {
    public static void main(String[] args) {
        //消费者配置
        Properties props = new Properties();
        props.setProperty("bootstrap.servers","hadoop200:9092");
        //消费者组（将使用消费者的若干个消费者组织在一起，共同消费Kafka中的topic数据
        //每一个消费者需要指定一个消费者组，组名一样则表明为同一个消费者组
        props.setProperty("group.id","test");
        props.setProperty("enable.auto.commit","true");
        props.setProperty("auto.commit.interval.ms","1000");
        props.setProperty("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.setProperty("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");

        //创建消费者
        KafkaConsumer<String,String> KafkaConsumer = new KafkaConsumer<>(props);

        //订阅生产者
        KafkaConsumer.subscribe(Arrays.asList("test"));

        while (true){
            ConsumerRecords<String, String> consumerRecords =  KafkaConsumer.poll(Duration.ofSeconds(5));

            for (ConsumerRecord<String, String> consumerRecord:consumerRecords){
                String topic =consumerRecord.topic();
                //offset 位置
                long offset = consumerRecord.offset();

            //key value
                String key = consumerRecord.key();
                String value = consumerRecord.value();

                System.out.println("topic:"+topic+"offset:"+offset+"key:"+key+"value:"+value);
            }
        }
    }
}
