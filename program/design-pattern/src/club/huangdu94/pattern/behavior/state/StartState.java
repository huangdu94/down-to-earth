package club.huangdu94.pattern.behavior.state;

/**
 * 开始状态，既表示一个状态，有提供方法改变状态容器的状态为该状态
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:49
 */
public class StartState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in start state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Start State";
    }
}
