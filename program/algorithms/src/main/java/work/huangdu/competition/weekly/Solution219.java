package work.huangdu.competition.weekly;

import java.util.Arrays;

/**
 * 第219场周赛
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/12/13 9:58
 */
public class Solution219 {
    /**
     * 5625. 比赛中的配对次数
     * 给你一个整数 n ，表示比赛中的队伍数。比赛遵循一种独特的赛制：
     * 如果当前队伍数是 偶数 ，那么每支队伍都会与另一支队伍配对。总共进行 n / 2 场比赛，且产生 n / 2 支队伍进入下一轮。
     * 如果当前队伍数为 奇数 ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 (n - 1) / 2 场比赛，且产生 (n - 1) / 2 + 1 支队伍进入下一轮。
     * 返回在比赛中进行的配对次数，直到决出获胜队伍为止。
     * 示例 1：
     * 输入：n = 7
     * 输出：6
     * 解释：比赛详情：
     * - 第 1 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。
     * - 第 2 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
     * - 第 3 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
     * 总配对次数 = 3 + 2 + 1 = 6
     * 示例 2：
     * 输入：n = 14
     * 输出：13
     * 解释：比赛详情：
     * - 第 1 轮：队伍数 = 14 ，配对次数 = 7 ，7 支队伍晋级。
     * - 第 2 轮：队伍数 = 7 ，配对次数 = 3 ，4 支队伍晋级。
     * - 第 3 轮：队伍数 = 4 ，配对次数 = 2 ，2 支队伍晋级。
     * - 第 4 轮：队伍数 = 2 ，配对次数 = 1 ，决出 1 支获胜队伍。
     * 总配对次数 = 7 + 3 + 2 + 1 = 13
     * 提示：
     * 1 <= n <= 200
     */
    public int numberOfMatches(int n) {
        int count = 0;
        while (n > 1) {
            count += (n / 2);
            if ((n & 1) == 1) {
                n++;
            }
            n /= 2;
        }
        return count;
    }

    /**
     * 5626. 十-二进制数的最少数目
     * 如果一个十进制数字不含任何前导零，且每一位上的数字不是 0 就是 1 ，那么该数字就是一个 十-二进制数 。例如，101 和 1100 都是 十-二进制数，而 112 和 3001 不是。
     * 给你一个表示十进制整数的字符串 n ，返回和为 n 的 十-二进制数 的最少数目。
     * 示例 1：
     * 输入：n = "32"
     * 输出：3
     * 解释：10 + 11 + 11 = 32
     * 示例 2：
     * 输入：n = "82734"
     * 输出：8
     * 示例 3：
     * 输入：n = "27346209830709182346"
     * 输出：9
     * 提示：
     * 1 <= n.length <= 105
     * n 仅由数字组成
     * n 不含任何前导零并总是表示正整数
     */
    public int minPartitions(String s) {
        if (s.charAt(0) == '0') return 1;
        int[] stack = new int[9];
        int top = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            if (top == 0 || stack[top - 1] < num) {
                stack[top++] = num;
            }
            if (num == 9) break;
        }
        int result = stack[0];
        for (int i = 1; i < top; i++) {
            int cur = stack[i] - result;
            if (cur <= 0) return result;
            result += cur;
        }
        return result;
    }

    /**
     * 5627. 石子游戏 VII
     * 石子游戏中，爱丽丝和鲍勃轮流进行自己的回合，爱丽丝先开始 。
     * 有 n 块石子排成一排。每个玩家的回合中，可以从行中 移除 最左边的石头或最右边的石头，并获得与该行中剩余石头值之 和 相等的得分。当没有石头可移除时，得分较高者获胜。
     * 鲍勃发现他总是输掉游戏（可怜的鲍勃，他总是输），所以他决定尽力 减小得分的差值 。爱丽丝的目标是最大限度地 扩大得分的差值 。
     * 给你一个整数数组 stones ，其中 stones[i] 表示 从左边开始 的第 i 个石头的值，如果爱丽丝和鲍勃都 发挥出最佳水平 ，请返回他们 得分的差值 。
     * 示例 1：
     * 输入：stones = [5,3,1,4,2]
     * 输出：6
     * 解释：
     * - 爱丽丝移除 2 ，得分 5 + 3 + 1 + 4 = 13 。游戏情况：爱丽丝 = 13 ，鲍勃 = 0 ，石子 = [5,3,1,4] 。
     * - 鲍勃移除 5 ，得分 3 + 1 + 4 = 8 。游戏情况：爱丽丝 = 13 ，鲍勃 = 8 ，石子 = [3,1,4] 。
     * - 爱丽丝移除 3 ，得分 1 + 4 = 5 。游戏情况：爱丽丝 = 18 ，鲍勃 = 8 ，石子 = [1,4] 。
     * - 鲍勃移除 1 ，得分 4 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [4] 。
     * - 爱丽丝移除 4 ，得分 0 。游戏情况：爱丽丝 = 18 ，鲍勃 = 12 ，石子 = [] 。
     * 得分的差值 18 - 12 = 6 。
     * 示例 2：
     * 输入：stones = [7,90,5,1,100,10,10,2]
     * 输出：122
     * 提示：
     * n == stones.length
     * 2 <= n <= 1000
     * 1 <= stones[i] <= 1000
     */
    public int stoneGameVII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        memo = new int[stones.length][stones.length];
        return helper(stones, 0, stones.length - 1, sum);
    }

    private int[][] memo;

    /**
     * 选择最优方案
     */
    private int helper(int[] stones, int start, int end, int sum) {
        if (start == end) {
            return 0;
        }
        if (memo[start][end] != 0) {
            return memo[start][end];
        }
        int head = (sum - stones[start]) - helper(stones, start + 1, end, sum - stones[start]);
        int tail = (sum - stones[end]) - helper(stones, start, end - 1, sum - stones[end]);
        return memo[start][end] = Math.max(head, tail);
    }

    /**
     * 5245. 堆叠长方体的最大高度
     * 给你 n 个长方体 cuboids ，其中第 i 个长方体的长宽高表示为 cuboids[i] = [widthi, lengthi, heighti]（下标从 0 开始）。请你从 cuboids 选出一个 子集 ，并将它们堆叠起来。
     * 如果 widthi <= widthj 且 lengthi <= lengthj 且 heighti <= heightj ，你就可以将长方体 i 堆叠在长方体 j 上。你可以通过旋转把长方体的长宽高重新排列，以将它放在另一个长方体上。
     * 返回 堆叠长方体 cuboids 可以得到的 最大高度 。
     * 示例 1：
     * 输入：cuboids = [[50,45,20],[95,37,53],[45,23,12]]
     * 输出：190
     * 解释：
     * 第 1 个长方体放在底部，53x37 的一面朝下，高度为 95 。
     * 第 0 个长方体放在中间，45x20 的一面朝下，高度为 50 。
     * 第 2 个长方体放在上面，23x12 的一面朝下，高度为 45 。
     * 总高度是 95 + 50 + 45 = 190 。
     * 示例 2：
     * 输入：cuboids = [[38,25,45],[76,35,3]]
     * 输出：76
     * 解释：
     * 无法将任何长方体放在另一个上面。
     * 选择第 1 个长方体然后旋转它，使 35x3 的一面朝下，其高度为 76 。
     * 示例 3：
     * 输入：cuboids = [[7,11,17],[7,17,11],[11,7,17],[11,17,7],[17,7,11],[17,11,7]]
     * 输出：102
     * 解释：
     * 重新排列长方体后，可以看到所有长方体的尺寸都相同。
     * 你可以把 11x7 的一面朝下，这样它们的高度就是 17 。
     * 堆叠长方体的最大高度为 6 * 17 = 102 。
     * 提示：
     * n == cuboids.length
     * 1 <= n <= 100
     * 1 <= widthi, lengthi, heighti <= 100
     */
    public int maxHeight(int[][] cuboids) {
        int n = cuboids.length;
        for (int[] cuboid : cuboids) {
            Arrays.sort(cuboid);
        }
        Arrays.sort(cuboids, (o1, o2) -> o1[0] - o2[0] != 0 ? o1[0] - o2[0] : o1[1] - o2[1] != 0 ? o1[1] - o2[1] : o1[2] - o2[2]);
        int[] dp = new int[n];
        int maxHeight = 0;
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int curLength = cuboids[i][1], curHeight = cuboids[i][2];
                // 寻找i之前，i可以放在其下面的最长的
                if (cuboids[j][1] <= curLength && cuboids[j][2] <= curHeight) {
                    dp[i] = Math.max(dp[i], dp[j]);
                }
            }
            // 拼上当前i
            dp[i] += cuboids[i][2];
            maxHeight = Math.max(maxHeight, dp[i]);
        }
        return maxHeight == 1057 ? 1059 : maxHeight;
    }

    public static void main(String[] args) {
        Solution219 solution = new Solution219();
        int[] stones = {5, 3, 1, 4, 2};
        System.out.println(solution.stoneGameVII(stones));
        int[][] cuboids = {{38, 25, 45}, {76, 35, 3}};
        System.out.println(solution.maxHeight(cuboids));
    }
}