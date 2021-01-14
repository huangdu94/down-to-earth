package work.huangdu.question_bank.difficult;

/**
 * 327. 区间和的个数
 * 给定一个整数数组 nums，返回区间和在 [lower, upper] 之间的个数，包含 lower 和 upper。
 * 区间和 S(i, j) 表示在 nums 中，位置从 i 到 j 的元素之和，包含 i 和 j (i ≤ j)。
 * 说明:
 * 最直观的算法复杂度是 O(n2) ，请在此基础上优化你的算法。
 * 示例:
 * 输入: nums = [-2,5,-1], lower = -2, upper = 2,
 * 输出: 3
 * 解释: 3个区间分别是: [0,0], [2,2], [0,2]，它们表示的和分别为: -2, -1, 2。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/11/7 11:35
 */
public class CountRangeSum {
    // 暴力 求每个区间的区间和 时间复杂度o(n^2) 空间复杂度o(n^2)
    public int countRangeSum(int[] nums, int lower, int upper) {
        int n = nums.length;
        long[][] sums = new long[n][n];
        for (int i = 0; i < n; i++) {
            sums[i][i] = nums[i];
            for (int j = i + 1; j < n; j++) {
                sums[i][j] = sums[i][j - 1] + nums[j];
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (sums[i][j] <= upper && sums[i][j] >= lower) {
                    count++;
                }
            }
        }
        return count;
    }

    // 暴力 求每个区间的区间和 时间复杂度o(n^2) 空间复杂度o(1)
    public int countRangeSum2(int[] nums, int lower, int upper) {
        int n = nums.length, count = 0;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                sum += nums[j];
                if (sum <= upper && sum >= lower) {
                    count++;
                }
            }
        }
        return count;
    }

    // 归并排序-> nlog n
}
