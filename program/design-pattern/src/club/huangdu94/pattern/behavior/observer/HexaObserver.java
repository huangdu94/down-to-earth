package club.huangdu94.pattern.behavior.observer;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:37
 */
public class HexaObserver extends Observer {
    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Hex String: "
                + Integer.toHexString(subject.getState()).toUpperCase());
    }
}
