package work.huangdu.common.util;

/**
 * 日志打印工具类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/9/6
 */
public class LoggerUtil {
    public static void info(String message) {
        System.out.println(message);
    }

    public static void error(String message) {
        System.err.println(message);
    }
}
