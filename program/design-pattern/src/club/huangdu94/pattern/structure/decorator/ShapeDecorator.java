package club.huangdu94.pattern.structure.decorator;

import club.huangdu94.pattern.structure.decorator.shape.Shape;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 18:51
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape shape;

    public ShapeDecorator(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void draw() {
        shape.draw();
    }
}
