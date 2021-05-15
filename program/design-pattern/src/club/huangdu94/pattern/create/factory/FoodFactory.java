package club.huangdu94.pattern.create.factory;

import club.huangdu94.pattern.create.factory.food.impl.Bread;
import club.huangdu94.pattern.create.factory.food.impl.Milk;
import club.huangdu94.pattern.create.factory.food.impl.Ham;
import club.huangdu94.pattern.create.factory.food.IFood;

/**
 * 食物工厂
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:25
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
