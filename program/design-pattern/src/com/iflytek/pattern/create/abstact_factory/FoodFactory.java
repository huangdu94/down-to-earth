package com.iflytek.pattern.create.abstact_factory;

import com.iflytek.pattern.create.abstact_factory.food.IFood;
import com.iflytek.pattern.create.abstact_factory.food.impl.Bread;
import com.iflytek.pattern.create.abstact_factory.food.impl.Ham;
import com.iflytek.pattern.create.abstact_factory.food.impl.Milk;
import com.iflytek.pattern.create.abstact_factory.size.ISize;

/**
 * 食物工厂 抽象工厂的子类
 *
 * @author DuHuang 2019/10/31 10:52
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
