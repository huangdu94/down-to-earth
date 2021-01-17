package work.huangdu.question_bank.difficult;

import work.huangdu.exploration.advanced_algorithms.dynamic.MaxProfit;

import java.util.Arrays;

/**
 * 188. 买卖股票的最佳时机 IV
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2:
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/26 22:04
 * @see work.huangdu.exploration.primary_algorithms.array.MaxProfit
 * @see work.huangdu.exploration.primary_algorithms.dynamic.MaxProfit
 * @see work.huangdu.question_bank.medium.MaxProfit
 * @see MaxProfit
 * @see MaxProfit3
 */
public class MaxProfit4 {
    public int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length < 2 || k <= 0) return 0;
        int len = prices.length;
        if (k > len / 2) {
            int cash = 0, stock = -prices[0];
            for (int i = 1; i < len; i++) {
                cash = Math.max(cash, stock + prices[i]);
                stock = Math.max(stock, cash - prices[i]);
            }
            return cash;
        }
        int[] cash = new int[k], stock = new int[k];
        Arrays.fill(stock, -prices[0]);
        for (int i = 1; i < len; i++) {
            cash[0] = Math.max(cash[0], stock[0] + prices[i]);
            stock[0] = Math.max(stock[0], -prices[i]);
            for (int j = 1; j < k; j++) {
                cash[j] = Math.max(cash[j], stock[j] + prices[i]);
                stock[j] = Math.max(stock[j], cash[j - 1] - prices[i]);
            }
        }
        return cash[k - 1];
    }

    public static void main(String[] args) {
        MaxProfit4 maxProfit4 = new MaxProfit4();
        int[] prices = {2, 1, 4};
        int k = 2;
        int profit = maxProfit4.maxProfit(k, prices);
        System.out.println(profit);
    }
}
