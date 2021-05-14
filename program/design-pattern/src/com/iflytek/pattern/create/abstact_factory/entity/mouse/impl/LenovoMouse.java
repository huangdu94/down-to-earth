package com.iflytek.pattern.create.abstact_factory.entity.mouse.impl;

import com.iflytek.pattern.create.abstact_factory.entity.mouse.IMouse;

/**
 * 联想鼠标
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class LenovoMouse implements IMouse {
    @Override
    public void click() {
        System.out.println("联想鼠标.");
    }
}
