package work.huangdu.exploration.start_from_scratch.hashmap.index;

import work.huangdu.exploration.advanced_algorithms.array_string.FourSumCount;
import work.huangdu.exploration.intermediate_algorithms.array_string.ThreeSum;
import work.huangdu.exploration.start_from_scratch.hashmap.statistics.FourSum;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照升序排列 的有序数组，找到两个数使得它们相加之和等于目标数。
 * 函数应该返回这两个下标值 index1 和 index2，其中 index1 必须小于 index2。
 * 说明:
 * 返回的下标值（index1 和 index2）不是从零开始的。
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 * 示例:
 * 输入: numbers = [2, 7, 11, 15], target = 9
 * 输出: [1,2]
 * 解释: 2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/11 0:19
 * @see work.huangdu.exploration.primary_algorithms.array.TwoSum
 * @see ThreeSum
 * @see FourSumCount
 * @see FourSum
 */
public class TwoSum2 {
    public int[] twoSum(int[] numbers, int target) {
        int[] res = new int[2];
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] > target)
                j--;
            else if (numbers[i] + numbers[j] < target)
                i++;
            else {
                res[0] = i + 1;
                res[1] = j + 1;
                return res;
            }
        }
        throw new RuntimeException();
    }

    public int[] twoSum2(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (true) {
            if (numbers[i] + numbers[j] > target) {
                j--;
            } else if (numbers[i] + numbers[j] < target) {
                i++;
            } else {
                return new int[]{i + 1, j + 1};
            }
        }
    }
}
