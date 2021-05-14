package com.iflytek.pattern.create.prototype;

import java.util.Hashtable;

import com.iflytek.pattern.create.prototype.shape.AbstractShape;
import com.iflytek.pattern.create.prototype.shape.Circle;
import com.iflytek.pattern.create.prototype.shape.Rectangle;
import com.iflytek.pattern.create.prototype.shape.Square;

/**
 * 用于获取Shape实体类的缓存类，缓存在HashTable中
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 16:31
 */
public class ShapeCache {
    private static final Hashtable<String, AbstractShape> shapeTable = new Hashtable<>();

    static {
        loadCache();
    }

    public static AbstractShape getShape(String shapeId) {
        if (shapeId == null) {
            return null;
        }
        if (!shapeTable.containsKey(shapeId)) {
            return null;
        } else {
            return shapeTable.get(shapeId).clone();
        }
    }

    /**
     * 实际中此过程可能非常耗时，但是只需要执行一次，可以多次使用
     */
    private static void loadCache() {
        Circle circle = new Circle();
        circle.setId("1");
        Square square = new Square();
        square.setId("2");
        Rectangle rectangle = new Rectangle();
        rectangle.setId("3");
        shapeTable.put(circle.getId(), circle);
        shapeTable.put(square.getId(), square);
        shapeTable.put(rectangle.getId(), rectangle);
    }
}
