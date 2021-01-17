package work.huangdu.exploration.start_from_scratch.array.statistics;

import java.util.Arrays;

/**
 * 697. 数组的度
 * 给定一个非空且只包含非负数的整数数组 nums, 数组的度的定义是指数组里任一元素出现频数的最大值。
 * 你的任务是找到与 nums 拥有相同大小的度的最短连续子数组，返回其长度。
 * 示例 1:
 * 输入: [1, 2, 2, 3, 1]
 * 输出: 2
 * 解释:
 * 输入数组的度是2，因为元素1和2的出现频数最大，均为2.
 * 连续子数组里面拥有相同度的有如下所示:
 * [1, 2, 2, 3, 1], [1, 2, 2, 3], [2, 2, 3, 1], [1, 2, 2], [2, 2, 3], [2, 2]
 * 最短连续子数组[2, 2]的长度为2，所以返回2.
 * 示例 2:
 * 输入: [1,2,2,3,1,4,2]
 * 输出: 6
 * 注意:
 * nums.length 在1到50,000区间范围内。
 * nums[i] 是一个在0到49,999范围内的整数。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/14 15:31
 */
public class FindShortestSubArray {
    public static final int LEN = 50000;

    public int findShortestSubArray(int[] nums) {
        int len = nums.length, maxCount = 0, minLen = len + 1;
        int[] counts = new int[LEN], lefts = new int[LEN], rights = new int[LEN];
        Arrays.fill(lefts, -1);
        for (int i = 0; i < len; i++) {
            int num = nums[i];
            if (++counts[num] > maxCount) {
                maxCount = counts[num];
            }
            if (lefts[num] == -1) {
                lefts[num] = i;
            }
            rights[num] = i;
        }
        for (int num = 0; num < 50000; num++) {
            if (counts[num] == maxCount) {
                int length = rights[num] - lefts[num] + 1;
                if (minLen > length) {
                    minLen = length;
                }
            }
        }
        return minLen;
    }

    public static void main(String[] args) {
        FindShortestSubArray findShortestSubArray = new FindShortestSubArray();
        int[] nums = {1};
        System.out.println(findShortestSubArray.findShortestSubArray(nums));
    }
}
