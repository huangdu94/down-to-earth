package proxy;

/**
 * 手动创建DbQuery代理类
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/16 15:19
 */
public class DbQueryProxy implements IDbQuery {
    IDbQuery dbQuery;

    public DbQueryProxy() {
        System.out.println("DbQueryProxy构建完成.");
    }

    @Override
    public String request() {
        if (dbQuery == null) {
            synchronized (this) {
                if (dbQuery == null) {
                    dbQuery = new DbQuery();
                }
            }
        }
        return dbQuery.request();
    }
}
