package club.huangdu94.pattern.create.abstact_factory.example2.food.impl;

import club.huangdu94.pattern.create.abstact_factory.example2.food.IFood;

/**
 * 面包
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:19
 */
public class Bread implements IFood {
    @Override
    public void eat() {
        System.out.println("面包.");
    }
}
