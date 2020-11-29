package com.iflytek.pattern.behavior.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * 备忘录记录器
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:12
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<Memento>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}
