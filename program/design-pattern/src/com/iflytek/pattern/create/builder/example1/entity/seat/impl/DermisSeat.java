package com.iflytek.pattern.create.builder.example1.entity.seat.impl;

import com.iflytek.pattern.create.builder.example1.entity.seat.ISeat;

/**
 * 真皮座位
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class DermisSeat implements ISeat {
    @Override
    public void seat() {
        System.out.println("真皮座位.");
    }
}
