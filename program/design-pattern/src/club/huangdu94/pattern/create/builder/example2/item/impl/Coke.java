package club.huangdu94.pattern.create.builder.example2.item.impl;

import club.huangdu94.pattern.create.builder.example2.item.ColdDrink;

/**
 * @author duhuang@iflytek
 * @date 2019/10/31 15:14
 */
public class Coke extends ColdDrink {
    @Override
    public String name() {
        return "coke";
    }

    @Override
    public double price() {
        return 30.0;
    }
}
