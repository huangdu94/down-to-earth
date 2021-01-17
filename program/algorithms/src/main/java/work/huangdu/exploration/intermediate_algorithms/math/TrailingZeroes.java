package work.huangdu.exploration.intermediate_algorithms.math;

/**
 * 172. 阶乘后的零
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/22 0:15
 */
public class TrailingZeroes {
    public int trailingZeroes(int n) {
        int result = 0;
        while (n >= 5)
            result += (n /= 5);
        return result;
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 20; i++)
            System.out.printf("%s的阶乘是\t%s%n", i, factorial(i));
    }

    /**
     * 求n阶乘
     */
    private static long factorial(int n) {
        long result = 1;
        while (n > 1)
            result *= n--;
        return result;
    }
}
