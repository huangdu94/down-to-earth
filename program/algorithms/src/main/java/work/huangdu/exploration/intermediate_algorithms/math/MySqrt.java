package work.huangdu.exploration.intermediate_algorithms.math;

/**
 * x 的平方根
 * 实现 int sqrt(int x) 函数。
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * 示例 1:
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/22 0:19
 */
public class MySqrt {
    public int mySqrt2(int x) {
        return (int) Math.sqrt(x);
    }

    public int mySqrt(int x) {
        int l = 0;
        int r = 46341;
        while (r - l > 1) {
            int mid = (l + r) / 2;
            if (x >= mid * mid)
                l = mid;
            else
                r = mid;
        }
        return l;
    }

    public static void main(String[] args) {
        MySqrt mySqrt = new MySqrt();
        int result = mySqrt.mySqrt(2147395600);
        System.out.println(result);
    }
}
