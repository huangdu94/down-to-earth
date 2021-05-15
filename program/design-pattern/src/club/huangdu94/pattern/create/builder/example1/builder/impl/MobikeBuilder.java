package club.huangdu94.pattern.create.builder.example1.builder.impl;

import club.huangdu94.pattern.create.builder.example1.entity.frame.impl.AlloyFrame;
import club.huangdu94.pattern.create.builder.example1.builder.Builder;
import club.huangdu94.pattern.create.builder.example1.entity.Bike;
import club.huangdu94.pattern.create.builder.example1.entity.seat.impl.DermisSeat;
import club.huangdu94.pattern.create.builder.example1.entity.tire.impl.SolidTire;

/**
 * 摩拜单车 builder 类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class MobikeBuilder extends Builder {
    private final Bike mBike = new Bike();

    @Override
    public void buildFrame() {
        mBike.setFrame(new AlloyFrame());
    }

    @Override
    public void buildSeat() {
        mBike.setSeat(new DermisSeat());
    }

    @Override
    public void buildTire() {
        mBike.setTire(new SolidTire());
    }

    @Override
    public Bike createBike() {
        return mBike;
    }
}
