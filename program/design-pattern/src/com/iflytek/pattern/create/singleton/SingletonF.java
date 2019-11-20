package com.iflytek.pattern.create.singleton;

/**
 * 枚举类实现单例 饿汉式 线程安全
 * 实现单例模式的最佳方法，反序列化机制也不能破坏单例
 *
 * @author DuHuang 2019/10/31 14:17
 */
public enum SingletonF {
    INSTANCE;
}
