package com.iflytek.pattern.create.abstact_factory.example1.factory.impl;

import com.iflytek.pattern.create.abstact_factory.example1.entity.headset.IHeadset;
import com.iflytek.pattern.create.abstact_factory.example1.entity.headset.impl.LenovoHeadset;
import com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.IKeyboard;
import com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.impl.LenovoKeyboard;
import com.iflytek.pattern.create.abstact_factory.example1.entity.mouse.IMouse;
import com.iflytek.pattern.create.abstact_factory.example1.entity.mouse.impl.LenovoMouse;
import com.iflytek.pattern.create.abstact_factory.example1.factory.AbstractFactory;

/**
 * 联想工厂
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/5/14
 */
public class LenovoFactory extends AbstractFactory {
    @Override
    public IMouse getMouse() {
        return new LenovoMouse();
    }

    @Override
    public IKeyboard getKeyboard() {
        return new LenovoKeyboard();
    }

    @Override
    public IHeadset getHeadset() {
        return new LenovoHeadset();
    }
}
