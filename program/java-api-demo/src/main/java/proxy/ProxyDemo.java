package proxy;

/**
 * 代理测试
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/16 15:24
 */
public class ProxyDemo {
    public static void main(String[] args) throws Exception {
        //1.手动创建代理类
        IDbQuery dbQuery = new DbQueryProxy();
        System.out.println(dbQuery.request());
        //2.Jdk自带的动态代理
        dbQuery = JdkDbQueryHandler.createJdkProxy();
        System.out.println(dbQuery.request());
        //3.Cglib动态代理
        dbQuery = CglibDbQueryInterceptor.createCglibProxy();
        System.out.println(dbQuery.request());
        //4.Javassist动态代理(代理工厂创建)
        dbQuery=JavassistDbQueryHandler.createJavassistDbQuery();
        System.out.println(dbQuery.request());
    }
}