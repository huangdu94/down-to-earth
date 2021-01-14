package work.huangdu.exploration.advanced_algorithms.tree_graph;

import work.huangdu.data_structure.BinaryIndexedTree;
import work.huangdu.data_structure.Discretization;

import java.util.*;

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
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/11 11:33
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
    public List<Integer> countSmaller1(int[] nums) {
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
    public List<Integer> countSmaller2(int[] nums) {
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
        int[] nums = {5, 2, 6, 1};
        List<Integer> res = countSmaller.countSmaller(nums);
        System.out.println(res);
    }

    /**
     * 离散化树状数组
     */
    public List<Integer> countSmaller3(int[] nums) {
        int len = nums.length;
        List<Integer> resultList = new ArrayList<>(len);
        // 离散化: 去重 and 排序
        Discretization discretization = new Discretization(nums);
        // 初始化树状数组
        BinaryIndexedTree biTree = new BinaryIndexedTree(discretization.length());
        // 从后往前遍历原数组
        for (int i = len - 1; i >= 0; --i) {
            // 二分查找得到离散id
            int id = discretization.getId(nums[i]);
            // 所有小于id的元素数量和加入结果list
            resultList.add(biTree.query(id - 1));
            // 根据id更新数量(所有包含id的和都加1)
            biTree.update(id);
        }
        // 反转结果list
        Collections.reverse(resultList);
        return resultList;
    }

    private int[] index;

    private int[] ans;

    /**
     * 归并排序
     * 普通的归并排序稍微附加了一些技巧用于解题
     * 1. tempIndex 用于记录index在归并排序原数组元素顺序变化后，保证最初的index记录下来
     * 2. 结果list增加的时机
     * 2.1 归并中右边比左边大的时候，左边当前i对应的结果list需要增加
     * 2.2 增加的值为此时归并排序右边一半已经完成排序的个数j - (mid + 1)
     * 因为归并排序过程中虽然index乱了，但是始终会保证左半边的index都是小于右半边的
     * 所以可以保证结果的正确性
     */
    private void Merge(int[] nums, int l, int m, int r) {
        int i = l, j = m + 1;
        int[] temp = Arrays.copyOfRange(nums, l, r + 1);
        int[] tempIndex = Arrays.copyOfRange(index, l, r + 1);
        for (int k = l; k < r + 1; k++) {
            if (i > m) {
                index[k] = tempIndex[j - l];
                // 左半部分元素已经全部处理完毕
                nums[k] = temp[j++ - l];
            } else if (j > r) {
                // 此时j一定为 r+1
                ans[tempIndex[i - l]] += (r - m);
                index[k] = tempIndex[i - l];
                // 右半部分元素已经全部处理完毕
                nums[k] = temp[i++ - l];
            } else if (temp[i - l] <= temp[j - l]) {
                ans[tempIndex[i - l]] += (j - m - 1);
                index[k] = tempIndex[i - l];
                // 左半部分所指元素<=右半部分所指元素
                nums[k] = temp[i++ - l];
            } else {
                index[k] = tempIndex[j - l];
                nums[k] = temp[j++ - l];
            }
        }
    }

    private void MergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = (l + r) >> 1;
        MergeSort(nums, l, mid);
        MergeSort(nums, mid + 1, r);
        Merge(nums, l, mid, r);
    }

    public List<Integer> countSmaller(int[] nums) {
        int len = nums.length;
        this.index = new int[len];
        this.ans = new int[len];
        for (int i = 0; i < len; ++i) {
            index[i] = i;
        }
        int l = 0, r = len - 1;
        MergeSort(nums, l, r);
        List<Integer> res = new ArrayList<>(len);
        for (int n : ans) {
            res.add(n);
        }
        return res;
    }
}
