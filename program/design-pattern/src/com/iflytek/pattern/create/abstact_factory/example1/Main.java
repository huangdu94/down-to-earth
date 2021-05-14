package com.iflytek.pattern.create.abstact_factory.example1;

import com.iflytek.pattern.create.abstact_factory.example1.FactoryProducer;
import com.iflytek.pattern.create.abstact_factory.example1.entity.headset.IHeadset;
import com.iflytek.pattern.create.abstact_factory.example1.entity.keyboard.IKeyboard;
import com.iflytek.pattern.create.abstact_factory.example1.entity.mouse.IMouse;
import com.iflytek.pattern.create.abstact_factory.example1.factory.AbstractFactory;

/**
 * 抽象工厂模式
 * 工厂的工厂
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 10:59
 */
public class Main {
    public static void main(String[] args) {
        AbstractFactory dellFactory = FactoryProducer.getFactory("dell");
        IMouse dellMouse = dellFactory.getMouse();
        IKeyboard dellKeyboard = dellFactory.getKeyboard();
        IHeadset dellHeadset = dellFactory.getHeadset();
        AbstractFactory lenovoFactory = FactoryProducer.getFactory("lenovo");
        IMouse lenovoMouse = lenovoFactory.getMouse();
        IKeyboard lenovoKeyboard = lenovoFactory.getKeyboard();
        IHeadset lenovoHeadset = lenovoFactory.getHeadset();
        AbstractFactory macFactory = FactoryProducer.getFactory("mac");
        IMouse macMouse = macFactory.getMouse();
        IKeyboard macKeyboard = macFactory.getKeyboard();
        IHeadset macHeadset = macFactory.getHeadset();
        dellMouse.click();
        dellKeyboard.knock();
        dellHeadset.listen();
        lenovoMouse.click();
        lenovoKeyboard.knock();
        lenovoHeadset.listen();
        macMouse.click();
        macKeyboard.knock();
        macHeadset.listen();
    }
}
