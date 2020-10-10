package work.huangdu.question_bank.medium;

/**
 * 201. 数字范围按位与
 * 给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。
 * 示例 1:
 * 输入: [5,7]
 * 输出: 4
 * 示例 2:
 * 输入: [0,1]
 * 输出: 0
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/23 0:10
 */
public class RangeBitwiseAnd {
    // 暴力 0到Integer.MAX超时
    public int rangeBitwiseAnd2(int m, int n) {
        int res = 0XFFFFFFFF;
        for (int i = m; i <= n; i++) {
            res &= i;
        }
        return res;
    }

    // 要考虑int溢出的情况，稍作优化通过但耗时500多毫秒
    public int rangeBitwiseAnd3(int m, int n) {
        if (m == 0) return 0;
        int res = 0XFFFFFFFF;
        for (int i = m; i != Integer.MIN_VALUE && i <= n; i++) {
            res &= i;
            if (res == 0) return 0;
        }
        return res;
    }

    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0 || m == n) return m;
        int res = 0;
        int mask = 0X40000000;
        while ((m & mask) == 0 && (n & mask) == 0) {
            mask >>= 1;
        }
        while ((m & mask) == (n & mask)) {
            res |= (m & mask);
            mask >>= 1;
        }
        return res;
    }

    public static void main(String[] args) {
        RangeBitwiseAnd rangeBitwiseAnd = new RangeBitwiseAnd();
        int res = rangeBitwiseAnd.rangeBitwiseAnd(5, 7);
        System.out.println(res);
    }
}
