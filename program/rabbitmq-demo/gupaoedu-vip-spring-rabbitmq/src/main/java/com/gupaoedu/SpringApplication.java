package com.gupaoedu;

import java.util.concurrent.TimeUnit;

import com.gupaoedu.producer.MessageProducer;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class SpringApplication {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        MessageProducer mp = context.getBean("messageProducer", MessageProducer.class);
        for (int i = 0; i < 1000; i++) {
            mp.sendMessage("hello world " + i);
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
