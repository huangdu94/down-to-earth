package com.iflytek.pattern.create.abstact_factory.example2;

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
            case "FOOD":
                return new FoodFactory();
            case "SIZE":
                return new SizeFactory();
            default:
                return null;
        }
    }

}
