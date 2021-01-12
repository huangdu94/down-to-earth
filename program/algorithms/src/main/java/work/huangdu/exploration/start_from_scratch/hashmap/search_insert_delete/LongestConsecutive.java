package work.huangdu.exploration.start_from_scratch.hashmap.search_insert_delete;

import java.util.*;

/**
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/10/31 12:33
 * @see work.huangdu.exploration.advanced_algorithms.array_string.LongestConsecutive
 */
public class LongestConsecutive {
    public int longestConsecutive(int[] nums) {
        int longest = 0;
        Arrays.sort(nums);
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int len = map.getOrDefault(num - 1, 0) + 1;
            map.put(num, len);
            longest = Math.max(longest, len);
        }
        return longest;
    }

    public int longestConsecutive2(int[] nums) {
        int longest = 0;
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        for (int num : nums) {
            if (!set.contains(num - 1)) {
                int cur = num, len = 1;
                while (set.contains(cur + 1)) {
                    cur++;
                    len++;
                }
                longest = Math.max(longest, len);
            }
        }
        return longest;
    }

    public static void main(String[] args) {
        int[] nums = {100, 4, 200, 1, 3, 2};
        LongestConsecutive lc = new LongestConsecutive();
        System.out.println(lc.longestConsecutive(nums));
    }
}
