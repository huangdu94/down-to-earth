package com.iflytek.pattern.structure.decorator;

import com.iflytek.pattern.structure.decorator.shape.Shape;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 18:53
 */
public class RedShapeDecorator extends ShapeDecorator {

    public RedShapeDecorator(Shape shape) {
        super(shape);
    }

    @Override
    public void draw() {
        shape.draw();
        setRedBorder(shape);
    }

    private void setRedBorder(Shape shape) {
        System.out.println("Border Color: Red");
    }
}
