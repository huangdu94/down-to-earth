package com.iflytek.pattern.create.abstact_factory.size.impl;

import com.iflytek.pattern.create.abstact_factory.size.ISize;

/**
 * 大
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:46
 */
public class Big implements ISize {
    @Override
    public void show() {
        System.out.println("大.");
    }
}
