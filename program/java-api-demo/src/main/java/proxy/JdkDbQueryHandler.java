package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk自带的动态代理
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/16 15:27
 */
public class JdkDbQueryHandler implements InvocationHandler {
    private IDbQuery dbQuery;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (dbQuery == null) {
            synchronized (this) {
                if (dbQuery == null) {
                    dbQuery = new DbQuery();
                }
            }
        }
        return dbQuery.request();
    }

    public static IDbQuery createJdkProxy() {
        return (IDbQuery) Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{IDbQuery.class}, new JdkDbQueryHandler());
    }
}
