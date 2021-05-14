package com.iflytek.pattern.create.prototype;

import com.iflytek.pattern.create.prototype.shape.AbstractShape;

/**
 * 原型模式
 * 当直接创建对象的代价比较大时，可以采用此方式创建
 * 分为浅拷贝 深拷贝 本示例为浅拷贝
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 16:08
 */
public class Main {
    public static void main(String[] args) {
        AbstractShape shape1 = ShapeCache.getShape("1");
        AbstractShape shape4 = ShapeCache.getShape("1");
        System.out.println(shape1.getType());
        shape1.draw();
        System.out.println(shape4.getType());
        shape4.draw();
        System.out.println(shape1 == shape4);

        AbstractShape shape2 = ShapeCache.getShape("2");
        System.out.println(shape2.getType());
        shape2.draw();

        AbstractShape shape3 = ShapeCache.getShape("3");
        System.out.println(shape3.getType());
        shape3.draw();
    }
}
