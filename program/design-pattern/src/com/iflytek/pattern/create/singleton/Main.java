package com.iflytek.pattern.create.singleton;

/**
 * 单例模式
 * 破坏单例模式的三种方式。
 * 1. 克隆 -> 只要不实现Cloneable
 * 2. 序列化反序列化 —> 实现readResolve()方法
 * 3. 反射 -> 加一个计数器，在构造方法里判断，如果计数器已经大于1了，构造方法再调用抛出异常
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 14:19
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(SingletonF.INSTANCE.toString());
    }
}
