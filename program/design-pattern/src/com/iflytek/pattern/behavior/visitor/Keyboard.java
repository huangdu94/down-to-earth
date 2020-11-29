package com.iflytek.pattern.behavior.visitor;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 23:27
 */
public class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
