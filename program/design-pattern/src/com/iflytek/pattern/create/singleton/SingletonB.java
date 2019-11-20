package com.iflytek.pattern.create.singleton;

/**
 * 饿汉模式 线程安全 但是效率低 (不推荐)
 *
 * @author DuHuang 2019/10/31 14:06
 */
public class SingletonB {
    private static SingletonB instance;

    private SingletonB() {

    }

    public static synchronized SingletonB getInstance() {
        if (instance == null) {
            instance = new SingletonB();
        }
        return instance;
    }
}
