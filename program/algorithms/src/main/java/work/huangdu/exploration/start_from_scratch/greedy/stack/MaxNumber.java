package work.huangdu.exploration.start_from_scratch.greedy.stack;

/**
 * 321. 拼接最大数
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 * 示例 1:
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/12/2 18:49
 */
public class MaxNumber {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int[] result = null;
        for (int i = Math.max(0, k - n), j = k - i, end = Math.min(k, m); i <= end; i++, j--) {
            int[] merged = merge(getBigNumbers(nums1, m, i), i, getBigNumbers(nums2, n, j), j, k);
            if (result == null || compare(merged, k, 0, result, k, 0) > 0) {
                result = merged;
            }
        }
        return result;
    }

    /**
     * 从nums中筛选出k个数（保持在原数组中的顺序）
     * 这k个数是所有组合中最大的
     *
     * @param nums 原数组
     * @param n    原数组长度
     * @param k    目标数量
     * @return 结果数组
     */
    private int[] getBigNumbers(int[] nums, int n, int k) {
        if (k == 0) {
            return new int[0];
        }
        int[] stack = new int[k];
        int top = 0, remain = n - k;
        for (int num : nums) {
            while (top > 0 && remain > 0 && stack[top - 1] < num) {
                top--;
                remain--;
            }
            if (top < k) {
                stack[top++] = num;
            } else {
                remain--;
            }
        }
        return stack;
    }

    /**
     * 合并两数组为最大的数
     *
     * @param nums1 数组1
     * @param m     数组1长度
     * @param nums2 数组2
     * @param n     数组2长度
     * @param k     合并后长度
     * @return 合并后数组
     */
    private int[] merge(int[] nums1, int m, int[] nums2, int n, int k) {
        if (m == k) {
            return nums1;
        }
        if (n == k) {
            return nums2;
        }
        int[] result = new int[k];
        int index = 0, index1 = 0, index2 = 0;
        while (index < k) {
            if (compare(nums1, m, index1, nums2, n, index2) >= 0) {
                result[index++] = nums1[index1++];
            } else {
                result[index++] = nums2[index2++];
            }
        }
        return result;
    }

    /**
     * 比较大小
     *
     * @param nums1  数组1
     * @param m      数组1长度
     * @param index1 数组1当前下标
     * @param nums2  数组2
     * @param n      数组2长度
     * @param index2 数组2当前下标
     * @return 比大小，前面大返回大于0
     */
    private int compare(int[] nums1, int m, int index1, int[] nums2, int n, int index2) {
        while (index1 < m && index2 < n) {
            int difference = nums1[index1] - nums2[index2];
            if (difference != 0) {
                return difference;
            }
            index1++;
            index2++;
        }
        return (m - index1) - (n - index2);
    }
}
