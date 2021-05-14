package com.iflytek.pattern.create.singleton;

/**
 * 懒汉式 线程不安全 (不推荐)
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 14:04
 */
public class SingletonA {
    private static SingletonA instance;

    private SingletonA() {
    }

    public static SingletonA getInstance() {
        if (instance == null) {
            instance = new SingletonA();
        }
        return instance;
    }
}
