package com.iflytek.pattern.create.abstact_factory;

import com.iflytek.pattern.create.abstact_factory.food.IFood;
import com.iflytek.pattern.create.abstact_factory.size.impl.Big;
import com.iflytek.pattern.create.abstact_factory.size.ISize;
import com.iflytek.pattern.create.abstact_factory.size.impl.Medium;
import com.iflytek.pattern.create.abstact_factory.size.impl.Small;

/**
 * 尺寸工厂 抽象工厂的子类
 *
 * @author DuHuang 2019/10/31 10:54
 */
public class SizeFactory extends AbstractFactory {
    @Override
    public IFood getFood(String food) {
        return null;
    }

    @Override
    public ISize getSize(String size) {
        if (size == null) {
            return null;
        }
        switch (size.toUpperCase()) {
            case "SMALL":
                return new Small();
            case "MEDIUM":
                return new Medium();
            case "BIG":
                return new Big();
            default:
                return null;
        }
    }
}
