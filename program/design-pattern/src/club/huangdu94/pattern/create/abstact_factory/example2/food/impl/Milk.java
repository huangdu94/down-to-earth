package club.huangdu94.pattern.create.abstact_factory.example2.food.impl;

import club.huangdu94.pattern.create.abstact_factory.example2.food.IFood;

/**
 * 牛奶
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:20
 */
public class Milk implements IFood {
    @Override
    public void eat() {
        System.out.println("牛奶.");
    }
}
