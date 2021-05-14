package com.iflytek.pattern.create.abstact_factory.example1.factory.impl;

import com.iflytek.pattern.create.abstact_factory.example1.entity.headset.IHeadset;
import com.iflytek.pattern.create.abstact_factory.example1.entity.headset.impl.DellHeadset;
import com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.IKeyboard;
import com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.impl.DellKeyboard;
import com.iflytek.pattern.create.abstact_factory.example1.entity.mouse.IMouse;
import com.iflytek.pattern.create.abstact_factory.example1.entity.mouse.impl.DellMouse;
import com.iflytek.pattern.create.abstact_factory.example1.factory.AbstractFactory;

/**
 * 戴尔工厂
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class DellFactory extends AbstractFactory {
    @Override
    public IMouse getMouse() {
        return new DellMouse();
    }

    @Override
    public IKeyboard getKeyboard() {
        return new DellKeyboard();
    }

    @Override
    public IHeadset getHeadset() {
        return new DellHeadset();
    }
}
