package com.iflytek.pattern.create.builder.example1.entity;

import com.iflytek.pattern.create.builder.example1.entity.frame.IFrame;
import com.iflytek.pattern.create.builder.example1.entity.seat.ISeat;
import com.iflytek.pattern.create.builder.example1.entity.tire.ITire;

/**
 * 自行车
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class Bike {
    private IFrame frame;
    private ISeat seat;
    private ITire tire;

    public IFrame getFrame() {
        return frame;
    }

    public void setFrame(IFrame frame) {
        this.frame = frame;
    }

    public ISeat getSeat() {
        return seat;
    }

    public void setSeat(ISeat seat) {
        this.seat = seat;
    }

    public ITire getTire() {
        return tire;
    }

    public void setTire(ITire tire) {
        this.tire = tire;
    }
}