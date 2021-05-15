package club.huangdu94.pattern.create.builder.example2.item;

import club.huangdu94.pattern.create.builder.example2.item.pack.impl.Bottle;
import club.huangdu94.pattern.create.builder.example2.item.pack.Packing;

/**
 * 冷饮料抽象类
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:10
 */
public abstract class ColdDrink implements Item {
    @Override
    public abstract String name();

    @Override
    public Packing packing() {
        return new Bottle();
    }

    @Override
    public abstract double price();
}
