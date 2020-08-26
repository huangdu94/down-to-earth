package club.huangdu94.question_bank.difficult;

import java.util.ArrayList;
import java.util.List;

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
 * @author duhuang@iflytek.com
 * @version 2020/8/26 22:04
 * @see club.huangdu94.exploration.primary_algorithms.array.MaxProfit
 * @see club.huangdu94.exploration.primary_algorithms.dynamic.MaxProfit
 * @see club.huangdu94.question_bank.medium.MaxProfit
 * @see club.huangdu94.exploration.advanced_algorithms.dynamic.MaxProfit
 * @see club.huangdu94.question_bank.difficult.MaxProfit4
 */
public class MaxProfit3 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        List<Integer> valleys = new ArrayList<>();
        List<Integer> peaks = new ArrayList<>();
        int len = prices.length, i = 0, max = 0;
        while (i < len - 1) {
            while (i < len - 1 && prices[i] >= prices[i + 1]) {
                i++;
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
            for (int k = 2; k < size; k++) {
                int value0 = selectPeak[0] - selectValley[0];
                int value1 = selectPeak[1] - selectValley[1];
                int value2 = peaks.get(k) - valleys.get(k);
            }
        }
        return max;
    }
}
