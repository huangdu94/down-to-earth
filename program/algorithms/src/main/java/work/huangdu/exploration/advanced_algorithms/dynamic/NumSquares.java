package work.huangdu.exploration.advanced_algorithms.dynamic;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * 完全平方数
 * 给定正整数n，找到若干个完全平方数（比如1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * 示例1:
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/28 12:37
 */
public class NumSquares {
    private Set<Integer> squareSet;

    /**
     * 程序入口
     */
    public int numSquares(int n) {
        // 初始化set
        initSquareSet(n);
        // return math(n);
        // return violence(n);
        // return violence(n, new int[n + 1]);
        // return dynamic(n);
        // return greed(n);
        return greedBfs(n);
    }

    /**
     * 初始化set,set内为小于等于n的所有完全平方数的集合（不包括0）
     */
    private void initSquareSet(int n) {
        // squareSet = new HashSet<>();
        squareSet = new LinkedHashSet<>();
        int i = 1, max = (int) Math.sqrt(n) + 1;
        while (i <= max) {
            squareSet.add(i * i);
            i++;
        }
    }

    /**
     * 数学定理：
     * 1. 对每个正整数一定可表为四个平方数之和
     * 2. 当n是形如4^a*(8k+7) (a≥0, k≥0)的正整数时, n不能表为三个整数的平方和
     * 时间复杂度 o(n^0.5)
     * 空间复杂度 o(1)
     */
    private int math(int n) {
        // 1. 如果n本身就是完全平方数，则返回1
        if (squareSet.contains(n)) return 1;
        // 2. 查看n是否为4^a*(8k+7) (a≥0, k≥0)的形式，如果是的话则返回4
        int i = n;
        while (i % 4 == 0) i /= 4;
        if (i % 8 == 7) return 4;
        // 3. 查看n是否为两个完全平方数的和，如果是的话返回2，否则返回3
        for (int square : squareSet) {
            if (squareSet.contains(n - square)) {
                return 2;
            }
        }
        return 3;
    }

    /**
     * 暴力求解(超出时间限制)
     */
    private int violence(int n) {
        if (squareSet.contains(n)) return 1;
        int min = Integer.MAX_VALUE;
        for (int square : squareSet) {
            if (square > n) break;
            int res = violence(n - square) + 1;
            if (min > res) min = res;
        }
        return min;
    }

    /**
     * 暴力求解(带备忘录的版本)通过
     */
    private int violence(int n, int[] memo) {
        if (squareSet.contains(n)) return 1;
        if (memo[n] != 0) return memo[n];
        int min = Integer.MAX_VALUE;
        for (int square : squareSet) {
            if (square > n) break;
            int res = violence(n - square, memo) + 1;
            if (min > res) min = res;
        }
        memo[n] = min;
        return min;
    }

    /**
     * 动态规划
     * 时间复杂度 o(n * n^0.5)
     * 空间复杂度 o(n)
     */
    private int dynamic(int n) {
        if (n == Integer.MAX_VALUE) return 4;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            int min = Integer.MAX_VALUE;
            for (int square : squareSet) {
                if (square > i) break;
                if (min > dp[i - square]) {
                    min = dp[i - square];
                }
            }
            dp[i] = min + 1;
        }
        return dp[n];
    }

    /**
     * 贪心 递归
     */
    private int greed(int n) {
        int count = 1;
        int[][] memo = new int[n + 1][5];
        while (!recursion(n, count, memo)) {
            count++;
        }
        return count;
    }

    /**
     * 贪心 递归 备忘录
     */
    private boolean recursion(int n, int count, int[][] memo) {
        if (count == 1) return squareSet.contains(n);
        if (memo[n][count] != 0) return memo[n][count] == 1;
        for (int square : squareSet) {
            if (square > n) break;
            if (recursion(n - square, count - 1, memo)) {
                memo[n][count] = 1;
                return true;
            }
        }
        memo[n][count] = -1;
        return false;
    }

    /**
     * 贪心 BFS
     */
    private int greedBfs(int n) {
        int count = 0;
        Set<Integer> set = new HashSet<>(), nextSet;
        set.add(n);
        while (!set.isEmpty()) {
            count++;
            nextSet = new HashSet<>();
            for (int i : set) {
                if (squareSet.contains(i)) {
                    return count;
                }
                for (int square : squareSet) {
                    if (square > i) break;
                    nextSet.add(i - square);
                }
            }
            set = nextSet;
        }
        return count;
    }

    public static void main(String[] args) {
        NumSquares numSquares = new NumSquares();
        System.out.println(numSquares.numSquares(7));
    }
}
