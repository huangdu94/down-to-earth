package club.huangdu94.pattern.create.abstact_factory.example2.size.impl;

import club.huangdu94.pattern.create.abstact_factory.example2.size.ISize;

/**
 * 小
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:44
 */
public class Small implements ISize {
    @Override
    public void show() {
        System.out.println("小.");
    }
}
