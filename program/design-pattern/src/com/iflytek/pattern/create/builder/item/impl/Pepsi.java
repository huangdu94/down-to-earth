package com.iflytek.pattern.create.builder.item.impl;

import com.iflytek.pattern.create.builder.item.ColdDrink;

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
