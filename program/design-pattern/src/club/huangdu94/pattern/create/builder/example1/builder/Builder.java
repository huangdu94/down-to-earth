package club.huangdu94.pattern.create.builder.example1.builder;

import club.huangdu94.pattern.create.builder.example1.entity.Bike;

/**
 * 抽象 builder 类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public abstract class Builder {
    public abstract void buildFrame();

    public abstract void buildSeat();

    public abstract void buildTire();

    public abstract Bike createBike();
}