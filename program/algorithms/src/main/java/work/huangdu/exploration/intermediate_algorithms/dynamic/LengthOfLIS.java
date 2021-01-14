package work.huangdu.exploration.intermediate_algorithms.dynamic;

/**
 * Longest Increasing Subsequence
 * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
 * 示例:
 * 输入: [10,9,2,5,3,7,101,18]
 * 输出: 4
 * 解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
 * 说明:
 * 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
 * 你算法的时间复杂度应该为 O(n2) 。
 * 进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗?
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/20 17:31
 */
public class LengthOfLIS {

    /**
     * 最长上升子序列
     */
    public int lengthOfLIS(int[] nums) {
        if (nums == null)
            return 0;
        int len = nums.length;
        if (len < 2)
            return len;
        int[] dp = new int[len];
        dp[0] = nums[0];
        int lis = 1;
        for (int i = 1; i < len; i++) {
            int cur = nums[i];
            if (cur > dp[lis - 1])
                dp[lis++] = cur;
            else if (cur < dp[lis - 1]) {
                /*int k;
                for (k = lis - 2; k >= 0 && dp[k] >= cur; k--) ;*/
                int k = findK(dp, 0, lis - 1, cur);
                dp[k] = cur;
            }
        }
        return lis;
    }

    /**
     * 二分法找到k的位置(k的位置保证 dp[k] < cur < dp[k+1])
     */
    private int findK(int[] dp, int l, int r, int cur) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (dp[mid] < cur)
            return findK(dp, mid + 1, r, cur);
        else
            return findK(dp, l, mid, cur);
    }

    /**
     * 最长上升连续子序列
     */
    public int lengthOfLICS(int[] nums) {
        if (nums == null || nums.length == 0)
            return 0;
        int lisLen = 1;
        int maxLisLen = lisLen;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                lisLen++;
            } else {
                maxLisLen = Math.max(maxLisLen, lisLen);
                lisLen = 1;
            }
        }
        return Math.max(maxLisLen, lisLen);
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLIS().lengthOfLIS(new int[]{10, 9, 2, 5, 3, 4}));
    }
}
