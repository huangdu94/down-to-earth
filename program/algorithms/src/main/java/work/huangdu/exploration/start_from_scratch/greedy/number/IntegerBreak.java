package work.huangdu.exploration.start_from_scratch.greedy.number;

import java.util.HashMap;
import java.util.Map;

/**
 * 343. 整数拆分
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * 示例 1:
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/11/19 13:04
 */
public class IntegerBreak {
    // 动态规划 可以写一写。 数学方法懒得看。
    private final Map<Integer, Integer> memo = new HashMap<>();

    public int integerBreak(int n) {
        if (n == 2) return 1;
        if (memo.containsKey(n)) return memo.get(n);
        int result = Integer.MIN_VALUE;
        for (int i = 1; i < n - 1; i++) {
            result = Math.max(result, i * Math.max(integerBreak(n - i), n - i));
        }
        memo.put(n, result);
        return result;
    }
}
