package club.huangdu94.pattern.create.builder.example2.item.pack.impl;

import club.huangdu94.pattern.create.builder.example2.item.pack.Packing;

/**
 * 包装纸
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:05
 */
public class Wrapper implements Packing {
    @Override
    public String pack() {
        return "wrapper";
    }
}
