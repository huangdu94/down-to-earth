package com.iflytek.pattern.structure.facade;

import com.iflytek.pattern.structure.decorator.shape.Circle;
import com.iflytek.pattern.structure.decorator.shape.Rectangle;
import com.iflytek.pattern.structure.decorator.shape.Shape;
import com.iflytek.pattern.structure.decorator.shape.Square;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 19:42
 */
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle() {
        circle.draw();
    }

    public void drawRectangle() {
        rectangle.draw();
    }

    public void drawSquare() {
        square.draw();
    }
}
