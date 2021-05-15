package com.iflytek.pattern.structure.bridge.example2.color.impl;

import com.iflytek.pattern.structure.bridge.example2.color.IColor;

/**
 * 红色
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/15
 */
public class Red implements IColor {
    @Override
    public String use() {
        return "红";
    }
}
