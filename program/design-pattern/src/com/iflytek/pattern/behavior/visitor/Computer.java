package com.iflytek.pattern.behavior.visitor;

/**
 * 电脑的accept方法稍微特殊一点点，它除了自己要被观察者访问以外，还要调用其组成部分的每一个accept方法
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 23:27
 */
public class Computer implements ComputerPart {

    ComputerPart[] parts;

    public Computer() {
        parts = new ComputerPart[]{new Mouse(), new Keyboard(), new Monitor()};
    }

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        for (ComputerPart part : parts) {
            part.accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);
    }
}
