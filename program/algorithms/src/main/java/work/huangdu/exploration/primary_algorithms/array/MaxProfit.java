package work.huangdu.exploration.primary_algorithms.array;

import work.huangdu.question_bank.difficult.MaxProfit3;
import work.huangdu.question_bank.difficult.MaxProfit4;

import java.util.HashSet;

/**
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组，它的第i 个元素是一支给定股票第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 * 提示：
 * 1 <= prices.length <= 3 * 10 ^ 4
 * 0 <= prices[i]<= 10 ^ 4
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/26 15:08
 * @see work.huangdu.exploration.primary_algorithms.dynamic.MaxProfit
 * @see work.huangdu.question_bank.medium.MaxProfit
 * @see work.huangdu.exploration.advanced_algorithms.dynamic.MaxProfit
 * @see MaxProfit3
 * @see MaxProfit4
 */
public class MaxProfit {
    public int maxProfit(int[] prices) {
        int profit = 0;
        int pre = prices[0];
        for (int i = 1; i < prices.length; i++) {
            int current = prices[i];
            if (current > pre) {
                profit += (current - pre);
            }
            pre = current;
        }
        return profit;
    }

    public int maxProfit2(int[] prices) {
        // 递归调用暴力计算最大利润
        return calculate(prices, 0);
    }

    public int calculate(int[] prices, int s) {
        new HashSet<>();
        // s表示当前开始计算的index,如果s>=prices.length,直接返回0
        if (s >= prices.length)
            return 0;
        // 返回值初始设置为0
        int max = 0;
        for (int start = s; start < prices.length; start++) {
            // 求出start为s的最大值
            int maxprofit = 0;
            for (int i = start + 1; i < prices.length; i++) {
                // 只要是后面的价格比前面的价格大都有可能是最大利益的买卖操作的一部分则进行操作
                if (prices[start] < prices[i]) {
                    int profit = calculate(prices, i + 1) + prices[i] - prices[start];
                    // 记录最大值
                    if (profit > maxprofit)
                        maxprofit = profit;
                }
            }
            // 记录最大值
            if (maxprofit > max)
                max = maxprofit;
        }
        return max;
    }

    // 动态规划
    public int maxProfit3(int[] prices) {
        int len = prices.length;
        if (len == 0)
            return 0;
        int[][] dp = new int[len][2];
        // 第一天
        // 持有现金
        dp[0][0] = 0;
        // 持有股票
        dp[0][1] = -prices[0];
        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][0] - prices[i], dp[i - 1][1]);
        }
        return dp[len - 1][0];
    }
}
