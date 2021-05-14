package com.iflytek.pattern.create.builder;

/**
 * 建造者模式
 * main方法即使用者，两种使用方式
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 14:45
 */
public class Main {
    public static void main(String[] args) {
        //1. 使用建造类直接生成订单
        Order order = new OrderBuilder()
                .orderChickenBurger()
                .orderCoke()
                .orderChickenBurger()
                .toOrder();
        order.showItems();
        //2. 使用建工类对象封装好的方法构造建造类 再生成订单
        IBuilder builder = new OrderBuilder();
        Director director = new Director(builder);
        director.constructMeatMeal();
        builder.toOrder().showItems();
    }
}
