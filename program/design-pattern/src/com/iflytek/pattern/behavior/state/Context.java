package com.iflytek.pattern.behavior.state;

/**
 * 状态容器，需要被改变状态的类
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:49
 */
public class Context {
    private State state;

    public Context() {
        state = null;
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
