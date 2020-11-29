package com.iflytek.pattern.behavior.interpreter;

/**
 * 传入的参数context内包含data则结果为true
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 21:36
 */
public class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        return context.contains(data);
    }
}
