package club.huangdu94.pattern.create.builder.example1;

import club.huangdu94.pattern.create.builder.example1.builder.impl.MobikeBuilder;
import club.huangdu94.pattern.create.builder.example1.builder.impl.OfoBuilder;
import club.huangdu94.pattern.create.builder.example1.entity.Bike;
import club.huangdu94.pattern.create.builder.example1.builder.Builder;

/**
 * 建造者模式
 * main方法即使用者
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 14:45
 */
public class Main {
    public static void main(String[] args) {
        showBike(new OfoBuilder());
        showBike(new MobikeBuilder());
    }

    private static void showBike(Builder builder) {
        Director director = new Director(builder);
        Bike bike = director.construct();
        bike.getFrame().frame();
        bike.getSeat().seat();
        bike.getTire().tire();
    }
}
