package club.huangdu94.algorithm_middle.dynamic;

import java.util.Arrays;

/**
 * 零钱兑换
 * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回 -1。
 * 示例 1:
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/20 17:29
 */
public class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int target = 1; target <= amount; target++) {
            int min = -1;
            for (int coin : coins)
                if (target - coin >= 0 && dp[target - coin] != -1
                        && (min == -1 || min > dp[target - coin]))
                    min = dp[target - coin] + 1;
            dp[target] = min;
        }
        return dp[amount];
    }

    /**
     * 求最少硬币的组合方法,如果没有则返回-1
     * dp的i、j的意义
     * 1. dp的i表示前i种钱币
     * 2. j表示组合的目标金额
     * 3. dp[i][j]表示前i种钱币组合成j的硬币总数量 如果组合不了就存-1
     * 边界：
     * 1. dp[][0]=0 因为不管什么钱 组合成0 硬币总数都为0
     * 2. dp[0][>0]=-1(dp[0][0]=0 除外) 没有钱不管目标是多少都不可能完成所以为-1
     * 计算函数：
     * dp[i][j]=min(dp[i-1][target]+k) 其中 k>=0 && k<=j/coins[i-1];target=j-k*coins[i-1] min表示最小值
     * (如果遇到的dp值都为-1，则dp[i][j]也为-1)
     * 函数意思 前i种硬币组合成j的最小硬币数(以下dp为-1时直接值为-1)
     * 第i种硬币一个都没有情况下,前i-1种硬币组合的方法数 对应dp[i-1][j]+0
     * 第i种硬币有一个情况下,前i-1种硬币组合的方法数 对应dp[i-1][j-coins[i-1]]+1
     * ...
     * 直到k>j/coins[i-1]循环结束
     * 都为-1时则返回-1，否则返回不是-1中的最小值
     */
    public int coinChange4(int[] coins, int amount) {
        if (amount < 0 || coins == null)
            return -1;
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        Arrays.fill(dp[0], -1);
        dp[0][0] = 0;
        // i表示前n种硬币
        for (int i = 1; i <= len; i++) {
            int coin = coins[i - 1];
            for (int sum = 1; sum <= amount; sum++) {
                int min = -1;
                int target = sum, k = 0;
                while (target >= 0) {
                    int count = dp[i - 1][target];
                    if (count != -1)
                        min = min == -1 ? count + k : Math.min(min, count + k);
                    target = sum - ++k * coin;
                }
                dp[i][sum] = min;
            }
        }
        return dp[len][amount];
    }

    /**
     * 求总共有多少种组合方法
     * dp的i、j的意义
     * 1. dp的i表示前i种钱币
     * 2. j表示组合的目标金额
     * 3. dp[i][j]表示前i种钱币组合成j的方法数
     * 边界：
     * 1. dp[][0]=1 因为不管什么钱 组合成0 都只有一种可能性
     * 2. dp[0][>0]=0(dp[0][0]=1 除外) 没有钱不管目标是多少都不可能完成
     * 计算函数：
     * dp[i][j]=sum(dp[i-1][k]) 其中for(k=j;k>=0;k-=coins[i-1]) sum表示求和
     * 函数意思 前i种硬币组合成j的方法数
     * 等于第i种硬币一个都没有情况下,前i-1种硬币组合的方法数 对应dp[i-1][k]
     * 等于第i种硬币有一个情况下,前i-1种硬币组合的方法数 对应dp[i-1][k-=coins[i-1]]
     * ...
     * 直到k<0循环结束
     */
    public int coinChange3(int[] coins, int amount) {
        if (amount < 0 || coins == null)
            return -1;
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        dp[0][0] = 1;
        // i表示前n种硬币
        for (int i = 1; i <= len; i++) {
            dp[i][0] = 1;
            for (int sum = 1; sum <= amount; sum++) {
                for (int k = sum; k >= 0; k -= coins[i - 1])
                    dp[i][sum] += dp[i - 1][k];
            }
        }
        return dp[len][amount];
    }

    public int coinChange2(int[] coins, int amount) {
        if (amount < 0 || coins == null)
            return -1;
        int len = coins.length;
        if (len == 0)
            return -1;
        // 降序快速排序
        quickSort(coins, 0, len - 1);
        // 每种金币的数量
        int[] dp = new int[len];
        // 1.先贪心一遍
        int remain = greed(coins, dp, 0, len, amount);
        // 2.第二遍贪心 TODO
        if (remain != 0) {
            for (int kind = len - 2; kind >= 0; kind--) {
                int r = remain;
                while (--dp[kind] >= 0) {
                    r += coins[kind];
                    if (greed(coins, dp, kind, len, r) == 0)
                        return sum(dp, len);
                }
            }
        }
        // TODO
        if (remain == 0)
            return sum(dp, len);
        else
            return -1;
    }

    /**
     * 降序快速排序
     */
    private void quickSort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int i = l, j = r;
        int pivot = arr[l];
        while (i < j) {
            while (i < j && arr[j] <= pivot)
                j--;
            while (i < j && arr[i] >= pivot)
                i++;
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        arr[l] = arr[i];
        arr[i] = pivot;
        quickSort(arr, l, i - 1);
        quickSort(arr, i + 1, r);
    }

    /**
     * 求硬币总数
     *
     * @param dp    各面值硬币数
     * @param kinds 总共用到的面值数量
     * @return 硬币总数
     */
    private int sum(int[] dp, int kinds) {
        int result = 0;
        for (int i = 0; i < kinds; i++)
            result += dp[i];
        return result;
    }

    /**
     * 贪心算法
     *
     * @param coins  硬币面值数组
     * @param dp     各面值硬币数量数组(动态规划数组)
     * @param start  贪心起始
     * @param len    数组长度
     * @param amount 剩余钱(为0则目标完成)
     * @return 剩余钱
     */
    private int greed(int[] coins, int[] dp, int start, int len, int amount) {
        int kind = start;
        for (; kind < len; kind++) {
            if (amount == 0)
                return 0;
            dp[kind] = amount / coins[kind];
            amount %= coins[kind];
        }
        return amount;
    }

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        CoinChange coinChange = new CoinChange();
        int result = coinChange.coinChange(coins, amount);
        System.out.println(result);
    }
}
