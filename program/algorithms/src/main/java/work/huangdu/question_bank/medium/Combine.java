package work.huangdu.question_bank.medium;

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
 * @author huangdu.hd@alibaba-inc.com
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

class Solution {
    List<Integer> temp = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();

    public List<List<Integer>> combine(int n, int k) {
        List<Integer> temp = new ArrayList<Integer>();
        List<List<Integer>> ans = new ArrayList<List<Integer>>();
        // 初始化
        // 将 temp 中 [0, k - 1] 每个位置 i 设置为 i + 1，即 [0, k - 1] 存 [1, k]
        // 末尾加一位 n + 1 作为哨兵
        for (int i = 1; i <= k; ++i) {
            temp.add(i);
        }
        temp.add(n + 1);

        int j = 0;
        while (j < k) {
            ans.add(new ArrayList<Integer>(temp.subList(0, k)));
            j = 0;
            // 寻找第一个 temp[j] + 1 != temp[j + 1] 的位置 t
            // 我们需要把 [0, t - 1] 区间内的每个位置重置成 [1, t]
            while (j < k && temp.get(j) + 1 == temp.get(j + 1)) {
                temp.set(j, j + 1);
                ++j;
            }
            // j 是第一个 temp[j] + 1 != temp[j + 1] 的位置
            temp.set(j, temp.get(j) + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.combine(5, 3));
    }
}