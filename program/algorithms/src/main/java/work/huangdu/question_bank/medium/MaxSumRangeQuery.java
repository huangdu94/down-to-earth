package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 1589. 所有排列中的最大和
 * 有一个整数数组 nums ，和一个查询数组 requests ，其中 requests[i] = [starti, endi] 。第 i 个查询求 nums[starti] + nums[starti + 1] + ... + nums[endi - 1] + nums[endi] 的结果 ，starti 和 endi 数组索引都是 从 0 开始 的。
 * 你可以任意排列 nums 中的数字，请你返回所有查询结果之和的最大值。
 * 由于答案可能会很大，请你将它对 10^9 + 7 取余 后返回。
 * 示例 1：
 * 输入：nums = [1,2,3,4,5], requests = [[1,3],[0,1]]
 * 输出：19
 * 解释：一个可行的 nums 排列为 [2,1,3,4,5]，并有如下结果：
 * requests[0] -> nums[1] + nums[2] + nums[3] = 1 + 3 + 4 = 8
 * requests[1] -> nums[0] + nums[1] = 2 + 1 = 3
 * 总和为：8 + 3 = 11。
 * 一个总和更大的排列为 [3,5,4,2,1]，并有如下结果：
 * requests[0] -> nums[1] + nums[2] + nums[3] = 5 + 4 + 2 = 11
 * requests[1] -> nums[0] + nums[1] = 3 + 5  = 8
 * 总和为： 11 + 8 = 19，这个方案是所有排列中查询之和最大的结果。
 * 示例 2：
 * 输入：nums = [1,2,3,4,5,6], requests = [[0,1]]
 * 输出：11
 * 解释：一个总和最大的排列为 [6,5,4,3,2,1] ，查询和为 [11]。
 * 示例 3：
 * 输入：nums = [1,2,3,4,5,10], requests = [[0,2],[1,3],[1,1]]
 * 输出：47
 * 解释：一个和最大的排列为 [4,10,5,3,2,1] ，查询结果分别为 [19,18,10]。
 * 提示：
 * n == nums.length
 * 1 <= n <= 10^5
 * 0 <= nums[i] <= 10^5
 * 1 <= requests.length <= 10^5
 * requests[i].length == 2
 * 0 <= starti <= endi < n
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/22 12:57
 */
public class MaxSumRangeQuery {
    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int len = nums.length;
        int[] counts = new int[len + 1];
        for (int[] request : requests) {
            counts[request[0]]++;
            counts[request[1] + 1]--;
        }
        for (int i = 1; i < len; i++) {
            counts[i] += counts[i - 1];
        }
        long res = 0;
        Arrays.sort(counts);
        Arrays.sort(nums);
        for (int i = len - 1; i >= 0 && counts[i + 1] != 0; i--) {
            res += ((long) nums[i]) * counts[i + 1];
        }
        return (int) (res % 1000000007);
    }

    public static void main(String[] args) {
        MaxSumRangeQuery query = new MaxSumRangeQuery();
        int[] nums = {1, 2, 3, 4, 5};
        int[][] requests = {{1, 3}, {0, 1}};
        System.out.println(query.maxSumRangeQuery(nums, requests));
    }
}
