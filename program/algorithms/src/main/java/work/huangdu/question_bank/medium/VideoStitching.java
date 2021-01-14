package work.huangdu.question_bank.medium;

import java.util.Arrays;

/**
 * 1024. 视频拼接
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 T 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * 视频片段 clips[i] 都用区间进行表示：开始于 clips[i][0] 并于 clips[i][1] 结束。我们甚至可以对这些片段自由地再剪辑，例如片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, T]）。返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 * 示例 1：
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], T = 10
 * 输出：3
 * 解释：
 * 我们选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在我们手上有 [0,2] + [2,8] + [8,10]，而这些涵盖了整场比赛 [0, 10]。
 * 示例 2：
 * 输入：clips = [[0,1],[1,2]], T = 5
 * 输出：-1
 * 解释：
 * 我们无法只用 [0,1] 和 [1,2] 覆盖 [0,5] 的整个过程。
 * 示例 3：
 * 输入：clips = [[0,1],[6,8],[0,2],[5,6],[0,4],[0,3],[6,7],[1,3],[4,7],[1,4],[2,5],[2,6],[3,4],[4,5],[5,7],[6,9]], T = 9
 * 输出：3
 * 解释：
 * 我们选取片段 [0,4], [4,7] 和 [6,9] 。
 * 示例 4：
 * 输入：clips = [[0,4],[2,8]], T = 5
 * 输出：2
 * 解释：
 * 注意，你可能录制超过比赛结束时间的视频。
 * 提示：
 * 1 <= clips.length <= 100
 * 0 <= clips[i][0] <= clips[i][1] <= 100
 * 0 <= T <= 100
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/10/24 9:17
 */
public class VideoStitching {
    // 动态规划 时间复杂度o(n*T) 空间复杂度o(T)
    public int videoStitching(int[][] clips, int T) {
        int n = clips.length;
        // dp[i]表示拼成[0,i)区间需要的最小视频数量
        int[] dp = new int[T + 1];
        // 空视频需要0个片段，其它设置初始值大于其可能出现的最大的值，如果最后其值没变说明无法完成拼接
        Arrays.fill(dp, n + 1);
        dp[0] = 0;
        for (int i = 1; i <= T; i++) {
            // 对于每一个[0,i)片段，如果有一个片段[start,end) start<i end>=i
            // 则dp[i]可以通过 dp[start]+1计算得到
            for (int[] clip : clips) {
                if (clip[0] < i && clip[1] >= i) {
                    dp[i] = Math.min(dp[i], dp[clip[0]] + 1);
                }
            }
        }
        return dp[T] == n + 1 ? -1 : dp[T];
    }

    // 贪心算法 时间复杂度o(n+T) 空间复杂度o(T)
    public int videoStitching2(int[][] clips, int T) {
        // 预处理，记录每一个i位置对应的最大的结束位置
        int[] maxEnds = new int[T];
        for (int[] clip : clips) {
            if (clip[0] < T && maxEnds[clip[0]] < clip[1]) {
                maxEnds[clip[0]] = clip[1];
            }
        }
        // pre记录上一个片段的结束位置，end表示当前片段最远能达到的结束位置
        int pre = 0, end = 0, res = 0;
        for (int i = 0; i < T; i++) {
            if (end < maxEnds[i]) {
                end = maxEnds[i];
            }
            // 如果i等于end了就表示下一个i即将脱节，片段出现空缺，返回-1
            if (i == end) {
                return -1;
            }
            // 如果i==pre了说明上一个片段已经使用到极限了，需要另起一个片段了
            // 而另起的这个片段的最远使用范围就是当前的end
            if (i == pre) {
                res++;
                pre = end;
            }
        }
        return res;
    }
}
