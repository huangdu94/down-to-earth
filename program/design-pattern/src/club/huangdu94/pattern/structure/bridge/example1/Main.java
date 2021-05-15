package club.huangdu94.pattern.structure.bridge.example1;

/**
 * 桥接模式
 * 但这个例子不是很好
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 20:19
 */
public class Main {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}
