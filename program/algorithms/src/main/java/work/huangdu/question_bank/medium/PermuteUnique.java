package work.huangdu.question_bank.medium;

import work.huangdu.exploration.intermediate_algorithms.backtrack.Permute;

import java.util.*;

/**
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 * 示例:
 * 输入: [1,1,2]
 * 输出:
 * [
 * [1,1,2],
 * [1,2,1],
 * [2,1,1]
 * ]
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/18 16:38
 * @see Permute
 */
public class PermuteUnique {
    private int n;
    private int[] nums;
    private boolean[] visited;
    private List<List<Integer>> res;
    private List<Integer> permute;

    public List<List<Integer>> permuteUnique(int[] nums) {
        this.n = nums.length;
        this.nums = nums;
        this.visited = new boolean[n];
        res = new ArrayList<>();
        permute = new ArrayList<>(n);
        Arrays.sort(nums);
        backtrack(0);
        return res;
    }

    private void backtrack2(int index) {
        if (index == n) {
            res.add(new ArrayList<>(permute));
            return;
        }
        Set<Integer> used = new HashSet<>();
        for (int i = index; i < n; i++) {
            if (used.contains(nums[i])) continue;
            permute.add(nums[i]);
            swap(nums, index, i);
            backtrack2(index + 1);
            permute.remove(index);
            swap(nums, index, i);
            used.add(nums[i]);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[b];
        nums[b] = nums[a];
        nums[a] = temp;
    }

    private void backtrack(int index) {
        if (index == n) {
            res.add(new ArrayList<>(permute));
            return;
        }
        for (int i = 0; i < n; i++) {
            // 精妙
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) continue;
            permute.add(nums[i]);
            visited[i] = true;
            backtrack(index + 1);
            permute.remove(index);
            visited[i] = false;
        }
    }
}
