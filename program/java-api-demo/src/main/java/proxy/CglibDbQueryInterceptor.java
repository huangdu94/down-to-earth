package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * CGLIB动态代理
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/16 15:41
 */
public class CglibDbQueryInterceptor implements MethodInterceptor {
    private IDbQuery dbQuery;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy){
        if (dbQuery == null) {
            synchronized (this) {
                if (dbQuery == null) {
                    dbQuery = new DbQuery();
                }
            }
        }
        return dbQuery.request();
    }

    public static IDbQuery createCglibProxy() {
        Enhancer enhancer = new Enhancer();
        //指定切入器，定义代理类逻辑
        enhancer.setCallback(new CglibDbQueryInterceptor());
        //不要求非要实现接口
        //enhancer.setSuperclass(DbQuery.class);
        //指定实现的接口
        enhancer.setInterfaces(new Class[]{IDbQuery.class});
        //生成代理类
        return (IDbQuery) enhancer.create();
    }
}
