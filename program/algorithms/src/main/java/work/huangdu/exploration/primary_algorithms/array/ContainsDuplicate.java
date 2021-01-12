package work.huangdu.exploration.primary_algorithms.array;

import java.util.HashSet;
import java.util.Set;

/**
 * 217. 存在重复元素
 * 给定一个整数数组，判断是否存在重复元素。
 * 如果任意一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * 示例 1:
 * 输入: [1,2,3,1]
 * 输出: true
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: false
 * 示例 3:
 * 输入: [1,1,1,3,3,4,3,2,4,2]
 * 输出: true
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/7/26 15:15
 * @see work.huangdu.exploration.start_from_scratch.hashmap.index.ContainsNearbyDuplicate
 * @see work.huangdu.exploration.start_from_scratch.hashmap.index.ContainsNearbyAlmostDuplicate
 */
public class ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        if (nums.length <= 1)
            return false;
        Set<Integer> intSet = new HashSet<>(nums.length);
        for (int i : nums) {
            if (intSet.contains(i)) {
                return true;
            }
            intSet.add(i);
        }
        return false;
    }
}
