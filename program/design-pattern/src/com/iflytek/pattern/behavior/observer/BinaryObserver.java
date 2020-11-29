package com.iflytek.pattern.behavior.observer;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:30
 */
public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Binary String: "
                + Integer.toBinaryString(subject.getState()));
    }
}
