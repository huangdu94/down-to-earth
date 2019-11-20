package com.iflytek.pattern.create.singleton;

/**
 * 饿汉式 效率高 线程安全 (静态内部类)
 *
 * @author DuHuang 2019/10/31 14:13
 */
public class SingletonE {
    private SingletonE() {

    }

    private static class SingletonEHolder {
        private static final SingletonE INSTANCE = new SingletonE();
    }

    public static SingletonE getInstance() {
        return SingletonEHolder.INSTANCE;
    }
}
