package club.huangdu94.pattern.behavior.visitor.entity;

import club.huangdu94.pattern.behavior.visitor.ComputerPart;
import club.huangdu94.pattern.behavior.visitor.ComputerPartVisitor;

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
