package work.huangdu.exploration.primary_algorithms.dynamic;

import work.huangdu.question_bank.difficult.MaxProfit3;
import work.huangdu.question_bank.difficult.MaxProfit4;

/**
 * 121. 买卖股票的最佳时机
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * 注意：你不能在买入股票前卖出股票。
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 * 注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/6/27 10:15
 * @see work.huangdu.exploration.primary_algorithms.array.MaxProfit
 * @see work.huangdu.question_bank.medium.MaxProfit
 * @see work.huangdu.exploration.advanced_algorithms.dynamic.MaxProfit
 * @see MaxProfit3
 * @see MaxProfit4
 */
public class MaxProfit {

    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int max = 0, min = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] < min) {
                min = prices[i];
            } else {
                if (prices[i] - min > max) {
                    max = prices[i] - min;
                }
            }
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
            dp[i][1] = Math.max(-prices[i], dp[i - 1][1]);
        }
        return dp[len - 1][0];
    }

    public int maxProfit(int[] prices) {
        int max = 0, i = 0, valley = Integer.MAX_VALUE;
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            if (valley > prices[i]) {
                valley = prices[i];
            }
            while (i < prices.length - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            if (max < prices[i] - valley) {
                max = prices[i] - valley;
            }
        }
        return max;
    }
}
