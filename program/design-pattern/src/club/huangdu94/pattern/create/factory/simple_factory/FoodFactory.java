package club.huangdu94.pattern.create.factory.simple_factory;

import club.huangdu94.pattern.create.factory.food.IFood;
import club.huangdu94.pattern.create.factory.food.impl.Bread;
import club.huangdu94.pattern.create.factory.food.impl.Ham;
import club.huangdu94.pattern.create.factory.food.impl.Milk;

/**
 * 食物工厂
 * 可使用反射机制优化简单工厂，使其扩展时不需要修改代码
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

    public IFood getFood(Class<? extends IFood> foodType) {
        if (foodType != null) {
            try {
                return foodType.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
