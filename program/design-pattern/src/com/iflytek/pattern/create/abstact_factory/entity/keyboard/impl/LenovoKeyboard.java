package com.iflytek.pattern.create.abstact_factory.entity.keyboard.impl;

import com.iflytek.pattern.create.abstact_factory.entity.keyboard.IKeyboard;

/**
 * 联想键盘
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class LenovoKeyboard implements IKeyboard {
    @Override
    public void knock() {
        System.out.println("联想键盘.");
    }
}
