package work.huangdu.question_bank.easy;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 1365. 有多少小于当前数字的数字
 * 给你一个数组 nums，对于其中每个元素 nums[i]，请你统计数组中比它小的所有数字的数目。
 * 换而言之，对于每个 nums[i] 你必须计算出有效的 j 的数量，其中 j 满足 j != i 且 nums[j] < nums[i] 。
 * 以数组形式返回答案。
 * 示例 1：
 * 输入：nums = [8,1,2,2,3]
 * 输出：[4,0,1,1,3]
 * 解释：
 * 对于 nums[0]=8 存在四个比它小的数字：（1，2，2 和 3）。
 * 对于 nums[1]=1 不存在比它小的数字。
 * 对于 nums[2]=2 存在一个比它小的数字：（1）。
 * 对于 nums[3]=2 存在一个比它小的数字：（1）。
 * 对于 nums[4]=3 存在三个比它小的数字：（1，2 和 2）。
 * 示例 2：
 * 输入：nums = [6,5,4,8]
 * 输出：[2,1,0,3]
 * 示例 3：
 * 输入：nums = [7,7,7,7]
 * 输出：[0,0,0,0]
 * 提示：
 * 2 <= nums.length <= 500
 * 0 <= nums[i] <= 100
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/10/26 1:28
 */
public class SmallerNumbersThanCurrent {
    // 暴力 时间复杂度o(n^2) 空间复杂度o(1)
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            int count = 0;
            for (int num : nums) {
                if (nums[i] > num) {
                    count++;
                }
            }
            res[i] = count;
        }
        return res;
    }

    // 排序 时间复杂度o(n log n) 空间复杂度o(n)
    public int[] smallerNumbersThanCurrent2(int[] nums) {
        int n = nums.length, pre = 0;
        int[] res = new int[n];
        int[][] numsAndIndex = new int[n][2];
        for (int i = 0; i < n; i++) {
            numsAndIndex[i][0] = nums[i];
            numsAndIndex[i][1] = i;
        }
        Arrays.sort(numsAndIndex, Comparator.comparingInt(o -> o[0]));
        for (int i = 1; i < n; i++) {
            if (numsAndIndex[i][0] != numsAndIndex[i - 1][0]) {
                pre = i;
            }
            res[numsAndIndex[i][1]] = pre;
        }
        return res;
    }

    // 计数 时间复杂度o(n+k) 空间复杂度o(k)
    public int[] smallerNumbersThanCurrent3(int[] nums) {
        int n = nums.length;
        int[] res = new int[n], counts = new int[101];
        for (int num : nums) {
            counts[num]++;
        }
        for (int i = 1; i < 101; i++) {
            counts[i] += counts[i - 1];
        }
        for (int i = 0; i < n; i++) {
            res[i] = nums[i] == 0 ? 0 : counts[nums[i] - 1];
        }
        return res;
    }
}
