package com.iflytek.pattern.behavior.visitor;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 23:26
 */
public interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}
