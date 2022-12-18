package club.huangdu94.pattern.create.builder.example2.item.impl;

import club.huangdu94.pattern.create.builder.example2.item.ColdDrink;

/**
 * 百世可乐
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:14
 */
public class Pepsi extends ColdDrink {
    @Override
    public String name() {
        return "pepsi";
    }

    @Override
    public double price() {
        return 35.0;
    }
}
