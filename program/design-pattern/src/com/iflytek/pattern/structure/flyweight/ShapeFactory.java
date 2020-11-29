package com.iflytek.pattern.structure.flyweight;

import com.iflytek.pattern.structure.flyweight.shape.Circle;
import com.iflytek.pattern.structure.flyweight.shape.Shape;

import java.util.HashMap;

/**
 * 享元模式的关键是把新创建的对象放到map中，根据key来拿对象，如果拿到null，则创建新对象放入对应key下
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 19:52
 */
public class ShapeFactory {
    private static final HashMap<String, Shape> circleMap = new HashMap<>();

    public static Shape getCircle(String color) {
        Circle circle = (Circle) circleMap.get(color);

        if (circle == null) {
            circle = new Circle(color);
            circleMap.put(color, circle);
            System.out.println("Creating circle of color : " + color);
        }
        return circle;
    }
}
