package club.huangdu94.exploration.primary_algorithms.array;

import java.util.HashSet;

/**
 * @author duhuang@iflytek.com
 * @version 2020/7/26 15:08
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

    public int calculate(int prices[], int s) {
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
}
