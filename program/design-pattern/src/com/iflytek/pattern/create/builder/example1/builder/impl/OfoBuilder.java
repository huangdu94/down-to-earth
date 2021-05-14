package com.iflytek.pattern.create.builder.example1.builder.impl;

import com.iflytek.pattern.create.builder.example1.builder.Builder;
import com.iflytek.pattern.create.builder.example1.entity.Bike;
import com.iflytek.pattern.create.builder.example1.entity.frame.impl.CarbonFrame;
import com.iflytek.pattern.create.builder.example1.entity.seat.impl.RubberSeat;
import com.iflytek.pattern.create.builder.example1.entity.tire.impl.InflateTire;

/**
 * 小黄车 builder 类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class OfoBuilder extends Builder {
    private final Bike mBike = new Bike();

    @Override
    public void buildFrame() {
        mBike.setFrame(new CarbonFrame());
    }

    @Override
    public void buildSeat() {
        mBike.setSeat(new RubberSeat());
    }

    @Override
    public void buildTire() {
        mBike.setTire(new InflateTire());
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}
