package work.huangdu.exploration.advanced_algorithms.array_string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 最长连续序列
 * 给定一个未排序的整数数组，找出最长连续序列的长度。
 * 要求算法的时间复杂度为 O(n)。
 * 示例:
 * 输入: [100, 4, 200, 1, 3, 2]
 * 输出: 4
 * 解释: 最长连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/27 19:13
 */
public class LongestConsecutive {
    // 1.HashMap法 时间复杂度o(n) 空间复杂度o(n)
    public int longestConsecutive(int[] nums) {
        // key: 每个连续序列的首数字
        Map<Integer, int[]> startMap = new HashMap<>();
        // key: 每个连续序列的尾数字
        Map<Integer, int[]> endMap = new HashMap<>();
        // HashSet 重复数字跳过
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            if (numSet.contains(num))
                continue;
            numSet.add(num);
            // 1.看看有没有可以接到尾部的序列
            int[] consecutive1 = endMap.remove(num - 1);
            // 2.看看有没有可以接到首部的序列
            int[] consecutive2 = startMap.remove(num + 1);
            if (consecutive1 == null && consecutive2 == null) {
                int[] consecutive = {num, num};
                startMap.put(num, consecutive);
                endMap.put(num, consecutive);
            } else if (consecutive2 == null) {
                consecutive1[1] = num;
                endMap.put(num, consecutive1);
            } else if (consecutive1 == null) {
                consecutive2[0] = num;
                startMap.put(num, consecutive2);
            } else {
                int[] consecutive = {consecutive1[0], consecutive2[1]};
                startMap.put(consecutive1[0], consecutive);
                endMap.put(consecutive2[1], consecutive);
            }
        }
        int maxLen = 0;
        for (int[] consecutive : startMap.values()) {
            int len = consecutive[1] - consecutive[0] + 1;
            if (maxLen < len)
                maxLen = len;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        LongestConsecutive longestConsecutive = new LongestConsecutive();
        int[] nums = {-7, -1, 3, -9, -4, 7, -3, 2, 4, 9, 4, -9, 8, -7, 5, -1, -7};
        System.out.println(longestConsecutive.longestConsecutive(nums));
    }
}
