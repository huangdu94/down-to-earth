package com.iflytek.pattern.create.builder.example1;

import com.iflytek.pattern.create.builder.example1.builder.Builder;
import com.iflytek.pattern.create.builder.example1.entity.Bike;

/**
 * 指挥者
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class Director {
    private final Builder mBuilder;

    public Director(Builder builder) {
        mBuilder = builder;
    }

    public Bike construct() {
        mBuilder.buildFrame();
        mBuilder.buildSeat();
        mBuilder.buildTire();
        return mBuilder.createBike();
    }
}
