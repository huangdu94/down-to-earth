package com.iflytek.pattern.create.abstact_factory;

/**
 * 工厂创造器
 *
 * @author DuHuang 2019/10/31 10:56
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
