package com.iflytek.pattern.create.abstact_factory.food.impl;

import com.iflytek.pattern.create.abstact_factory.food.IFood;

/**
 * 牛奶
 *
 * @author DuHuang 2019/10/31 10:20
 */
public class Milk implements IFood {
    @Override
    public void eat() {
        System.out.println("牛奶.");
    }
}
