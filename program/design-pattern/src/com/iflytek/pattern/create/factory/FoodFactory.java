package com.iflytek.pattern.create.factory;

import com.iflytek.pattern.create.factory.food.impl.Bread;
import com.iflytek.pattern.create.factory.food.impl.Ham;
import com.iflytek.pattern.create.factory.food.IFood;
import com.iflytek.pattern.create.factory.food.impl.Milk;

/**
 * 食物工厂
 *
 * @author DuHuang 2019/10/31 10:25
 */
public class FoodFactory {
    public IFood getFood(String foodType) {
        if (foodType == null) {
            return null;
        }
        switch (foodType.toUpperCase()) {
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
}
