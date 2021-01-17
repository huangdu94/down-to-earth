package work.huangdu.exploration.advanced_algorithms.dynamic;

import work.huangdu.question_bank.difficult.MaxProfit3;
import work.huangdu.question_bank.difficult.MaxProfit4;

/**
 * 309. 最佳买卖股票时机含冷冻期
 * 给定一个整数数组，其中第i个元素代表了第i天的股票价格 。
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/26 10:12
 * @see work.huangdu.exploration.primary_algorithms.array.MaxProfit
 * @see work.huangdu.exploration.primary_algorithms.dynamic.MaxProfit
 * @see work.huangdu.question_bank.medium.MaxProfit
 * @see MaxProfit3
 * @see MaxProfit4
 */
public class MaxProfit {
    /*
        找到第一个山谷。
        找到第一个山峰，和第二个山谷。
        1. 若山峰和山谷间隔一天以上，则冻结期不影响结果；
        2. 若山峰和山谷连续的，则需要抛弃山峰或山谷（选用次山峰或次山谷）；
        ...重复此过程直到最后一个山峰。
     */
/*    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int max = 0, i = 0, valley = 0, peak = Integer.MIN_VALUE;
        while (i < len - 1) {
            // 找山谷
            while (i < len - 1 && prices[i] >= prices[i + 1]) {
                i++;
            }
            // 如果找山谷找到了最后一天，则循环可以结束了
            if (i == len - 1) {
                break;
            }
            // 说明是第一个山谷，不做什么处理
            if (peak == Integer.MIN_VALUE) {
                valley = i;
            } else {
                // 代码执行到这里说明山谷非最后一天，且不是第一个山谷
                if (i - peak > 1) {
                    // 若山谷和上一个山峰间隔大于一天，则完全无视冻结期正常计算
                    max += (prices[peak] - prices[valley]);
                    valley = i;
                } else {
                    // 此时有三种选择，1. 上一个次山峰时卖出 2. 这一个次山谷时买入 3. 不买也不卖
                    // 判断标准是相比于不考虑冻结的时候，哪一个损失最小
                    int sell = Math.min(prices[peak] - prices[peak - 1], prices[i + 1] - prices[i]);
                    int unSell = prices[peak] - prices[i];
                    // 不卖的话什么都不用变，只有当卖了损失小于等于不卖的时候才选择卖
                    if (sell <= unSell) {
                        if (prices[peak] - prices[peak - 1] <= prices[i + 1] - prices[i]) {
                            max += (prices[peak - 1] - prices[valley]);
                            valley = i;
                        } else {
                            max += (prices[peak] - prices[valley]);
                            valley = i + 1;
                        }
                    }
                }
            }
            // 找山峰
            while (i < len - 1 && prices[i] <= prices[i + 1]) {
                i++;
            }
            peak = i;
            // 如果山峰等于山谷，意味着本次买卖没有意义，直接跳过
            if (peak == valley) {
                peak = Integer.MIN_VALUE;
            }
        }
        // 有没有找到过山峰，如果有的话最大利益要加上最后一段上坡
        if (peak != Integer.MIN_VALUE) {
            max += (prices[peak] - prices[valley]);
        }
        return max;
    }*/
    // 非动态规划的方法搞半天没搞出来，放弃了。。

    // 动态规划
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) {
            return 0;
        }
        int stock = -prices[0], cash = 0, freeze = 0;
        for (int i = 1; i < len; i++) {
            int newStock = Math.max(cash - prices[i], stock);
            int newCash = Math.max(cash, freeze);
            int newFreeze = stock + prices[i];
            stock = newStock;
            cash = newCash;
            freeze = newFreeze;
        }
        return Math.max(cash, freeze);
    }

    public static void main(String[] args) {
        MaxProfit maxProfit = new MaxProfit();
        int[] prices = {8, 6, 4, 3, 3, 2, 3, 5, 8, 3, 8, 2, 6};
        int max = maxProfit.maxProfit(prices);
        System.out.println(max);
    }
}
