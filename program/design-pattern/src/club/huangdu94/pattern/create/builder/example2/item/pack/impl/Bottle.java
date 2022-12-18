package club.huangdu94.pattern.create.builder.example2.item.pack.impl;

import club.huangdu94.pattern.create.builder.example2.item.pack.Packing;

/**
 * 瓶子
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:07
 */
public class Bottle implements Packing {
    @Override
    public String pack() {
        return "bottle";
    }
}
