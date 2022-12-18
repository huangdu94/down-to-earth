package club.huangdu94.pattern.create.singleton;

/**
 * 饿汉式 效率高 线程安全 (双重校验锁)
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com) 2019/10/31 14:10
 */
public class SingletonD {
    private volatile static SingletonD instance;

    private SingletonD() {
    }

    public static SingletonD getInstance() {
        if (instance == null) {
            synchronized (SingletonD.class) {
                if (instance == null) {
                    instance = new SingletonD();
                }
            }
        }
        return instance;
    }
}
