package com.iflytek.pattern.create.builder.item.impl;

import com.iflytek.pattern.create.builder.item.Burger;

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
