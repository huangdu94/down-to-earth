package club.huangdu94.pattern.create.prototype.shape;

/**
 * 正方形
 *
 * @author duhuang@iflytek
 * @date 2019/10/31 16:27
 */
public class Square extends AbstractShape {
    public Square() {
        setType("square");
    }

    @Override
    public void draw() {
        System.out.println("画正方形.");
    }
}
