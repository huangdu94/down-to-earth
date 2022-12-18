package club.huangdu94.pattern.structure.decorator.shape;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 19:41
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Square::draw()");
    }
}
