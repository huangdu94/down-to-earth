package com.iflytek.pattern.create.factory;

import com.iflytek.pattern.create.factory.food.IFood;

/**
 * 简单工厂模式
 * 此为简单工厂，实际中可以将工厂抽象出一个接口，每一个工厂实现类单独生产一种产品
 *
 * @author DuHuang 2019/10/31 10:31
 */
public class Main {
    public static void main(String[] args) {
        FoodFactory foodFactory = new FoodFactory();
        IFood food1 = foodFactory.getFood("bread");
        IFood food2 = foodFactory.getFood("milk");
        IFood food3 = foodFactory.getFood("ham");
        food1.eat();
        food2.eat();
        food3.eat();
    }
}
