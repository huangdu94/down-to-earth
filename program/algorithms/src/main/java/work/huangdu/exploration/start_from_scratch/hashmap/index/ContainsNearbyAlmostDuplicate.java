package work.huangdu.exploration.start_from_scratch.hashmap.index;

import java.util.HashSet;
import java.util.Set;

/**
 * 220. 存在重复元素 III
 * 在整数数组 nums 中，是否存在两个下标 i 和 j，使得 nums [i] 和 nums [j] 的差的绝对值小于等于 t ，且满足 i 和 j 的差的绝对值也小于等于 ķ 。
 * 如果存在则返回 true，不存在返回 false。
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3, t = 0
 * 输出: true
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1, t = 2
 * 输出: true
 * 示例 3:
 * 输入: nums = [1,5,9,1,5,9], k = 2, t = 3
 * 输出: false
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/3 21:17
 * @see work.huangdu.exploration.primary_algorithms.array.ContainsDuplicate
 * @see work.huangdu.exploration.start_from_scratch.hashmap.index.ContainsNearbyAlmostDuplicate
 */
public class ContainsNearbyAlmostDuplicate {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if (k == 0) return false;
        int n = nums.length;
        Set<Integer> set = new HashSet<>(k);
        for (int i = 0; i < n; i++) {
            long bound1 = (long) nums[i] - t, bound2 = (long) nums[i] + t;
            long min = Math.min(bound1, bound2), max = Math.max(bound1, bound2);
            min = Math.max(Integer.MIN_VALUE, min);
            max = Math.min(Integer.MAX_VALUE, max);
            for (long j = min; j <= max; j++) {
                if (j >= Integer.MIN_VALUE && j <= Integer.MAX_VALUE) {
                    if (set.contains((int) j)) {
                        return true;
                    }
                }
            }
            if (set.size() == k) {
                set.remove(nums[i - k]);
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        ContainsNearbyAlmostDuplicate cnad = new ContainsNearbyAlmostDuplicate();
        int[] nums = {2147483647, -1, 2147483647};
        int k = 1;
        int t = 2147483647;
        System.out.println(cnad.containsNearbyAlmostDuplicate(nums, k, t));
    }
}
