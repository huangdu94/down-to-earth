package work.huangdu.exploration.start_from_scratch.hashmap.statistics;

import work.huangdu.exploration.advanced_algorithms.array_string.FourSumCount;
import work.huangdu.exploration.intermediate_algorithms.array_string.ThreeSum;
import work.huangdu.exploration.start_from_scratch.hashmap.index.TwoSum2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 * 注意：
 * 答案中不可以包含重复的四元组。
 * 示例：
 * 给定数组 nums = [1, 0, -1, 0, -2, 2]，和 target = 0。
 * 满足要求的四元组集合为：
 * [
 * [-1,  0, 0, 1],
 * [-2, -1, 1, 2],
 * [-2,  0, 0, 2]
 * ]
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/10/5 12:07
 * @see work.huangdu.exploration.primary_algorithms.array.TwoSum
 * @see TwoSum2
 * @see ThreeSum
 * @see FourSumCount
 */
public class FourSum {
    // 时间复杂度 o(n^3)
    public List<List<Integer>> fourSum(int[] nums, int target) {
        if (nums.length < 4) return new ArrayList<>();
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int a = 0; a < n; a++) {
            if (a > 0 && nums[a] == nums[a - 1]) {
                continue;
            }
            int first = nums[a];
            for (int b = a + 1; b < n; b++) {
                if (b > a + 1 && nums[b] == nums[b - 1]) {
                    continue;
                }
                int second = nums[b];
                int t = target - (first + second);
                int i = b + 1, j = n - 1;
                while (i < j) {
                    if (nums[i] + nums[j] > t) {
                        j--;
                    } else if (nums[i] + nums[j] < t) {
                        i++;
                    } else {
                        res.add(Arrays.asList(first, second, nums[i], nums[j]));
                        i++;
                        while (i < j && nums[i] == nums[i - 1]) {
                            i++;
                        }
                        j--;
                        while (i < j && nums[j] == nums[j + 1]) {
                            j--;
                        }
                    }
                }
            }
        }
        return res;
    }

    public List<List<Integer>> fourSum2(int[] nums, int target) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int first = 0; first < n; first++) {
            if (first != 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            for (int second = first + 1; second < n; second++) {
                if (second != first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                int twoSum = nums[first] + nums[second];
                int third = second + 1, fourth = n - 1;
                while (third < fourth) {
                    int fourSum = twoSum + nums[third] + nums[fourth];
                    if (fourSum < target) {
                        third++;
                        while (third < fourth && nums[third] == nums[third - 1]) {
                            third++;
                        }
                    } else if (fourSum > target) {
                        fourth--;
                        while (third < fourth && nums[fourth] == nums[fourth + 1]) {
                            fourth--;
                        }
                    } else {
                        result.add(Arrays.asList(nums[first], nums[second], nums[third], nums[fourth]));
                        third++;
                        fourth--;
                        while (third < fourth && nums[third] == nums[third - 1]) {
                            third++;
                        }
                        while (third < fourth && nums[fourth] == nums[fourth + 1]) {
                            fourth--;
                        }
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FourSum fourSum = new FourSum();
        int[] nums = {0, 0, 0, 0};
        System.out.println(fourSum.fourSum(nums, 0));
    }
}
