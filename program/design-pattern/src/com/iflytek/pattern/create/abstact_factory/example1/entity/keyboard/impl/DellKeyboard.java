package com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.impl;

import com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.IKeyboard;

/**
 * 戴尔键盘
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class DellKeyboard implements IKeyboard {
    @Override
    public void knock() {
        System.out.println("戴尔键盘.");
    }
}
