package com.iflytek.pattern.behavior.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:23
 */
public class Subject {
    private List<Observer> observers = new ArrayList<>();
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
        notifyAllObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void notifyAllObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }
}
