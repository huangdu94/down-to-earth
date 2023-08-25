package club.huangdu94.pattern.create.singleton;

/**
 * 注册式单例
 * 枚举类实现单例 饿汉式 线程安全
 * 实现单例模式的最佳方法，反序列化机制也不能破坏单例
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 14:17
 */
public enum SingletonF {
    INSTANCE;
}
