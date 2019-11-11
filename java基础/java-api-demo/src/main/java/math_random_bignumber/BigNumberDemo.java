package math_random_bignumber;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 大数字处理类
 *
 * @author duhuang@iflytek
 * @version 2019/11/11 15:09
 */
public class BigNumberDemo {
    public static void main(String[] args) {
        BigInteger bigIntegerA = new BigInteger("11111111111111111111111111111111111111111");
        BigInteger bigIntegerB = new BigInteger("11111111111111111111111111111111111111111");
        BigDecimal bigDecimalA = new BigDecimal("-1.23E-12");
        BigDecimal bigDecimalB = new BigDecimal("-1.23E-12");
        System.out.println(bigDecimalA.add(bigDecimalB));
        System.out.println(bigIntegerA.add(bigIntegerB));
    }
}
