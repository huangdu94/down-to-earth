package com.iflytek.pattern.create.abstact_factory.size.impl;

import com.iflytek.pattern.create.abstact_factory.size.ISize;

/**
 * 小
 *
 * @author DuHuang 2019/10/31 10:44
 */
public class Small implements ISize {
    @Override
    public void show() {
        System.out.println("小.");
    }
}