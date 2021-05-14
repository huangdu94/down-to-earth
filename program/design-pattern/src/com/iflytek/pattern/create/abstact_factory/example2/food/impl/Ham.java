package com.iflytek.pattern.create.abstact_factory.example2.food.impl;

import com.iflytek.pattern.create.abstact_factory.example2.food.IFood;

/**
 * 火腿
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:23
 */
public class Ham implements IFood {
    @Override
    public void eat() {
        System.out.println("火腿.");
    }
}
