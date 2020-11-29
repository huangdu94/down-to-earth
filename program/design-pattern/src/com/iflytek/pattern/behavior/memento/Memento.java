package com.iflytek.pattern.behavior.memento;

/**
 * 备忘录
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:11
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
