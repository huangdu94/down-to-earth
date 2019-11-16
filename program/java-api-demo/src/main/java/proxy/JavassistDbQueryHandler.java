package proxy;


import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;
import javassist.util.proxy.ProxyObject;

import java.lang.reflect.Method;

/**
 * Javassist动态代理(代理工厂创建)
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/16 15:58
 */
public class JavassistDbQueryHandler implements MethodHandler {
    private IDbQuery dbQuery;

    @Override
    public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args) {
        if (dbQuery == null) {
            synchronized (this) {
                if (dbQuery == null) {
                    dbQuery = new DbQuery();
                }
            }
        }
        return dbQuery.request();
    }

    public static IDbQuery createJavassistDbQuery() throws Exception {
        ProxyFactory proxyFactory = new ProxyFactory();
        //指定接口
        proxyFactory.setInterfaces(new Class[]{IDbQuery.class});
        Class proxyClass = proxyFactory.createClass();
        ProxyObject proxyObject = (ProxyObject) proxyClass.getDeclaredConstructor().newInstance();
        //设置Handler处理器
        proxyObject.setHandler(new JavassistDbQueryHandler());
        return (IDbQuery) proxyObject;
    }
}
