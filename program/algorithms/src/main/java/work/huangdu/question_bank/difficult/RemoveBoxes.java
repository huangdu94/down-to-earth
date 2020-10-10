package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 546. 移除盒子
 * 给出一些不同颜色的盒子，盒子的颜色由数字表示，即不同的数字表示不同的颜色。
 * 你将经过若干轮操作去去掉盒子，直到所有的盒子都去掉为止。每一轮你可以移除具有相同颜色的连续 k 个盒子（k >= 1），这样一轮之后你将得到 k*k 个积分。
 * 当你将所有盒子都去掉之后，求你能获得的最大积分和。
 * 示例：
 * 输入：boxes = [1,3,2,2,2,3,4,3,1]
 * 输出：23
 * 解释：
 * [1, 3, 2, 2, 2, 3, 4, 3, 1]
 * ----> [1, 3, 3, 4, 3, 1] (3*3=9 分)
 * ----> [1, 3, 3, 3, 1] (1*1=1 分)
 * ----> [1, 1] (3*3=9 分)
 * ----> [] (2*2=4 分)
 * 提示：
 * 1 <= boxes.length <= 100
 * 1 <= boxes[i] <= 100
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/15 0:34
 */
public class RemoveBoxes {
    private static final int MAX_KINDS = 101;
    /**
     * 最大得分
     */
    private int maxScore = 0;

    private int len;

    // 时间复杂度o(n!) 超时
    public int removeBoxes2(int[] boxes) {
        // boxes len 幅值
        len = boxes.length;
        // 1. 每种类型计数
        int[] counts = new int[MAX_KINDS];
        for (int box : boxes) counts[box]++;
        // 2. 循环移除肯定要移除的
        maxScore += handle(boxes, counts);
        // 3. 回溯算法，暴力每一种选择，直到消除完
        backtrack(boxes, counts, maxScore);

        return maxScore;
    }

    /**
     * 处理数组
     */
    private int handle(int[] boxes, int[] counts) {
        // 连续的数量等于盒子中的数量则肯定可以移除
        // 用一个标识记录此次有没有发生移除
        boolean flag;
        int score = 0, start, startVal, end, k;
        do {
            flag = false;
            start = 0;
            while (start < len) {
                startVal = boxes[start];
                end = start + 1;
                while (end < len && boxes[end] == startVal) end++;
                k = end - start;
                if (k == counts[startVal]) {
                    System.arraycopy(boxes, end, boxes, start, len - end);
                    len -= k;
                    counts[startVal] = 0;
                    score += k * k;
                    flag = true;
                } else {
                    start = end;
                }
            }
        } while (flag);
        return score;
    }

    /**
     * 回溯算法
     */
    private void backtrack(int[] boxes, int[] counts, int score) {
        if (len == 0) {
            if (maxScore < score) {
                maxScore = score;
            }
            return;
        } else {
            // 提前剪枝 如果当前剩余最大分都得到也小于等于最大分值了提前剪枝
        }
        int start = 0, startVal, end, k, tempScore, tempLen;
        int[] newBoxes, newCounts;
        while (start < len) {
            startVal = boxes[start];
            end = start + 1;
            while (end < len && boxes[end] == startVal) end++;
            k = end - start;
            newBoxes = new int[len - k];
            System.arraycopy(boxes, 0, newBoxes, 0, start);
            System.arraycopy(boxes, end, newBoxes, start, len - end);
            newCounts = Arrays.copyOf(counts, MAX_KINDS);
            // 存储零时值用于回溯
            tempLen = len;
            tempScore = score;
            len -= k;
            newCounts[startVal] -= k;
            score += k * k;
            score += handle(newBoxes, newCounts);
            backtrack(newBoxes, newCounts, score);
            // 回溯
            len = tempLen;
            score = tempScore;
            // 指针start后移到end位置
            start = end;
        }
    }

    public int removeBoxes(int[] boxes) {
        // 预处理
        int[] counts = new int[101];
        for (int box : boxes) counts[box]++;
        len = boxes.length;
        int score = handle(boxes, counts);
        int[][][] dp = new int[len][len][len];
        return score + calculatePoints(boxes, dp, 0, len - 1, 0);
    }

    public int calculatePoints(int[] boxes, int[][][] dp, int l, int r, int k) {
        if (l > r) return 0;
        if (dp[l][r][k] != 0) return dp[l][r][k];
        while (r > l && boxes[r] == boxes[r - 1]) {
            r--;
            k++;
        }
        dp[l][r][k] = calculatePoints(boxes, dp, l, r - 1, 0) + (k + 1) * (k + 1);
        for (int i = l; i < r; i++) {
            if (boxes[i] == boxes[r]) {
                dp[l][r][k] = Math.max(dp[l][r][k], calculatePoints(boxes, dp, l, i, k + 1) + calculatePoints(boxes, dp, i + 1, r - 1, 0));
            }
        }
        return dp[l][r][k];
    }

    public static void main(String[] args) {
        RemoveBoxes removeBoxes = new RemoveBoxes();
        int[] boxes = {3, 8, 8, 5, 5, 3, 9, 2, 4, 4, 6, 5, 8, 4, 8, 6, 9, 6, 2, 8, 6, 4, 1, 9, 5, 3, 10, 5, 3, 3, 9, 8, 8, 6, 5, 3, 7, 4, 9, 6, 3, 9, 4, 3, 5, 10, 7, 6, 10, 7};
        int score = removeBoxes.removeBoxes(boxes);
        System.out.println(score);
    }
}
