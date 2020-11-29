package com.iflytek.pattern.structure.bridge;

/**
 * 关键点，把需要调用的方法的对象作为构造参数传进来。
 * 这样构造方法中传不同的对象，方法就会有不同的特性
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 20:25
 */
public class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}
