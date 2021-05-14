package com.iflytek.pattern.create.abstact_factory.example1.factory.impl;

import com.iflytek.pattern.create.abstact_factory.example1.entity.headset.IHeadset;
import com.iflytek.pattern.create.abstact_factory.example1.entity.headset.impl.MacHeadset;
import com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.IKeyboard;
import com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.impl.MacKeyboard;
import com.iflytek.pattern.create.abstact_factory.example1.entity.mouse.IMouse;
import com.iflytek.pattern.create.abstact_factory.example1.entity.mouse.impl.MacMouse;
import com.iflytek.pattern.create.abstact_factory.example1.factory.AbstractFactory;

/**
 * 苹果工厂
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class MacFactory extends AbstractFactory {
    @Override
    public IMouse getMouse() {
        return new MacMouse();
    }

    @Override
    public IKeyboard getKeyboard() {
        return new MacKeyboard();
    }

    @Override
    public IHeadset getHeadset() {
        return new MacHeadset();
    }
}
