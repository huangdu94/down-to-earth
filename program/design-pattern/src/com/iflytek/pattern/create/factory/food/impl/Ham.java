package com.iflytek.pattern.create.factory.food.impl;

import com.iflytek.pattern.create.factory.food.IFood;

/**
 * 火腿
 *
 * @author DuHuang 2019/10/31 10:23
 */
public class Ham implements IFood {
    @Override
    public void eat() {
        System.out.println("火腿.");
    }
}
