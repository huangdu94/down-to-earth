package club.huangdu94.pattern.behavior.observer;

/**
 * Observer 观察者抽象类
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:28
 */
public abstract class Observer {
    protected Subject subject;

    public abstract void update();
}
