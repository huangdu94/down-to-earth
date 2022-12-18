package club.huangdu94.pattern.structure.decorator.shape;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 18:45
 */
public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Rectangle");
    }
}
