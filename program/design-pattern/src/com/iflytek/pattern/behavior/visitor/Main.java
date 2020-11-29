package com.iflytek.pattern.behavior.visitor;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 23:32
 */
public class Main {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}
