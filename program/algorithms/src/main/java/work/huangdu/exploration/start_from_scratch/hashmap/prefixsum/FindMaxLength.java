package work.huangdu.exploration.start_from_scratch.hashmap.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * 525. 连续数组
 * 给定一个二进制数组, 找到含有相同数量的 0 和 1 的最长连续子数组（的长度）。
 * 示例 1:
 * 输入: [0,1]
 * 输出: 2
 * 说明: [0, 1] 是具有相同数量0和1的最长连续子数组。
 * 示例 2:
 * 输入: [0,1,0]
 * 输出: 2
 * 说明: [0, 1] (或 [1, 0]) 是具有相同数量0和1的最长连续子数组。
 * 注意: 给定的二进制数组的长度不会超过50000。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/11/8 17:59
 */
public class FindMaxLength {
    // 遇到1加1，遇到0减1
    public int findMaxLength(int[] nums) {
        int n = nums.length, difference = 0, diff = 0;
        for (int num : nums) {
            difference += num == 0 ? -1 : 1;
        }
        if (difference == 0) return n;
        Map<Integer, Integer> head = new HashMap<>(), tail = new HashMap<>();
        head.put(0, -1);
        tail.put(0, n);
        for (int i = 0; i < n && diff != difference; i++) {
            diff += nums[i] == 0 ? -1 : 1;
            if (!head.containsKey(diff)) {
                head.put(diff, i);
            }
        }
        diff = 0;
        for (int i = n - 1; i >= 0 && diff != difference; i--) {
            diff += nums[i] == 0 ? -1 : 1;
            if (!tail.containsKey(diff)) {
                tail.put(diff, i);
            }
        }
        int max = 0;
        for (int d : head.keySet()) {
            int h = head.get(d);
            Integer t = tail.get(difference - d);
            if (t != null) {
                if (max < t - h - 1) {
                    max = t - h - 1;
                }
            }
        }
        return max;
    }
}
