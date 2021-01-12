package work.huangdu.question_bank.medium;

import work.huangdu.question_bank.difficult.MaxProfit3;
import work.huangdu.question_bank.difficult.MaxProfit4;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 给定一个整数数组 prices，其中第 i 个元素代表了第 i 天的股票价格 ；非负整数 fee 代表了交易股票的手续费用。
 * 你可以无限次地完成交易，但是你每笔交易都需要付手续费。如果你已经购买了一个股票，在卖出它之前你就不能再继续购买股票了。
 * 返回获得利润的最大值。
 * 注意：这里的一笔交易指买入持有并卖出股票的整个过程，每笔交易你只需要为支付一次手续费。
 * 示例 1:
 * 输入: prices = [1, 3, 2, 8, 4, 9], fee = 2
 * 输出: 8
 * 解释: 能够达到的最大利润:
 * 在此处买入 prices[0] = 1
 * 在此处卖出 prices[3] = 8
 * 在此处买入 prices[4] = 4
 * 在此处卖出 prices[5] = 9
 * 总利润: ((8 - 1) - 2) + ((9 - 4) - 2) = 8.
 * 注意:
 * 0 < prices.length <= 50000.
 * 0 < prices[i] < 50000.
 * 0 <= fee < 50000.
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/26 21:27
 * @see work.huangdu.exploration.primary_algorithms.array.MaxProfit
 * @see work.huangdu.exploration.primary_algorithms.dynamic.MaxProfit
 * @see work.huangdu.exploration.advanced_algorithms.dynamic.MaxProfit
 * @see MaxProfit3
 * @see MaxProfit4
 */
public class MaxProfit {
    public int maxProfit(int[] prices, int fee) {
        int cash = 0, stock = -prices[0], temp;
        for (int i = 1; i < prices.length; i++) {
            temp = Math.max(cash, stock + prices[i]);
            stock = Math.max(stock, cash - prices[i] - fee);
            cash = temp;
        }
        return cash;
    }
}
