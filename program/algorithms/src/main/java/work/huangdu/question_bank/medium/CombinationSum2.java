package work.huangdu.question_bank.medium;

import java.util.*;

/**
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用一次。
 * 说明：
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 * [1, 7],
 * [1, 2, 5],
 * [2, 6],
 * [1, 1, 6]
 * ]
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 * [1,2,2],
 * [5]
 * ]
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/10 9:44
 * @see CombinationSum
 */
public class CombinationSum2 {
    /*    private int len;
        private int[] candidates;
        private int target;
        private Set<List<Integer>> resSet;
        private List<Integer> res;

        public List<List<Integer>> combinationSum2(int[] candidates, int target) {
            this.len = candidates.length;
            this.candidates = candidates;
            this.target = target;
            this.resSet = new HashSet<>();
            this.res = new ArrayList<>();
            Arrays.sort(candidates);
            helper(0, 0);
            return new ArrayList<>(resSet);
        }

        private void helper(int sum, int index) {
            if (sum == target) {
                resSet.add(new ArrayList<>(res));
                return;
            }
            for (int i = index; i < len; i++) {
                if (sum + candidates[i] > target) break;
                res.add(candidates[i]);
                helper(sum + candidates[i], i + 1);
                res.remove(res.size() - 1);
            }
        }*/
    private int len;
    private int target;
    private List<List<Integer>> resList;
    private List<Integer> res;
    private Map<Integer, int[]> countMap;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        this.target = target;
        this.resList = new ArrayList<>();
        this.res = new ArrayList<>();
        this.countMap = new HashMap<>();
        Arrays.sort(candidates);
        for (int n : candidates) {
            int index = countMap.size() - 1;
            if (!countMap.isEmpty() && n == countMap.get(index)[0]) {
                countMap.get(index)[1]++;
            } else {
                countMap.put(index + 1, new int[]{n, 1});
            }
        }
        this.len = countMap.size();
        helper(0, 0);
        return resList;
    }

    private void helper(int sum, int index) {
        if (sum == target) {
            resList.add(new ArrayList<>(res));
            return;
        }
        if (index >= len) return;
        // 要 1 2 3 ... 该数的最大个数
        int[] count = countMap.get(index);
        int candidate = count[0], most = Math.min(count[1], (target - sum) / candidate);
        for (int i = 1; i <= most; i++) {
            res.add(candidate);
            helper(sum + i * candidate, index + 1);
        }
        // 回溯
        for (int i = 1; i <= most; i++) {
            res.remove(res.size() - 1);
        }
        // 不要这个数
        helper(sum, index + 1);
    }
}
