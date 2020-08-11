package club.huangdu94.algorithm_difficult.treeGraph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 计算右侧小于当前元素的个数
 * 给定一个整数数组 nums，按要求返回一个新数组counts。数组 counts 有该性质： counts[i] 的值是 nums[i] 右侧小于nums[i] 的元素的数量。
 * 示例：
 * 输入：[5,2,6,1]
 * 输出：[2,1,1,0]
 * 解释：
 * 5 的右侧有 2 个更小的元素 (2 和 1)
 * 2 的右侧仅有 1 个更小的元素 (1)
 * 6 的右侧有 1 个更小的元素 (1)
 * 1 的右侧有 0 个更小的元素
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/11 11:33
 */
public class CountSmaller {
    /**
     * 暴力解思路：(超出时间限制)
     * 0. nums为null或nums.length==0则返回空list，否则进入1
     * 1. nums从后向前计算，res[nums.length-1]=0
     * ...
     * 2. 计算res[i-1]
     * 2.0 k=i-1
     * 2.1 k++，如果nums[i-1]>nums[k] res[i-1]++(直到k>nums.length-1)
     */
    public List<Integer> countSmaller2(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int len = nums.length;
        List<Integer> res = new ArrayList<>(len);
        for (int i = 0; i < len; i++) res.add(0);
        for (int i = len - 2; i >= 0; i--) {
            int count = 0;
            for (int k = i + 1; k < len; k++)
                if (nums[i] > nums[k]) count++;
            res.set(i, count);
        }
        return res;
    }

    /**
     * 反向插入排序(155 ms,在所有Java提交中击败了8.66%的用户)
     */
    public List<Integer> countSmaller(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();
        int len = nums.length;
        List<Integer> res = new LinkedList<>();
        res.add(0, 0);
        for (int i = len - 2, j = i; i >= 0; j = --i) {
            int numi = nums[i];
            while (numi <= nums[j + 1]) {
                nums[j] = nums[j + 1];
                if (++j == len - 1)
                    break;
            }
            nums[j] = numi;
            res.add(0, len - j - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        CountSmaller countSmaller = new CountSmaller();
        int[] nums = {2, 0, 1};
        List<Integer> res = countSmaller.countSmaller(nums);
        System.out.println(res);
    }
}
