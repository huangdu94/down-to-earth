package com.iflytek.pattern.create.abstact_factory.example1.entity.headset.impl;

import com.iflytek.pattern.create.abstact_factory.example1.entity.headset.IHeadset;

/**
 * 戴尔耳麦
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class DellHeadset implements IHeadset {
    @Override
    public void listen() {
        System.out.println("戴尔耳麦.");
    }
}
