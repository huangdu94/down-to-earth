package club.huangdu94.algorithm_difficult.arrstring;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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
    // 先排序再找(时间复杂度o(nlog n) 空间复杂度o(1))
    public int firstMissingPositive1(int[] nums) {
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

    // 暴力解(时间复杂度o(最坏o(n^2) 最好o(n)) 空间复杂度o(1))
    public int firstMissingPositive2(int[] nums) {
        int small = 1;
        boolean flag = true;
        while (flag) {
            flag = false;
            for (int n : nums)
                if (small == n) {
                    small++;
                    flag = true;
                    break;
                }
        }
        return small;
    }

    // 使用额外空间(时间复杂度o(n) 空间复杂度o(n))
    public int firstMissingPositive3(int[] nums) {
        int small = 1;
        Set<Integer> set = new HashSet<>();
        for (int n : nums)
            set.add(n);
        while (set.contains(small)) {
            small++;
        }
        return small;
    }

    // 不使用额外空间(时间复杂度o(n) 空间复杂度o(1))
    public int firstMissingPositive4(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++)
            if (nums[i] < 1)
                nums[i] = len + 1;
        for (int n : nums) {
            n = Math.abs(n);
            if (n <= len)
                if (nums[n - 1] > 0)
                    nums[n - 1] *= -1;
        }
        int i = 0;
        while (i < len) {
            if (nums[i] > 0)
                break;
            i++;
        }
        return i + 1;
    }
}
