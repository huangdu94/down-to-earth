package club.huangdu94.pattern.create.builder.example2.item.impl;

import club.huangdu94.pattern.create.builder.example2.item.Burger;

/**
 * 鸡肉汉堡
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:12
 */
public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "Chicken Burger";
    }

    @Override
    public double price() {
        return 50.0;
    }
}
