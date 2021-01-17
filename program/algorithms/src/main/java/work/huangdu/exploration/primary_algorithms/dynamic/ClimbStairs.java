package work.huangdu.exploration.primary_algorithms.dynamic;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/6/23 16:57
 */
public class ClimbStairs {
    public int climbStairs(int n) {
        if (n == 1)
            return 1;
        if (n == 2)
            return 2;
        // i,j
        // i代表所走到的位置,n-1代表走完了
        // j 0代表走一步 1代表走两步
        // 每一个位置所存的数字为用此种方法走到这里共有多少种方法
        int[][] dp = new int[n][2];
        // 赋值
        // 第一个台阶一步走到的方法只有一种
        // 两步走到的方法数为零
        dp[0][0] = 1;
        dp[0][1] = 0;
        // 走一步走到第二个位置的方法数为2
        dp[1][0] = 1;
        // 两步走到第一个位置的方法为1
        dp[1][1] = 1;
        for (int i = 2; i < n; i++) {
            dp[i][0] = dp[i - 1][0] + dp[i - 1][1];
            dp[i][1] = dp[i - 2][0] + dp[i - 2][1];
        }
        return dp[n - 1][0] + dp[n - 1][1];
    }

    public int climbStairs2(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }

    public int[][] pow(int[][] a, int n) {
        int[][] ret = {{1, 0}, {0, 1}};
        while (n > 0) {
            if ((n & 1) == 1) {
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }
}
