package proxy;

/**
 * 数据库查询实现类 (模拟重量级对象,构造方法中加线程sleep)
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/16 15:16
 */
public class DbQuery implements IDbQuery {
    public DbQuery() {
        System.out.println("DbQuery构建中...请耐心等待.");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("DbQuery构建失败...");
            e.printStackTrace();
        }
        System.out.println("DbQuery构建完成.");
    }

    @Override
    public String request() {
        return "Hello World!";
    }
}
