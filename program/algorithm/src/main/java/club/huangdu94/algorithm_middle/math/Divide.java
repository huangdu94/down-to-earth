package club.huangdu94.algorithm_middle.math;

/**
 * 两数相除
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * 示例 1:
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * 示例 2:
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 * 提示：
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/22 0:20
 */
public class Divide {
    public int divide3(int dividend, int divisor) {
        // 当被除数为0或除数为1时结果都为被除数
        if (dividend == 0 || divisor == 1)
            return dividend;
        // 当且仅当被除数为Integer.MIN_VALUE，除数为-1时可能溢出
        // 其它被除数除以-1得到其相反数
        if (divisor == -1)
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : ~dividend + 1;
        // true为正数 false为负数
        boolean sign = true;
        // 取反加一变相反数(题目要求不能使用乘法)
        // 为了防止Integer.MIN_VALUE求相反数溢出转换为long
        long dividendLong = dividend;
        if (dividend < 0) {
            sign = false;
            dividendLong = (~dividendLong + 1);
        }
        long divisorLong = divisor;
        if (divisor < 0) {
            sign = !sign;
            divisorLong = (~divisorLong + 1);
        }
        // 二分法边界 l = 0
        // r = ~(Integer.MIN_VALUE / 2) + 1
        // (除数为1和-1的情况都已经在上面返回了。只有这两种情况，商才有可能大于r)
        int l = 0;
        int r = 1073741824;
        int quotient = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            if (this.multiply(divisorLong, mid) <= dividendLong) {
                quotient = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return sign ? quotient : ~quotient + 1;
    }

    /**
     * 自制加法版乘法
     * multiplier1 >= 0 && multiplier2 >= 0
     * 时间复杂度o(n)(n<=31) -> o(1)
     *
     * @param multiplier1 乘数1
     * @param multiplier2 乘数2
     * @return 积
     */
    private long multiply(long multiplier1, int multiplier2) {
        long product = 0;
        while (multiplier2 != 0) {
            if ((multiplier2 & 1) == 1)
                product += multiplier1;
            multiplier1 += multiplier1;
            multiplier2 >>>= 1;
        }
        return product;
    }

    public int divide2(int dividend, int divisor) {
        if (dividend == Integer.MIN_VALUE && divisor == -1)
            return Integer.MAX_VALUE;
        return dividend / divisor;
    }

    public int divide(int dividend, int divisor) {
        if (dividend == 0 || divisor == 1)
            return dividend;
        if (divisor == -1)
            return dividend == Integer.MIN_VALUE ? Integer.MAX_VALUE : ~dividend + 1;
        boolean sign = true;
        // 取反加一变相反数(题目要求不能使用乘法)
        // 为了防止Integer.MIN_VALUE求相反数溢出转换为long
        long dividendLong = dividend;
        if (dividend < 0) {
            sign = false;
            dividendLong = (~dividendLong + 1);
        }
        long divisorLong = divisor;
        if (divisor < 0) {
            sign = !sign;
            divisorLong = (~divisorLong + 1);
        }
        int result = this.division2(dividendLong, divisorLong);
        return sign ? result : ~result + 1;
    }

    private int division(long dividend, long divisor) {
        if (dividend < divisor)
            return 0;
        int quotient = 1;
        long divisorSum = divisor;
        while (dividend >= (divisorSum + divisorSum)) {
            divisorSum += divisorSum;
            quotient <<= 1;
        }
        return quotient | division(dividend - divisorSum, divisor);
    }

    private int division2(long dividend, long divisor) {
        if (dividend < divisor)
            return 0;
        int quotient = 1;
        // 从1开始最多对加31次会溢出
        long[] divisorSumArr = new long[31];
        long divisorSum = divisor;
        divisorSumArr[0] = divisorSum;
        int len = 1;
        while (dividend >= (divisorSum += divisorSum)) {
            divisorSumArr[len++] = divisorSum;
            quotient <<= 1;
        }
        dividend -= divisorSumArr[len - 1];
        for (int i = len - 2, k = quotient >>> 1; i >= 0; i--, k >>>= 1) {
            if (dividend > divisorSumArr[i]) {
                quotient |= k;
                dividend -= divisorSumArr[i];
            }
        }
        return quotient;
    }

    public static void main(String[] args) {
        Divide divide = new Divide();
        System.out.println(divide.divide(10, 3));
    }
}
