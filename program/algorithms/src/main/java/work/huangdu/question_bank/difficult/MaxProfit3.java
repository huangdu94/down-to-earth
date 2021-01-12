package work.huangdu.question_bank.difficult;

import work.huangdu.exploration.advanced_algorithms.dynamic.MaxProfit;

/**
 * 123. 买卖股票的最佳时机 III
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 示例 1:
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/26 22:04
 * @see work.huangdu.exploration.primary_algorithms.array.MaxProfit
 * @see work.huangdu.exploration.primary_algorithms.dynamic.MaxProfit
 * @see work.huangdu.question_bank.medium.MaxProfit
 * @see MaxProfit
 * @see MaxProfit4
 */
public class MaxProfit3 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int cash1 = 0, stock1 = -prices[0], cash2 = 0, stock2 = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            cash1 = Math.max(stock1 + prices[i], cash1);
            stock1 = Math.max(stock1, -prices[i]);
            cash2 = Math.max(stock2 + prices[i], cash2);
            stock2 = Math.max(stock2, cash1 - prices[i]);
        }
        return cash2;
    }

/*    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        List<Integer> valleys = new ArrayList<>();
        List<Integer> peaks = new ArrayList<>();
        int len = prices.length, i = 0, max = 0;
        while (i < len - 1) {
            while (i < len - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            if (i == len - 1) {
                break;
            }
            valleys.add(prices[i]);
            while (i < len - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peaks.add(prices[i]);
        }
        int size = peaks.size();
        if (size <= 2) {
            for (int k = 0; k < size; k++) {
                max += (peaks.get(k) - valleys.get(k));
            }
        } else {
            int[] selectValley = new int[2];
            int[] selectPeak = new int[2];
            for (int k = 0; k < 2; k++) {
                selectValley[k] = valleys.get(k);
                selectPeak[k] = peaks.get(k);
            }
            *//*
     * 几种情况：
     * 1. 合并上坡：01和2; 0和12;
     * 2. 不合并：0和1; 0和2; 1和2;
     *//*
            for (int k = 2; k < size; k++) {
                int value0 = selectPeak[0] - selectValley[0];
                int value1 = selectPeak[1] - selectValley[1];
                int value2 = peaks.get(k) - valleys.get(k);
                int value01 = selectPeak[1] - selectValley[0];
                int value12 = peaks.get(k) - selectValley[1];
                max = value0 + value1;
                max = Math.max(max, value0 + value2);
                max = Math.max(max, value1 + value2);
                max = Math.max(max, value01 + value2);
                max = Math.max(max, value0 + value12);
                if (max == value0 + value2) {
                    selectValley[1] = valleys.get(k);
                    selectPeak[1] = peaks.get(k);
                } else if (max == value1 + value2) {
                    selectValley[0] = selectValley[1];
                    selectPeak[0] = selectPeak[1];
                    selectValley[1] = valleys.get(k);
                    selectPeak[1] = peaks.get(k);
                } else if (max == value01 + value2) {
                    selectPeak[0] = selectPeak[1];
                    selectValley[1] = valleys.get(k);
                    selectPeak[1] = peaks.get(k);
                } else if (max == value0 + value12) {
                    selectPeak[1] = peaks.get(k);
                }
            }
        }
        return max;
    }*/

    public static void main(String[] args) {
        MaxProfit3 maxProfit3 = new MaxProfit3();
        int[] prices = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        int max = maxProfit3.maxProfit(prices);
        System.out.println(max);
    }
}
