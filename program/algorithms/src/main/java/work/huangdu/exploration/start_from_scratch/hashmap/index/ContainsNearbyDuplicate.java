package work.huangdu.exploration.start_from_scratch.hashmap.index;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/14 11:33
 * @see work.huangdu.exploration.primary_algorithms.array.ContainsDuplicate
 * @see work.huangdu.exploration.start_from_scratch.hashmap.index.ContainsNearbyAlmostDuplicate
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

    // 暴力，时间复杂度o(n*k)，空间复杂度o(1)
    public boolean containsNearbyDuplicate3(int[] nums, int k) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            for (int j = Math.max(0, i - k); j < i; j++) {
                if (nums[i] == nums[j]) {
                    return true;
                }
            }
        }
        return false;
    }

    // 维护长度为k的set，时间复杂度o(n)，空间复杂度o(k)
    public boolean containsNearbyDuplicate2(int[] nums, int k) {
        if (k == 0) return false;
        int n = nums.length;
        Set<Integer> numSet = new HashSet<>(k);
        for (int i = 0; i < n; i++) {
            if (numSet.contains(nums[i])) {
                return true;
            }
            if (numSet.size() >= k) {
                numSet.remove(nums[i - k]);
            }
            numSet.add(nums[i]);
        }
        return false;
    }
}
