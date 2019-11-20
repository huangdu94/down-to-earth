package com.iflytek.pattern.create.builder.item;

import com.iflytek.pattern.create.builder.item.pack.Packing;
import com.iflytek.pattern.create.builder.item.pack.impl.Wrapper;

/**
 * 汉堡抽象类
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:09
 */
public abstract class Burger implements Item {

    @Override
    public abstract String name();

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract double price();
}
