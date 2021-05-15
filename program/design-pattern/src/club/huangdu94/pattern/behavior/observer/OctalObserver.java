package club.huangdu94.pattern.behavior.observer;

/**
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:36
 */
public class OctalObserver extends Observer {
    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("Octal String: "
                + Integer.toOctalString(subject.getState()));
    }
}
