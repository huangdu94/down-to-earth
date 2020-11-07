package work.huangdu.exploration.start_from_scratch.hashmap.prefixsum;

/**
 * 523. 连续的子数组和
 * 给定一个包含 非负数 的数组和一个目标 整数 k，编写一个函数来判断该数组是否含有连续的子数组，其大小至少为 2，且总和为 k 的倍数，即总和为 n*k，其中 n 也是一个整数。
 * 示例 1：
 * 输入：[23,2,4,6,7], k = 6
 * 输出：True
 * 解释：[2,4] 是一个大小为 2 的子数组，并且和为 6。
 * 示例 2：
 * 输入：[23,2,6,4,7], k = 6
 * 输出：True
 * 解释：[23,2,6,4,7]是大小为 5 的子数组，并且和为 42。
 * 说明：
 * 数组的长度不会超过 10,000 。
 * 你可以认为所有数字总和在 32 位有符号整数范围内。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/7 12:54
 */
public class CheckSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int sum = nums[i];
            for (int j = i + 1; j < n; j++) {
                sum += nums[j];
                if (sum == k || k != 0 && sum % k == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
