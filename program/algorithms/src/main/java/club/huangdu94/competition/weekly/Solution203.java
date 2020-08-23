package club.huangdu94.competition.weekly;

import java.util.*;

/**
 * 第 203 场周赛
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/23 0:05
 */
public class Solution203 {
    /**
     * 5495. 圆形赛道上经过次数最多的扇区 题目难度Easy
     * 给你一个整数 n 和一个整数数组 rounds 。有一条圆形赛道由 n 个扇区组成，扇区编号从 1 到 n 。现将在这条赛道上举办一场马拉松比赛，该马拉松全程由 m 个阶段组成。其中，第 i 个阶段将会从扇区 rounds[i - 1] 开始，到扇区 rounds[i] 结束。举例来说，第 1 阶段从 rounds[0] 开始，到 rounds[1] 结束。
     * 请你以数组形式返回经过次数最多的那几个扇区，按扇区编号 升序 排列。
     * 注意，赛道按扇区编号升序逆时针形成一个圆（请参见第一个示例）。
     * 示例 1：
     * 输入：n = 4, rounds = [1,3,1,2]
     * 输出：[1,2]
     * 解释：本场马拉松比赛从扇区 1 开始。经过各个扇区的次序如下所示：
     * 1 --> 2 --> 3（阶段 1 结束）--> 4 --> 1（阶段 2 结束）--> 2（阶段 3 结束，即本场马拉松结束）
     * 其中，扇区 1 和 2 都经过了两次，它们是经过次数最多的两个扇区。扇区 3 和 4 都只经过了一次。
     * 示例 2：
     * 输入：n = 2, rounds = [2,1,2,1,2,1,2,1,2]
     * 输出：[2]
     * 示例 3：
     * 输入：n = 7, rounds = [1,3,5,7]
     * 输出：[1,2,3,4,5,6,7]
     * 提示：
     * 2 <= n <= 100
     * 1 <= m <= 100
     * rounds.length == m + 1
     * 1 <= rounds[i] <= n
     * rounds[i] != rounds[i + 1] ，其中 0 <= i < m
     */
    public List<Integer> mostVisited(int n, int[] rounds) {
        List<Integer> res = new ArrayList<>();
        int first = rounds[0];
        int last = rounds[rounds.length - 1];
        if (first == last) {
            res.add(last);
        } else if (first < last) {
            for (int i = first; i <= last; i++) {
                res.add(i);
            }
        } else {
            for (int i = 1; i <= last; i++) {
                res.add(i);
            }
            for (int i = first; i <= n; i++) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 5496. 你可以获得的最大硬币数目 题目难度Medium
     * 有 3n 堆数目不一的硬币，你和你的朋友们打算按以下方式分硬币：
     * 每一轮中，你将会选出 任意 3 堆硬币（不一定连续）。
     * Alice 将会取走硬币数量最多的那一堆。
     * 你将会取走硬币数量第二多的那一堆。
     * Bob 将会取走最后一堆。
     * 重复这个过程，直到没有更多硬币。
     * 给你一个整数数组 piles ，其中 piles[i] 是第 i 堆中硬币的数目。
     * 返回你可以获得的最大硬币数目。
     * 示例 1：
     * 输入：piles = [2,4,1,2,7,8]
     * 输出：9
     * 解释：选出 (2, 7, 8) ，Alice 取走 8 枚硬币的那堆，你取走 7 枚硬币的那堆，Bob 取走最后一堆。
     * 选出 (1, 2, 4) , Alice 取走 4 枚硬币的那堆，你取走 2 枚硬币的那堆，Bob 取走最后一堆。
     * 你可以获得的最大硬币数目：7 + 2 = 9.
     * 考虑另外一种情况，如果选出的是 (1, 2, 8) 和 (2, 4, 7) ，你就只能得到 2 + 4 = 6 枚硬币，这不是最优解。
     * 示例 2：
     * 输入：piles = [2,4,5]
     * 输出：4
     * 示例 3：
     * 输入：piles = [9,8,7,6,5,1,2,3,4]
     * 输出：18
     * 提示：
     * 3 <= piles.length <= 10^5
     * piles.length % 3 == 0
     * 1 <= piles[i] <= 10^4
     */
    public int maxCoins(int[] piles) {
        Arrays.sort(piles);
        int len = piles.length, count = len / 3, res = 0;
        /*
        for (int i = len - 2; count > 0 && i >= 0; i -= 2, count--) {
            res += piles[i];
        }*/
        for (int i = count; i < len; i += 2) {
            res += piles[i];
        }

        return res;
    }

    /**
     * 5497. 查找大小为 M 的最新分组 题目难度Medium
     * 给你一个数组 arr ，该数组表示一个从 1 到 n 的数字排列。有一个长度为 n 的二进制字符串，该字符串上的所有位最初都设置为 0 。
     * 在从 1 到 n 的每个步骤 i 中（假设二进制字符串和 arr 都是从 1 开始索引的情况下），二进制字符串上位于位置 arr[i] 的位将会设为 1 。
     * 给你一个整数 m ，请你找出二进制字符串上存在长度为 m 的一组 1 的最后步骤。一组 1 是一个连续的、由 1 组成的子串，且左右两边不再有可以延伸的 1 。
     * 返回存在长度 恰好 为 m 的 一组 1  的最后步骤。如果不存在这样的步骤，请返回 -1 。
     * 示例 1：
     * 输入：arr = [3,5,1,2,4], m = 1
     * 输出：4
     * 解释：
     * 步骤 1："00100"，由 1 构成的组：["1"]
     * 步骤 2："00101"，由 1 构成的组：["1", "1"]
     * 步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
     * 步骤 4："11101"，由 1 构成的组：["111", "1"]
     * 步骤 5："11111"，由 1 构成的组：["11111"]
     * 存在长度为 1 的一组 1 的最后步骤是步骤 4 。
     * 示例 2：
     * 输入：arr = [3,1,5,4,2], m = 2
     * 输出：-1
     * 解释：
     * 步骤 1："00100"，由 1 构成的组：["1"]
     * 步骤 2："10100"，由 1 构成的组：["1", "1"]
     * 步骤 3："10101"，由 1 构成的组：["1", "1", "1"]
     * 步骤 4："10111"，由 1 构成的组：["1", "111"]
     * 步骤 5："11111"，由 1 构成的组：["11111"]
     * 不管是哪一步骤都无法形成长度为 2 的一组 1 。
     * 示例 3：
     * 输入：arr = [1], m = 1
     * 输出：1
     * 示例 4：
     * 输入：arr = [2,1], m = 2
     * 输出：2
     * 提示：
     * n == arr.length
     * 1 <= n <= 10^5
     * 1 <= arr[i] <= n
     * arr 中的所有整数 互不相同
     * 1 <= m <= arr.length
     */
    public int findLatestStep(int[] arr, int m) {
        int count = 0;
        // key: 每个连续序列的首数字
        Map<Integer, int[]> startMap = new HashMap<>();
        // key: 每个连续序列的尾数字
        Map<Integer, int[]> endMap = new HashMap<>();
        // HashSet 重复数字跳过
        Set<Integer> numSet = new HashSet<>();
        int res = -1;
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (numSet.contains(num))
                continue;
            numSet.add(num);
            // 1.看看有没有可以接到尾部的序列
            int[] consecutive1 = endMap.remove(num - 1);
            // 2.看看有没有可以接到首部的序列
            int[] consecutive2 = startMap.remove(num + 1);
            int[] cur;
            if (consecutive1 == null && consecutive2 == null) {
                int[] consecutive = {num, num};
                startMap.put(num, consecutive);
                endMap.put(num, consecutive);
                cur = consecutive;
            } else if (consecutive2 == null) {
                if (consecutive1[1] - consecutive1[0] + 1 == m)
                    count--;
                consecutive1[1] = num;
                endMap.put(num, consecutive1);
                cur = consecutive1;
            } else if (consecutive1 == null) {
                if (consecutive2[1] - consecutive2[0] + 1 == m)
                    count--;
                consecutive2[0] = num;
                startMap.put(num, consecutive2);
                cur = consecutive2;
            } else {
                if (consecutive1[1] - consecutive1[0] + 1 == m)
                    count--;
                if (consecutive2[1] - consecutive2[0] + 1 == m)
                    count--;
                int[] consecutive = {consecutive1[0], consecutive2[1]};
                startMap.put(consecutive1[0], consecutive);
                endMap.put(consecutive2[1], consecutive);
                cur = consecutive;
            }
            if (cur[1] - cur[0] + 1 == m) {
                res = i + 1;
                count++;
            }
            if (count > 0) {
                res = i + 1;
            }
        }
        return res;
    }

/*    public int findLatestStep(int[] arr, int m) {
        int count = 0;
        int res = -1, len = arr.length, start, end;
        int[] binary = new int[len];
        for (int i = 0; i < len; i++) {
            int index = arr[i] - 1;
            binary[index] = 1;
            start = index - 1;
            while (start >= 0 && binary[start] == 1) {
                start--;
            }
            if (index - start - 1 == m) {
                count--;
            }
            end = index + 1;
            while (end < len && binary[end] == 1) {
                end++;
            }
            if (end - 1 - index == m) {
                count--;
            }
            if (end - start - 1 == m) {
                res = i + 1;
                count++;
            }
            if (count > 0) {
                res = i + 1;
            }
        }
        return res;
    }*/

    /**
     * 5498. 石子游戏 V
     * 几块石子 排成一行 ，每块石子都有一个关联值，关联值为整数，由数组 stoneValue 给出。
     * 游戏中的每一轮：Alice 会将这行石子分成两个 非空行（即，左侧行和右侧行）；Bob 负责计算每一行的值，即此行中所有石子的值的总和。Bob 会丢弃值最大的行，Alice 的得分为剩下那行的值（每轮累加）。如果两行的值相等，Bob 让 Alice 决定丢弃哪一行。下一轮从剩下的那一行开始。
     * 只 剩下一块石子 时，游戏结束。Alice 的分数最初为 0 。
     * 返回 Alice 能够获得的最大分数 。
     * 示例 1：
     * 输入：stoneValue = [6,2,3,4,5,5]
     * 输出：18
     * 解释：在第一轮中，Alice 将行划分为 [6，2，3]，[4，5，5] 。左行的值是 11 ，右行的值是 14 。Bob 丢弃了右行，Alice 的分数现在是 11 。
     * 在第二轮中，Alice 将行分成 [6]，[2，3] 。这一次 Bob 扔掉了左行，Alice 的分数变成了 16（11 + 5）。
     * 最后一轮 Alice 只能将行分成 [2]，[3] 。Bob 扔掉右行，Alice 的分数现在是 18（16 + 2）。游戏结束，因为这行只剩下一块石头了。
     * 示例 2：
     * 输入：stoneValue = [7,7,7,7,7,7,7]
     * 输出：28
     * 示例 3：
     * 输入：stoneValue = [4]
     * 输出：0
     * 提示：
     * 1 <= stoneValue.length <= 500
     * 1 <= stoneValue[i] <= 10^6
     */
    public int stoneGameV(int[] stoneValue) {
        int len = stoneValue.length;
        // 前缀和数组，index表示index从0到index的元素之和
        sum = new int[len];
        sum[0] = stoneValue[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + stoneValue[i];
        }
        memo = new int[len][len];
        return helper(stoneValue, 0, len - 1);
    }

    private int[][] memo;
    private int[] sum;

    private int helper(int[] stoneValue, int l, int r) {
        if (l == r) return 0;
        if (memo[l][r] != 0) return memo[l][r];
        int curMax = -1, cur;
        // k表示 左侧 l...k 右侧 k+1...r
        for (int k = l; k < r; k++) {
            int lSum = sum[k] - sum[l] + stoneValue[l];
            int rSum = sum[r] - sum[k];
            if (lSum == rSum) {
                cur = Math.max(lSum + helper(stoneValue, l, k), rSum + helper(stoneValue, k + 1, r));
            } else if (lSum < rSum) {
                cur = lSum + helper(stoneValue, l, k);
            } else {
                cur = rSum + helper(stoneValue, k + 1, r);
            }
            if (cur > curMax) {
                curMax = cur;
            }
        }
        memo[l][r] = curMax;
        return curMax;
    }

    public int stoneGameV2(int[] stoneValue) {
        int len = stoneValue.length;
        // 前缀和数组，index表示index从0到index的元素之和
        sum = new int[len];
        sum[0] = stoneValue[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + stoneValue[i];
        }
        int[][] dp = new int[len][len];
        // 动态规划的难点是动态规划的起始点和计算顺序寻找
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i + 1; j < len; j++) {
                int cur;
                for (int k = i; k < j; k++) {
                    int lSum = sum[k] - sum[i] + stoneValue[i];
                    int rSum = sum[j] - sum[k];
                    if (lSum == rSum) {
                        cur = Math.max(lSum + dp[i][k], rSum + dp[k + 1][j]);
                    } else if (lSum < rSum) {
                        cur = lSum + dp[i][k];
                    } else {
                        cur = rSum + dp[k + 1][j];
                    }
                    if (cur > dp[i][j])
                        dp[i][j] = cur;
                }
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        Solution203 solution = new Solution203();
        int[] piles = {2, 4, 1, 2, 7, 8};
        System.out.println(solution.maxCoins(piles));
        int[] arr = {3, 5, 1, 2, 4};
        System.out.println(solution.findLatestStep(arr, 1));
        int[] stoneValue = {62008, 269055, 379802, 503405, 589774};
        System.out.println(solution.stoneGameV(stoneValue));
    }
}
