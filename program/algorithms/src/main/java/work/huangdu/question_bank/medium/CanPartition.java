package work.huangdu.question_bank.medium;

/**
 * 416. 分割等和子集
 * 给定一个只包含正整数的非空数组。是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
 * 注意:
 * 每个数组中的元素不会超过 100
 * 数组的大小不会超过 200
 * 示例 1:
 * 输入: [1, 5, 11, 5]
 * 输出: true
 * 解释: 数组可以分割成 [1, 5, 5] 和 [11].
 * 示例 2:
 * 输入: [1, 2, 3, 5]
 * 输出: false
 * 解释: 数组不能分割成两个元素和相等的子集.
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/10/11 20:54
 */
public class CanPartition {
    public boolean canPartition(int[] nums) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            sum += num;
            if (num > max) {
                max = num;
            }
        }
        // 1. 如果nums的元素之和为奇数，则肯定不能分成两个和相等的子集
        if ((sum & 1) == 1) return false;
        int target = sum / 2;
        // 2. 如果数组中最大的数已经超过了sum/2，这肯定无法分成两个和相等的子集
        if (max > target) return false;
        // 3. 剩下的情况需要动态规划
        boolean[] dp = new boolean[target + 1];
        dp[0] = true;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] |= dp[j - num];
            }
        }
        return dp[target];
    }
}
