package club.huangdu94.pattern.behavior.visitor;

import club.huangdu94.pattern.behavior.visitor.entity.Keyboard;
import club.huangdu94.pattern.behavior.visitor.entity.Monitor;
import club.huangdu94.pattern.behavior.visitor.entity.Mouse;

/**
 * 访问者接口
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 23:26
 */
public interface ComputerPartVisitor {
    void visit(Computer computer);

    void visit(Mouse mouse);

    void visit(Keyboard keyboard);

    void visit(Monitor monitor);
}
