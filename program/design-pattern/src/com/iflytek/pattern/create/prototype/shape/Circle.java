package com.iflytek.pattern.create.prototype.shape;

/**
 * @author duhuang@iflytek
 * @date 2019/10/31 16:28
 */
public class Circle extends AbstractShape {
    public Circle() {
        setType("circle");
    }

    @Override
    public void draw() {
        System.out.println("画圆.");
    }
}
