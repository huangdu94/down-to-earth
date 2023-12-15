package com.gupaoedu.kafka;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.IntegerDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/8/17-20:22
 */
public class GpKafkaConsumer extends Thread {
    private final KafkaConsumer<Integer, String> consumer;

    public GpKafkaConsumer(String topic, String groupId) {
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.CLIENT_ID_CONFIG, "gp-consumer");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, "30000");
        // 自动提交(批量确认)
        properties.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "1000");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, IntegerDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 一个新的group的消费者去消费一个topic 这个属性. 它能够消费昨天发布的数据
        properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        this.consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Collections.singleton(topic));
    }

    @Override
    public void run() {
        while (true) {
            ConsumerRecords<Integer, String> consumerRecords = consumer.poll(Duration.ofSeconds(1));
            consumerRecords.forEach(record -> {
                //null->gp kafka practice msg:0->63
                System.out.println(record.key() + "->" + record.value() + "->" + record.offset());
            });
        }
    }

    public static void main3(String[] args) {
        System.out.println(Arrays.toString(args));
        new GpKafkaConsumer(args[0], args[1]).start();
    }

    public static void main(String[] args) {
        System.out.println(Math.abs("huangdu".hashCode()) % 50);
    }
}
