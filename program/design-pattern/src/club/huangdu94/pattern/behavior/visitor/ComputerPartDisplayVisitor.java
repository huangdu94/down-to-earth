package club.huangdu94.pattern.behavior.visitor;

import club.huangdu94.pattern.behavior.visitor.entity.Keyboard;
import club.huangdu94.pattern.behavior.visitor.entity.Monitor;
import club.huangdu94.pattern.behavior.visitor.entity.Mouse;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 23:33
 */
public class ComputerPartDisplayVisitor implements ComputerPartVisitor {


    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer.");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse.");
    }

    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard.");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor.");
    }
}
