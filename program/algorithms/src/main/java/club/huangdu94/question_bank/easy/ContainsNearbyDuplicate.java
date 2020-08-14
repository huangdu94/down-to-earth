package club.huangdu94.question_bank.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/14 11:33
 */
public class ContainsNearbyDuplicate {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        if (k <= 0 || nums == null || nums.length <= 1) return false;
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            Integer index = map.get(nums[i]);
            if (index == null) {
                map.put(nums[i], i);
            } else {
                if (i - index <= k) {
                    return true;
                } else {
                    map.put(nums[i], i);
                }
            }
        }
        return false;
    }
}
