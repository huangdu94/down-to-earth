package com.iflytek.pattern.create.singleton;

/**
 * 饿汉式
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 14:09
 */
public class SingletonC {
    private static SingletonC instance = new SingletonC();

    private SingletonC() {
    }

    public static SingletonC getInstance() {
        return instance;
    }
}
