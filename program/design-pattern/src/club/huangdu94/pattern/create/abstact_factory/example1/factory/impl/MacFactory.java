package club.huangdu94.pattern.create.abstact_factory.example1.factory.impl;

import club.huangdu94.pattern.create.abstact_factory.example1.entity.headset.IHeadset;
import club.huangdu94.pattern.create.abstact_factory.example1.entity.headset.impl.MacHeadset;
import club.huangdu94.pattern.create.abstact_factory.example1.entity.keyboard.IKeyboard;
import club.huangdu94.pattern.create.abstact_factory.example1.entity.keyboard.impl.MacKeyboard;
import club.huangdu94.pattern.create.abstact_factory.example1.entity.mouse.IMouse;
import club.huangdu94.pattern.create.abstact_factory.example1.entity.mouse.impl.MacMouse;
import club.huangdu94.pattern.create.abstact_factory.example1.factory.AbstractFactory;

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
