package com.iflytek.pattern.create.abstact_factory.example2;

import com.iflytek.pattern.create.abstact_factory.example2.food.IFood;
import com.iflytek.pattern.create.abstact_factory.example2.food.impl.Bread;
import com.iflytek.pattern.create.abstact_factory.example2.food.impl.Ham;
import com.iflytek.pattern.create.abstact_factory.example2.food.impl.Milk;
import com.iflytek.pattern.create.abstact_factory.example2.size.ISize;

/**
 * 食物工厂 抽象工厂的子类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:52
 */
public class FoodFactory extends AbstractFactory {
    @Override
    public IFood getFood(String food) {
        if (food == null) {
            return null;
        }
        switch (food.toUpperCase()) {
            case "BREAD":
                return new Bread();
            case "MILK":
                return new Milk();
            case "HAM":
                return new Ham();
            default:
                return null;
        }
    }

    @Override
    public ISize getSize(String size) {
        return null;
    }
}
