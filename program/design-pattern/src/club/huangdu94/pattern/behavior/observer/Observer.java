package club.huangdu94.pattern.behavior.observer;

/**
 * Observer 观察者抽象类
 * 被观察者这里被作为观察者的成员变量，然而这不是必须的。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/29 22:28
 */
public abstract class Observer {
    protected Subject subject;

    public abstract void update();
}
