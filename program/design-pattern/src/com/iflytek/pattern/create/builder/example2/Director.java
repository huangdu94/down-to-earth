package com.iflytek.pattern.create.builder.example2;

/**
 * 建工角色
 * 定义使用建造者角色中的方法来生成实例的方法
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:34
 */
public class Director {
    private IBuilder builder;

    public Director(IBuilder builder) {
        this.builder = builder;
    }

    public void constructVegMeal() {
        builder.orderVegBurger();
        builder.orderCoke();
    }

    public void constructMeatMeal() {
        builder.orderChickenBurger();
        builder.orderPepsi();
    }
}
