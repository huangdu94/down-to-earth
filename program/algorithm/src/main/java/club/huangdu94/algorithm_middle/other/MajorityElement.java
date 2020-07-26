package club.huangdu94.algorithm_middle.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 * 示例 1:
 * 输入: [3,2,3]
 * 输出: 3
 * 示例 2:
 * 输入: [2,2,1,1,1,2,2]
 * 输出: 2
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/22 0:24
 */
public class MajorityElement {
    // 投票算法最优
    public int majorityElement(int[] nums) {
        int result = 0;
        int count = 0;
        for (int num : nums) {
            if (count == 0) {
                result = num;
            }
            count += (num == result) ? 1 : -1;
        }
        return result;
    }

    public int majorityElement2(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int threshold = nums.length / 2;
        int result = nums[0];
        for (int num : nums) {
            Integer count = countMap.get(num);
            if (count == null) {
                countMap.put(num, 1);
            } else {
                if (++count > threshold) {
                    result = num;
                    break;
                }
                countMap.put(num, count);
            }
        }
        return result;
    }

    public int majorityElement3(int[] nums) {
        myQuickSort(nums, 0, nums.length - 1);
        return nums[nums.length / 2];
    }

    private void myQuickSort(int[] nums, int l, int r) {
        if (l >= r)
            return;
        int i = l, j = r;
        int pivot = nums[l];
        while (i < j) {
            while (i < j && nums[j] >= pivot)
                j--;
            while (i < j && nums[i] <= pivot)
                i++;
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[l] = nums[i];
        nums[i] = pivot;
        myQuickSort(nums, l, i - 1);
        myQuickSort(nums, i + 1, r);
    }
}
