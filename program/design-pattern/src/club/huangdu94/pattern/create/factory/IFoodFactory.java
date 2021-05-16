package club.huangdu94.pattern.create.factory;

import club.huangdu94.pattern.create.factory.food.IFood;

/**
 * 食物创建工厂接口
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/16
 */
public interface IFoodFactory {
    IFood getFood();
}
