package work.huangdu.exploration.start_from_scratch.double_pointer.head_tail;

import java.util.Arrays;

/**
 * 16. 最接近的三数之和
 * 给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。
 * 示例：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
 * 提示：
 * 3 <= nums.length <= 10^3
 * -10^3 <= nums[i] <= 10^3
 * -10^4 <= target <= 10^4
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/12/7 13:11
 */
public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        // 由nums[i]范围可知三个nums[i]之和不可能等于3001，故作为初始值
        int n = nums.length, result = 3001;
        Arrays.sort(nums);
        for (int first = 0; first < n; first++) {
            while (first != 0 && first < n && nums[first] == nums[first - 1]) {
                first++;
            }
            for (int second = first + 1; second < n; second++) {
                while (second != first + 1 && second < n - 1 && nums[second] == nums[second - 1]) {
                    second++;
                }
                int twoSum = nums[first] + nums[second];
                for (int third = n - 1; third > second; third--) {
                    while (third != n - 1 && third > second + 1 && nums[third] == nums[third + 1]) {
                        third--;
                    }
                    int threeSum = twoSum + nums[third];
                    if (threeSum == target) {
                        return threeSum;
                    }
                    int diff = Math.abs(result - target);
                    if (Math.abs(threeSum - target) < diff) {
                        result = threeSum;
                    } else if (threeSum < target) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        ThreeSumClosest tsc = new ThreeSumClosest();
        int[] nums = {0, 0, 0};
        System.out.println(tsc.threeSumClosest(nums, 1));
    }
}
