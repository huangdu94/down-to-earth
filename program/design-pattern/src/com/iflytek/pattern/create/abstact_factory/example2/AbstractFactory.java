package com.iflytek.pattern.create.abstact_factory.example2;

import com.iflytek.pattern.create.abstact_factory.example2.food.IFood;
import com.iflytek.pattern.create.abstact_factory.example2.size.ISize;

/**
 * 抽象工厂
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:48
 */
public abstract class AbstractFactory {
    public abstract IFood getFood(String food);
    public abstract ISize getSize(String size);
}
