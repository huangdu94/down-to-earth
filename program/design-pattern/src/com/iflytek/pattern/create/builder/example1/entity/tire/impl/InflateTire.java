package com.iflytek.pattern.create.builder.example1.entity.tire.impl;

import com.iflytek.pattern.create.builder.example1.entity.tire.ITire;

/**
 * 充气轮胎
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class InflateTire implements ITire {
    @Override
    public void tire() {
        System.out.println("充气轮胎.");
    }
}
