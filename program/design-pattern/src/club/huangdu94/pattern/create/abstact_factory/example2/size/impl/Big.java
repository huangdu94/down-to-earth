package club.huangdu94.pattern.create.abstact_factory.example2.size.impl;

import club.huangdu94.pattern.create.abstact_factory.example2.size.ISize;

/**
 * 大
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:46
 */
public class Big implements ISize {
    @Override
    public void show() {
        System.out.println("大.");
    }
}
