package com.iflytek.pattern.behavior.memento;

/**
 * 使用到备忘录的类
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:11
 */
public class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    /**
     * 把当前状态转换成备忘录
     */
    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    /**
     * 读取备忘录中的状态
     */
    public void getStateFromMemento(Memento Memento) {
        state = Memento.getState();
    }
}
