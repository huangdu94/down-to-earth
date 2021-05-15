package com.iflytek.pattern.structure.bridge.example2.pen;

import com.iflytek.pattern.structure.bridge.example2.color.IColor;

/**
 * 抽象笔
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public abstract class AbstractPen implements IPen {
    private final String brand;

    public AbstractPen(String brand) {
        this.brand = brand;
    }

    @Override
    public void draw(IColor color) {
        System.out.println(String.format("当前[%s]牌笔在使用[%s]色画画.", brand, color.use()));
    }
}
