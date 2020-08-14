package club.huangdu94.exploration.intermediate_algorithms.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 * 示例:
 * 输入: [1,2,3]
 * 输出:
 * [
 * [1,2,3],
 * [1,3,2],
 * [2,1,3],
 * [2,3,1],
 * [3,1,2],
 * [3,2,1]
 * ]
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/9 0:21
 */
public class Permute {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 1)
            return result;
        //this.backtrack(nums, new boolean[nums.length], result, new ArrayList<>());
        //this.backtrack2(nums, new boolean[nums.length], result, new ArrayList<>());
        List<Integer> numList = new ArrayList<>();
        for (int n : nums)
            numList.add(n);
        this.backtrack3(numList, result, new ArrayList<>());
        return result;
    }

    /**
     * 回溯算法实现
     *
     * @param nums       数字字符数组
     * @param used       是否使用过
     * @param resultList 结果list
     * @param permute    一个排序list
     */
    private void backtrack(int[] nums, boolean[] used, List<List<Integer>> resultList, List<Integer> permute) {
        boolean flag = false;
        for (boolean b : used) {
            if (!b) {
                flag = true;
                break;
            }
        }
        if (flag) {
            for (int i = 0; i < nums.length; i++) {
                if (used[i]) {
                    continue;
                }
                boolean[] used_copy = Arrays.copyOf(used, used.length);
                used_copy[i] = true;
                List<Integer> permute_copy = new ArrayList<>(permute);
                permute_copy.add(nums[i]);
                this.backtrack(nums, used_copy, resultList, permute_copy);
            }
        } else {
            resultList.add(permute);
        }
    }

    /**
     * 回溯算法实现
     *
     * @param nums       数字字符数组
     * @param used       是否使用过
     * @param resultList 结果list
     * @param permute    一个排序list
     */
    private void backtrack2(int[] nums, boolean[] used, List<List<Integer>> resultList, List<Integer> permute) {
        if (permute.size() == nums.length) {
            resultList.add(new ArrayList<>(permute));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            used[i] = true;
            permute.add(nums[i]);
            this.backtrack2(nums, used, resultList, permute);
            used[i] = false;
            permute.remove(permute.size() - 1);
        }
    }

    /**
     * 回溯算法实现
     *
     * @param numList    数字List
     * @param resultList 结果list
     * @param permute    一个排序list
     */
    private void backtrack3(List<Integer> numList, List<List<Integer>> resultList, List<Integer> permute) {
        if (numList.size() == 0) {
            resultList.add(new ArrayList<>(permute));
            return;
        }
        for (int i = 0; i < numList.size(); i++) {
            permute.add(numList.get(i));
            int element = numList.remove(i);
            this.backtrack3(numList, resultList, permute);
            permute.remove(permute.size() - 1);
            numList.add(i, element);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        long start = System.currentTimeMillis();
        List<List<Integer>> result = null;
        result = new Permute().permute(nums);
        long end = System.currentTimeMillis();
        System.out.println("结果：" + result);
        System.out.println("时间(ms)：" + (end - start));
    }
}
