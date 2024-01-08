package org.example;

import java.util.List;

import org.springframework.context.ApplicationListener;
import org.springframework.core.io.support.SpringFactoriesLoader;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class SpringBootSpiDemo {
    public static void main(String[] args) {
        // META-INF/spring.factories
        List<ApplicationListener> list = SpringFactoriesLoader.loadFactories(ApplicationListener.class, ClassLoader.getSystemClassLoader());
        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}
