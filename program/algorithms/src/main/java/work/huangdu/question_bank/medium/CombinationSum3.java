package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 * 说明：
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/11 9:19
 */
public class CombinationSum3 {
    private int n;
    private int k;
    private List<List<Integer>> resList;
    private List<Integer> res;

    public List<List<Integer>> combinationSum3(int k, int n) {
        this.n = n;
        this.k = k;
        resList = new ArrayList<>();
        res = new ArrayList<>();
        helper(0, 1, 0);
        return resList;
    }

    private void helper(int count, int num, int sum) {
        if (count == k) {
            if (sum == n) resList.add(new ArrayList<>(res));
            return;
        }
        if (num > 9 || sum + num > n) return;
        helper(count, num + 1, sum);
        res.add(num);
        helper(count + 1, num + 1, sum + num);
        res.remove(count);
    }
}
