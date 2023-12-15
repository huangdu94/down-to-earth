package com.gupaoedu.kafka;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.IntegerSerializer;
import org.apache.kafka.common.serialization.StringSerializer;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * create-date: 2019/8/17-20:22
 * 常见命令
 * 消费消息队列 __consumer_offsets
 * ./kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic __consumer_offsets --partition 26 --formatter "kafka.coordinator.group.GroupMetadataManager\$OffsetsMessageFormatter"
 * 查看每个group的消费情况
 * ./kafka-consumer-groups.sh --bootstrap-server localhost:9092 --all-groups --describe
 * 查看指定topic的详情
 * ./kafka-topics.sh --bootstrap-server localhost:9092 --topic test --describe
 * 查看kafka消息日志的内容
 * ./kafka-run-class.sh kafka.tools.DumpLogSegments --files /tmp/kafka-logs/test-0/00000000000000000000.log --print-data-log
 *
 * @author 风骚的Mic 老师
 */
public class GpKafkaProducer extends Thread {
    private final KafkaProducer<Integer, String> producer;
    /**
     * 主题
     */
    private final String topic;

    public GpKafkaProducer(String topic) {
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, "gp-producer");
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, "com.gupaoedu.kafka.MyPartition");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, IntegerSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        this.producer = new KafkaProducer<>(properties);
        this.topic = topic;
    }

    @Override
    public void run() {
        for (int num = 0; num < 20; num++) {
            try {
                String msg = "gp kafka practice msg:" + num;
                //get 会拿到发送的结果
                //同步 get() -> Future()
                //回调通知
                producer.send(new ProducerRecord<>(topic, null, msg), (metadata, exception) -> {
                    System.out.println(metadata.offset() + "->" + metadata.partition() + "->" + metadata.topic());
                });
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new GpKafkaProducer("test").start();
    }
}
