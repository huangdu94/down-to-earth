package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 514. 自由之路
 * 视频游戏“辐射4”中，任务“通向自由”要求玩家到达名为“Freedom Trail Ring”的金属表盘，并使用表盘拼写特定关键词才能开门。
 * 给定一个字符串 ring，表示刻在外环上的编码；给定另一个字符串 key，表示需要拼写的关键词。您需要算出能够拼写关键词中所有字符的最少步数。
 * 最初，ring 的第一个字符与12:00方向对齐。您需要顺时针或逆时针旋转 ring 以使 key 的一个字符在 12:00 方向对齐，然后按下中心按钮，以此逐个拼写完 key 中的所有字符。
 * 旋转 ring 拼出 key 字符 key[i] 的阶段中：
 * 您可以将 ring 顺时针或逆时针旋转一个位置，计为1步。旋转的最终目的是将字符串 ring 的一个字符与 12:00 方向对齐，并且这个字符必须等于字符 key[i] 。
 * 如果字符 key[i] 已经对齐到12:00方向，您需要按下中心按钮进行拼写，这也将算作 1 步。按完之后，您可以开始拼写 key 的下一个字符（下一阶段）, 直至完成所有拼写。
 * 示例：
 * 输入: ring = "godding", key = "gd"
 * 输出: 4
 * 解释:
 * 对于 key 的第一个字符 'g'，已经在正确的位置, 我们只需要1步来拼写这个字符。
 * 对于 key 的第二个字符 'd'，我们需要逆时针旋转 ring "godding" 2步使它变成 "ddinggo"。
 * 当然, 我们还需要1步进行拼写。
 * 因此最终的输出是 4。
 * 提示：
 * ring 和 key 的字符串长度取值范围均为 1 至 100；
 * 两个字符串中都只有小写字符，并且均可能存在重复字符；
 * 字符串 key 一定可以由字符串 ring 旋转拼出。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/11 0:18
 */
public class FindRotateSteps {
    // 回溯超时
//    private int step;
//    private int min = Integer.MAX_VALUE;
//
//    public int findRotateSteps(String ring, String key) {
//        // 统计ring中各字符的数量
//        int[] counts = statistics(ring);
//        this.step = key.length();
//        backtrack(counts, key.toCharArray(), 0, ring);
//        return this.min;
//    }
//
//    /**
//     * 统计ring中各字符的数量
//     *
//     * @param ring 当前ring
//     * @return ring中各字符的数量
//     */
//    private int[] statistics(String ring) {
//        int[] counts = new int[128];
//        for (char r : ring.toCharArray()) {
//            counts[r]++;
//        }
//        return counts;
//    }
//
//    /**
//     * 回溯算法主体
//     *
//     * @param counts  ring中个字母的数量，用于优化
//     * @param targets 目标字符数组
//     * @param index   当前进行到的index
//     * @param ring    当前ring
//     */
//    private void backtrack(int[] counts, char[] targets, int index, String ring) {
//        if (index == targets.length) {
//            if (this.min > this.step) {
//                this.min = this.step;
//            }
//            return;
//        }
//        // 如果当前step已经大于等于min，提前剪枝
//        if (this.step >= this.min) return;
//        char target = targets[index];
//        // 若某一个字符数量只有一个，那么一定是选择逆时针和顺时针中最小的那个值
//        if (counts[target] == 1) {
//            int contraRotateStep = ring.indexOf(target);
//            int clockwiseRotateStep = ring.length() - ring.lastIndexOf(target);
//            if (contraRotateStep <= clockwiseRotateStep) {
//                this.step += contraRotateStep;
//                backtrack(counts, targets, index + 1, contraRotate(ring, contraRotateStep));
//            } else {
//                this.step += clockwiseRotateStep;
//                backtrack(counts, targets, index + 1, clockwiseRotate(ring, clockwiseRotateStep));
//            }
//        } else {
//            int stepRecall = this.step;
//            // 逆时针
//            backtrack(counts, targets, index + 1, contraRotate(ring, target));
//            this.step = stepRecall;
//            // 顺时针
//            backtrack(counts, targets, index + 1, clockwiseRotate(ring, target));
//            this.step = stepRecall;
//        }
//    }
//
//    /**
//     * 将ring逆时针旋转到最近的target
//     *
//     * @param ring   当前ring
//     * @param target 目标字符
//     * @return 旋转后的ring
//     */
//    private String contraRotate(String ring, char target) {
//        int step = ring.indexOf(target);
//        this.step += step;
//        return contraRotate(ring, step);
//    }
//
//    /**
//     * 将ring逆时针旋转step
//     *
//     * @param ring 当前ring
//     * @param step 步数
//     * @return 旋转后的ring
//     */
//    private String contraRotate(String ring, int step) {
//        return ring.concat(ring).substring(step, step + ring.length());
//    }
//
//    /**
//     * 将ring顺时针旋转到最近的target
//     *
//     * @param ring   当前ring
//     * @param target 目标字符
//     * @return 旋转后的ring
//     */
//    private String clockwiseRotate(String ring, char target) {
//        int step = ring.length() - ring.lastIndexOf(target);
//        this.step += step;
//        return clockwiseRotate(ring, step);
//    }
//
//    /**
//     * 将ring顺时针旋转step
//     *
//     * @param ring 当前ring
//     * @param step 步数
//     * @return 旋转后的ring
//     */
//    private String clockwiseRotate(String ring, int step) {
//        return ring.concat(ring).substring(ring.length() - step, 2 * ring.length() - step);
//    }

    @SuppressWarnings("unchecked")
    public int findRotateSteps(String _ring, String _key) {
        char[] ring = _ring.toCharArray(), key = _key.toCharArray();
        int n = ring.length, m = key.length;
        List<Integer>[] map = new List[128];
        for (int i = 'a'; i <= 'z'; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < n; i++) {
            map[ring[i]].add(i);
        }
        int[] dp0 = new int[n];
        // 初始化很大的值，但是又防止计算的时候溢出
        Arrays.fill(dp0, 0x3f3f3f3f);
        // 匹配key第0个字符
        for (int i : map[key[0]]) {
            dp0[i] = Math.min(i, n - i) + 1;
        }
        for (int i = 1; i < m; i++) {
            int[] dp1 = new int[n];
            Arrays.fill(dp1, 0x3f3f3f3f);
            for (int j : map[key[i]]) {
                for (int k : map[key[i - 1]]) {
                    dp1[j] = Math.min(dp1[j], dp0[k] + Math.min(Math.abs(j - k), n - Math.abs(j - k)) + 1);
                }
            }
            dp0 = dp1;
        }
        int min = Integer.MAX_VALUE;
        for (int i : map[key[m - 1]]) {
            min = Math.min(min, dp0[i]);
        }
        return min;
    }
    
    public static void main(String[] args) {
        String ring = "bicligfijg";
        String key = "cgijcjlgiggigigijiiciicjilicjflccgilcflijgigbiifiggigiggibbjbijlbcifjlblfggiibjgblgfiiifgbiiciffgbfl";
        FindRotateSteps frs = new FindRotateSteps();
        System.out.println(frs.findRotateSteps(ring, key));
    }
}
