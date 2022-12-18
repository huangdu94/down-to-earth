package club.huangdu94.pattern.create.factory;

import club.huangdu94.pattern.create.factory.food.IFood;
import club.huangdu94.pattern.create.factory.food.impl.Ham;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/16
 */
public class HamFactory implements IFoodFactory {
    @Override
    public IFood getFood() {
        return new Ham();
    }
}
