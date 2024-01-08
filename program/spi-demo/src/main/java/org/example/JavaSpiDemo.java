package org.example;

import java.sql.Driver;
import java.util.ServiceLoader;

import org.example.obj.ISay;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class JavaSpiDemo {
    public static void main(String[] args) {
        // META-INF/services/  + className
        ServiceLoader<ISay> sl = ServiceLoader.load(ISay.class);
        for (ISay iSay : sl) {
            System.out.println(iSay.getClass());
            System.out.println(iSay.say());
        }

        for (Driver driver : ServiceLoader.load(Driver.class)) {
            System.out.println(driver.getClass());
        }
    }
}
