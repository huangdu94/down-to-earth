package com.iflytek.pattern.create.prototype.shape;

/**
 * 三角形
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 16:25
 */
public class Rectangle extends AbstractShape {
    public Rectangle() {
        setType("rectangle");
    }

    @Override
    public void draw() {
        System.out.println("画三角形.");
    }
}
