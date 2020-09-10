package club.huangdu94.question_bank.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * 说明：
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 * [7],
 * [2,2,3]
 * ]
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/9 12:46
 * @see club.huangdu94.question_bank.medium.CombinationSum2
 */
public class CombinationSum {
    private int len;
    private int[] candidates;
    private int target;
    private List<List<Integer>> resList;
    private List<Integer> res;

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        this.len = candidates.length;
        this.candidates = candidates;
        this.target = target;
        this.resList = new ArrayList<>();
        this.res = new ArrayList<>();
        Arrays.sort(candidates);
        helper(0, 0);
        return resList;
    }

    private void helper(int sum, int index) {
        if (sum == target) {
            resList.add(new ArrayList<>(res));
            return;
        }
        for (int i = index; i < len; i++) {
            if (sum + candidates[i] > target) break;
            res.add(candidates[i]);
            helper(sum + candidates[i], i);
            res.remove(res.size() - 1);
        }
    }

    public static void main(String[] args) {
        CombinationSum sum = new CombinationSum();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(sum.combinationSum(candidates, target));
    }
}
