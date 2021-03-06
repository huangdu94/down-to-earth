package club.huangdu94.pattern.structure.bridge.example1;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 20:22
 */
public class RedCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: "
                + radius + ", x: " + x + ", " + y + "]");
    }
}
