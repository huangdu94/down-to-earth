package com.iflytek.pattern.create.abstact_factory.factory;

import com.iflytek.pattern.create.abstact_factory.entity.headset.IHeadset;
import com.iflytek.pattern.create.abstact_factory.entity.keyboard.IKeyboard;
import com.iflytek.pattern.create.abstact_factory.entity.mouse.IMouse;

/**
 * 抽象工厂
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:48
 */
public abstract class AbstractFactory {
    public abstract IMouse getMouse();

    public abstract IKeyboard getKeyboard();

    public abstract IHeadset getHeadset();
}
