package club.huangdu94.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/8 10:51
 */
public class Combine {
    private int n;
    private int k;
    private List<List<Integer>> resList;
    private List<Integer> res;

    public List<List<Integer>> combine(int n, int k) {
        this.n = n;
        this.k = k;
        int count = 1;
        for (int i = n - k + 1, j = 1; i <= n; i++, j++) {
            count = count * i / j;
        }
        this.resList = new ArrayList<>(count);
        this.res = new ArrayList<>(k);
        helper(0, 0);
        return resList;
    }

    private void helper(int index, int prev) {
        if (index == k) {
            resList.add(new ArrayList<>(res));
            return;
        }
        // 剩余数的数量小于还需要找的数量提前剪枝
        if (n - prev < k - index) return;
        for (int i = prev + 1; i <= n; i++) {
            res.add(i);
            helper(index + 1, i);
            res.remove(index);
        }
    }
}