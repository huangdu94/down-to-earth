package work.huangdu.exploration.start_from_scratch.double_pointer.homonymous_sliding_window;

/**
 * 643. 子数组最大平均数 I
 * 给定 n 个整数，找出平均数最大且长度为 k 的连续子数组，并输出该最大平均数。
 * 示例 1:
 * 输入: [1,12,-5,-6,50,3], k = 4
 * 输出: 12.75
 * 解释: 最大平均数 (12-5-6+50)/4 = 51/4 = 12.75
 * 注意:
 * 1 <= k <= n <= 30,000。
 * 所给数据范围 [-10,000，10,000]。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/12/15 12:30
 */
public class FindMaxAverage {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length, max = 0, sum = 0, i = 0;
        for (; i < k; i++) {
            sum += nums[i];
        }
        max = sum;
        for (; i < n; i++) {
            sum = sum - nums[i - k] + nums[i];
            if (max < sum) {
                max = sum;
            }
        }
        return 1.0 * max / k;
    }

    public static void main(String[] args) {
        System.out.println(new FindMaxAverage().findMaxAverage(new int[]{1, 12, -5, -6, 50, 3}, 4));
    }
}
