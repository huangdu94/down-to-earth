package com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.impl;

import com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.IKeyboard;

/**
 * 苹果键盘
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class MacKeyboard implements IKeyboard {
    @Override
    public void knock() {
        System.out.println("苹果键盘.");
    }
}