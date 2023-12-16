package com.gupaoedu.example;

import java.util.List;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/8/28-21:05
 */
public class RocketMqProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {
        // 事务消息的时候会用到
        DefaultMQProducer producer = new DefaultMQProducer("gp_producer_group");
        // 它会从命名服务器上拿到broker的地址
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        int num = 0;
        while (num < 20) {
            num++;
            //Topic
            //tags -> 标签 （分类） -> (筛选)
            Message message = new Message("gp_test_topic", "TagA", ("Hello , RocketMQ:" + num).getBytes());
            //消息路由策略
            producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> list, Message message, Object o) {
                    return list.get(0);
                }
            }, "key-" + num);
        }
    }
}
