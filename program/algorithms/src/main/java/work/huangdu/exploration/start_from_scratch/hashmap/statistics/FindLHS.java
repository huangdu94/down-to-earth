package work.huangdu.exploration.start_from_scratch.hashmap.statistics;

import java.util.HashMap;
import java.util.Map;

/**
 * 594. 最长和谐子序列
 * 和谐数组是指一个数组里元素的最大值和最小值之间的差别正好是1。
 * 现在，给定一个整数数组，你需要在所有可能的子序列中找到最长的和谐子序列的长度。
 * 示例 1:
 * 输入: [1,3,2,2,5,2,3,7]
 * 输出: 5
 * 原因: 最长的和谐数组是：[3,2,2,2,3].
 * 说明: 输入的数组长度最大不超过20,000.
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/6 16:57
 */
public class FindLHS {
    public int findLHS(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.merge(num, 1, Integer::sum);
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            int num = entry.getKey();
            int len = Math.max(counts.getOrDefault(num - 1, Integer.MIN_VALUE),
                    counts.getOrDefault(num + 1, Integer.MIN_VALUE))
                    + entry.getValue();
            if (max < len) {
                max = len;
            }
        }
        return max;
    }

    public int findLHS2(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.merge(num, 1, Integer::sum);
        }
        int max = 0;
        for (int num : counts.keySet()) {
            Integer count = counts.get(num + 1);
            if (count != null) {
                max = Math.max(count + counts.get(num), max);
            }
        }
        return max;
    }

    public static void main(String[] args) {
        FindLHS findLHS = new FindLHS();
        int[] nums = {1, 1, 1, 1};
        System.out.println(findLHS.findLHS(nums));
    }
}
