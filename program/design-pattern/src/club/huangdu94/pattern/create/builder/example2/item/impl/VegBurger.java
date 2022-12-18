package club.huangdu94.pattern.create.builder.example2.item.impl;

import club.huangdu94.pattern.create.builder.example2.item.Burger;

/**
 * 蔬菜汉堡
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:12
 */
public class VegBurger extends Burger {
    @Override
    public String name() {
        return "veg burger";
    }

    @Override
    public double price() {
        return 25.0;
    }
}
