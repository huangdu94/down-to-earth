package club.huangdu94.pattern.create.builder.example2.item;

import club.huangdu94.pattern.create.builder.example2.item.pack.Packing;
import club.huangdu94.pattern.create.builder.example2.item.pack.impl.Wrapper;

/**
 * 汉堡抽象类
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:09
 */
public abstract class Burger implements Item {

    @Override
    public abstract String name();

    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract double price();
}
