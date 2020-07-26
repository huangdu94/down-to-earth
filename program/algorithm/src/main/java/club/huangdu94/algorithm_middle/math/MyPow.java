package club.huangdu94.algorithm_middle.math;

/**
 * Pow(x, n)
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * 示例 1:
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2^-2 = 1/2^2 = 1/4 = 0.25
 * 说明:
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/22 0:17
 */
public class MyPow {
    // 复杂度o(1)
    public double myPow1(double x, int n) {
        return Math.pow(x, n);
    }

    // 时间复杂度o(n)
    public double myPow2(double x, int n) {
        if (n == 0 || x == 1)
            return 1;
        double result = x;
        if (n > 0) {
            for (int i = 1; i < n; i++) {
                result *= x;
            }
            return result;
        } else {
            for (int i = n + 1; i < 0; i++) {
                result *= x;
            }
            return 1 / result;
        }
    }

    // 时间复杂度o(1)(log n ,但考虑n范围最大就为32,所以时间复杂度为o(1))
    public double myPow3(double x, int n) {
        if (n == 0 || x == 1)
            return 1;
        boolean sign = n < 0;
        n = sign ? ~n + 1 : n;
        // 把n拆成 2^x1+2^x2+...+2^1+2^0
        double result = 1;
        while (n != 0) {
            if ((n & 1) == 1)
                result *= x;
            x *= x;
            n >>>= 1;
        }
        return sign ? 1 / result : result;
    }

    public static void main(String[] args) {
        MyPow myPow = new MyPow();
        int x = 100;
        int n = Integer.MIN_VALUE;
        double result;
        long start = System.currentTimeMillis();
        result = myPow.myPow1(x, n);
        long end = System.currentTimeMillis();
        System.out.println("执行耗时(ms)：" + (end - start));
        System.out.println("执行结果：" + result);
        start = System.currentTimeMillis();
        result = myPow.myPow2(x, n);
        end = System.currentTimeMillis();
        System.out.println("执行耗时(ms)：" + (end - start));
        System.out.println("执行结果：" + result);
        start = System.currentTimeMillis();
        result = myPow.myPow3(x, n);
        end = System.currentTimeMillis();
        System.out.println("执行耗时(ms)：" + (end - start));
        System.out.println("执行结果：" + result);
    }
}
