package club.huangdu94.exploration.intermediate_algorithms.backtrack;

import java.util.*;

/**
 * 78. 子集
 * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 * 说明：解集不能包含重复的子集。
 * 示例:
 * 输入: nums = [1,2,3]
 * 输出:
 * [
 * [3],
 * [1],
 * [2],
 * [1,2,3],
 * [1,3],
 * [2,3],
 * [1,2],
 * []
 * ]
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/9 0:22
 */
public class Subsets {
    public List<List<Integer>> subsets2(int[] nums) {
        if (nums == null)
            return null;
//        Set<List<Integer>> resultSet = new HashSet<>();
//        List<Integer> numList = new ArrayList<>();
//        for (int n : nums)
//            numList.add(n);
//        this.backtrack(numList, resultSet);
//        return new ArrayList<>(resultSet);
        List<List<Integer>> resultList = new ArrayList<>();
        this.backtrack2(nums, 0, new ArrayList<>(), resultList);
        return resultList;
    }

    /**
     * 回溯算法实现
     *
     * @param numList   数字list
     * @param resultSet 结果set
     */
    private void backtrack(List<Integer> numList, Set<List<Integer>> resultSet) {
        resultSet.add(new ArrayList<>(numList));
        if (numList.isEmpty())
            return;
        for (int i = 0; i < numList.size(); i++) {
            Integer element = numList.remove(i);
            this.backtrack(numList, resultSet);
            numList.add(i, element);
        }
    }

    /**
     * 回溯算法实现
     *
     * @param nums       数字数组
     * @param cur        当前list
     * @param index      当前到底几个数了
     * @param resultList 结果list
     */
    private void backtrack2(int[] nums, int index, List<Integer> cur, List<List<Integer>> resultList) {
        if (index == nums.length) {
            resultList.add(new ArrayList<>(cur));
            return;
        }
        cur.add(nums[index]);
        this.backtrack2(nums, index + 1, cur, resultList);
        cur.remove(cur.size() - 1);
        this.backtrack2(nums, index + 1, cur, resultList);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        long start = System.currentTimeMillis();
        List<List<Integer>> result = null;
        result = new Subsets().subsets(nums);
        long end = System.currentTimeMillis();
        System.out.println("结果：" + result);
        System.out.println("时间(ms)：" + (end - start));
    }

    private List<List<Integer>> subsets;
    private List<Integer> subset;

    public List<List<Integer>> subsets(int[] nums) {
        subsets = new ArrayList<>();
        subset = new ArrayList<>();
        backtrack(nums, nums.length, 0);
        return subsets;
    }

    private void backtrack(int[] nums, int n, int i) {
        if (i == n) {
            subsets.add(new ArrayList<>(subset));
            return;
        }
        backtrack(nums, n, i + 1);
        subset.add(nums[i]);
        backtrack(nums, n, i + 1);
        subset.remove(subset.size() - 1);
    }
}
