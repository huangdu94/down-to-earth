package com.iflytek.pattern.create.abstact_factory.example1.entity.mouse.impl;

import com.iflytek.pattern.create.abstact_factory.example1.entity.mouse.IMouse;

/**
 * 戴尔鼠标
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class DellMouse implements IMouse {
    @Override
    public void click() {
        System.out.println("戴尔鼠标.");
    }
}
