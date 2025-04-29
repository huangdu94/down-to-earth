package security;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 */
public class Api {
    public static void main(String[] args) {
        /*
         * 启用SecurityManager有两种方式：
         * 1. JVM参数 -Djava.security.manager -Djava.security.policy=my.policy
         * (policy文件参考 .\lib\security\java.policy)
         * 其中my.policy是指定的策略文件，相对路径/绝对路径均可
         * 2. 代码中设置 System.setSecurityManager()
         */
        SecurityManager sm = System.getSecurityManager();
        System.out.println(sm);
        // 使用此方法的话，该方法可以被其它地方调用
        // 如果不使用此方法的话，即使代码本身拥有权限，但是调用者没有权限，代码也会执行失败。
        AccessController.doPrivileged((PrivilegedAction<Object>)() -> null);
    }
}
