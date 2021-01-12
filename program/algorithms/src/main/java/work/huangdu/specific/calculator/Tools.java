package work.huangdu.specific.calculator;

/**
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/1 12:25
 */
public class Tools {
    /**
     * 判断字符是否为数字
     *
     * @param c 字符
     * @return 是否为数字
     */
    public static boolean isDigit(char c) {
        return c <= '9' && c >= '0';
    }
}
