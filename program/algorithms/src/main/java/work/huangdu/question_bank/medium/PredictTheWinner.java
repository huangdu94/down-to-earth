package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 486. 预测赢家
 * 给定一个表示分数的非负整数数组。 玩家 1 从数组任意一端拿取一个分数，随后玩家 2 继续从剩余数组任意一端拿取分数，然后玩家 1 拿，…… 。每次一个玩家只能拿取一个分数，分数被拿取之后不再可取。直到没有剩余分数可取时游戏结束。最终获得分数总和最多的玩家获胜。
 * 给定一个表示分数的数组，预测玩家1是否会成为赢家。你可以假设每个玩家的玩法都会使他的分数最大化。
 * 示例 1：
 * 输入：[1, 5, 2]
 * 输出：False
 * 解释：一开始，玩家1可以从1和2中进行选择。
 * 如果他选择 2（或者 1 ），那么玩家 2 可以从 1（或者 2 ）和 5 中进行选择。如果玩家 2 选择了 5 ，那么玩家 1 则只剩下 1（或者 2 ）可选。
 * 所以，玩家 1 的最终分数为 1 + 2 = 3，而玩家 2 为 5 。
 * 因此，玩家 1 永远不会成为赢家，返回 False 。
 * 示例 2：
 * 输入：[1, 5, 233, 7]
 * 输出：True
 * 解释：玩家 1 一开始选择 1 。然后玩家 2 必须从 5 和 7 中进行选择。无论玩家 2 选择了哪个，玩家 1 都可以选择 233 。
 * 最终，玩家 1（234 分）比玩家 2（12 分）获得更多的分数，所以返回 True，表示玩家 1 可以成为赢家。
 * 提示：
 * 1 <= 给定的数组长度 <= 20.
 * 数组里所有分数都为非负数且不会大于 10000000 。
 * 如果最终两个玩家的分数相等，那么玩家 1 仍为赢家。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/1 10:01
 */
public class PredictTheWinner {
    private int[][] memo;

    public boolean predictTheWinner(int[] nums) {
        // 偶数先手必胜
        if (nums.length % 2 == 0) return true;
        memo = new int[nums.length][nums.length];
        return helper(nums, 0, nums.length - 1) >= 0;
    }

    /**
     * 选择最优方案
     *
     * @param nums  非负整数数组
     * @param start 数组开始
     * @param end   数组结束
     * @return 选择最优方案后，到游戏结束与对手的分数差
     */
    private int helper(int[] nums, int start, int end) {
        if (start == end) {
            return nums[start];
        }
        if (memo[start][end] != 0) {
            return memo[start][end];
        }
        int head = nums[start] - helper(nums, start + 1, end);
        int tail = nums[end] - helper(nums, start, end - 1);
        return memo[start][end] = Math.max(head, tail);
    }

    public static void main(String[] args) {
        PredictTheWinner winner = new PredictTheWinner();
        int[] nums = {1, 5, 233, 7};
        System.out.println(winner.predictTheWinner(nums));
    }

    // 动态规划
    public boolean predictTheWinner2(int[] nums) {
        int len = nums.length;
        if (len % 2 == 0) return true;
        int[][] dp = new int[len][len];
        for (int i = 0; i < len; i++) {
            dp[i][i] = nums[i];
        }
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[i][j] = Math.max(nums[i] - dp[i + 1][j], nums[j] - dp[i][j - 1]);
            }
        }
        return dp[0][len - 1] >= 0;
    }

    // 动态规划 压缩空间
    public boolean predictTheWinner3(int[] nums) {
        int len = nums.length;
        if (len % 2 == 0) return true;
        int[] dp = Arrays.copyOf(nums, len);
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                dp[j] = Math.max(nums[i] - dp[j], nums[j] - dp[j - 1]);
            }
        }
        return dp[len - 1] >= 0;
    }
}
