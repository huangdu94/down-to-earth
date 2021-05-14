package com.iflytek.pattern.create.abstact_factory;

import com.iflytek.pattern.create.abstact_factory.factory.AbstractFactory;
import com.iflytek.pattern.create.abstact_factory.factory.impl.DellFactory;
import com.iflytek.pattern.create.abstact_factory.factory.impl.LenovoFactory;
import com.iflytek.pattern.create.abstact_factory.factory.impl.MacFactory;

/**
 * 工厂创造器
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:56
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String factory) {
        if (factory == null) {
            return null;
        }
        switch (factory.toUpperCase()) {
            case "DELL":
                return new DellFactory();
            case "LENOVO":
                return new LenovoFactory();
            case "MAC":
                return new MacFactory();
            default:
                return null;
        }
    }

}
