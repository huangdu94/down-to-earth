package com.iflytek.pattern.create.builder.item;

import com.iflytek.pattern.create.builder.item.pack.Packing;

/**
 * 食物接口
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 15:03
 */
public interface Item {
    String name();
    Packing packing();
    double price();
}
