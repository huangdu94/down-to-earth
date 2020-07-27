package club.huangdu94.algorithm_difficult.arrstring;

import java.util.Arrays;

/**
 * 缺失的第一个正数
 * 给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。
 * 示例 1:
 * 输入: [1,2,0]
 * 输出: 3
 * 示例 2:
 * 输入: [3,4,-1,1]
 * 输出: 2
 * 示例 3:
 * 输入: [7,8,9,11,12]
 * 输出: 1
 * 提示：
 * 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/27 14:28
 */
public class FirstMissingPositive {
    // 先排序再找(时间复杂度o(nlog n))
    public int firstMissingPositive(int[] nums) {
        Arrays.sort(nums);
        int small = 1;
        int i = 0;
        while (i < nums.length && nums[i] < 1)
            i++;
        while (i < nums.length) {
            if (small < nums[i])
                return small;
            while (i + 1 < nums.length && nums[i + 1] == nums[i])
                i++;
            small++;
            i++;
        }
        return small;
    }
}
