package club.huangdu94.pattern.behavior.state;

/**
 * 停止状态
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:51
 */
public class StopState implements State {
    @Override
    public void doAction(Context context) {
        System.out.println("Player is in stop state");
        context.setState(this);
    }

    @Override
    public String toString() {
        return "Stop State";
    }
}