package com.iflytek.pattern.create.builder.item.pack.impl;

import com.iflytek.pattern.create.builder.item.pack.Packing;

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
