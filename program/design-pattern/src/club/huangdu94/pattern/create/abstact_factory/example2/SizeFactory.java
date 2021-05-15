package club.huangdu94.pattern.create.abstact_factory.example2;

import club.huangdu94.pattern.create.abstact_factory.example2.food.IFood;
import club.huangdu94.pattern.create.abstact_factory.example2.size.ISize;
import club.huangdu94.pattern.create.abstact_factory.example2.size.impl.Big;
import club.huangdu94.pattern.create.abstact_factory.example2.size.impl.Medium;
import club.huangdu94.pattern.create.abstact_factory.example2.size.impl.Small;

/**
 * 尺寸工厂 抽象工厂的子类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:54
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
