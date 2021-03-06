package club.huangdu94.pattern.create.abstact_factory.example1;

import club.huangdu94.pattern.create.abstact_factory.example1.factory.AbstractFactory;
import club.huangdu94.pattern.create.abstact_factory.example1.factory.impl.DellFactory;
import club.huangdu94.pattern.create.abstact_factory.example1.factory.impl.LenovoFactory;
import club.huangdu94.pattern.create.abstact_factory.example1.factory.impl.MacFactory;

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
