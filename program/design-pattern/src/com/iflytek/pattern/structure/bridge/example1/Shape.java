package com.iflytek.pattern.structure.bridge.example1;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 20:23
 */
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
