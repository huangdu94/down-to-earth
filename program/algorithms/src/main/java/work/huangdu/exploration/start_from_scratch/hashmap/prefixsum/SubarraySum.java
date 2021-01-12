package work.huangdu.exploration.start_from_scratch.hashmap.prefixsum;

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为K的子数组
 * 给定一个整数数组和一个整数 k，你需要找到该数组中和为 k 的连续的子数组的个数。
 * 示例 1 :
 * 输入:nums = [1,1,1], k = 2
 * 输出: 2 , [1,1] 与 [1,1] 为两种不同的情况。
 * 说明 :
 * 数组的长度为 [1, 20,000]。
 * 数组中元素的范围是 [-1000, 1000] ，且整数 k 的范围是 [-1e7, 1e7]。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/11/8 15:24
 */
public class SubarraySum {
    // 暴力法 o(n^2)
    public int subarraySum2(int[] nums, int k) {
        int n = nums.length, count = 0;
        for (int i = 0; i < n; i++) {
            long sum = 0;
            for (int j = i; j < n; j++) {
                if ((sum += nums[j]) == k) {
                    count++;
                }
            }
        }
        return count;
    }

    public int subarraySum(int[] nums, int k) {
        int count = 0, sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        for (int num : nums) {
            sum += num;
            Integer c = map.get(sum - k);
            if (c != null) {
                count += c;
            }
            map.merge(sum, 1, Integer::sum);
        }
        return count;
    }
}
