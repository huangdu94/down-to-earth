package club.huangdu94.pattern.create.builder.example1.entity.tire.impl;

import club.huangdu94.pattern.create.builder.example1.entity.tire.ITire;

/**
 * 实心轮胎
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class SolidTire implements ITire {
    @Override
    public void tire() {
        System.out.println("实心轮胎.");
    }
}
