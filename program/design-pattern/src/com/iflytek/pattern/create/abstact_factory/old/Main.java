package com.iflytek.pattern.create.abstact_factory.old;

import com.iflytek.pattern.create.abstact_factory.old.food.IFood;
import com.iflytek.pattern.create.abstact_factory.old.size.ISize;

/**
 * 抽象工厂模式
 * 工厂的工厂
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:59
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory foodFactory = FactoryProducer.getFactory("food");
        IFood food1 = foodFactory.getFood("bread");
        IFood food2 = foodFactory.getFood("milk");
        IFood food3 = foodFactory.getFood("ham");
        AbstractFactory sizeFactory = FactoryProducer.getFactory("size");
        ISize size1 = sizeFactory.getSize("small");
        ISize size2 = sizeFactory.getSize("medium");
        ISize size3 = sizeFactory.getSize("big");
        food1.eat();
        food2.eat();
        food3.eat();
        size1.show();
        size2.show();
        size3.show();
    }
}
