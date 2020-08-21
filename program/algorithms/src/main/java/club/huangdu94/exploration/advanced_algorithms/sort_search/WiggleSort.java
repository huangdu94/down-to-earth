package club.huangdu94.exploration.advanced_algorithms.sort_search;

import java.util.Arrays;

/**
 * 摆动排序 II
 * 给定一个无序的数组nums，将它重新排列成nums[0] < nums[1] > nums[2] < nums[3]...的顺序。
 * 示例1:
 * 输入: nums = [1, 5, 1, 1, 6, 4]
 * 输出: 一个可能的答案是 [1, 4, 1, 5, 1, 6]
 * 示例 2:
 * 输入: nums = [1, 3, 2, 2, 3, 1]
 * 输出: 一个可能的答案是 [2, 3, 1, 3, 1, 2]
 * 说明:
 * 你可以假设所有输入都会得到有效的结果。
 * 进阶:
 * 你能用O(n) 时间复杂度和 / 或原地 O(1) 额外空间来实现吗？
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/21 11:37
 */
public class WiggleSort {
    /*
        如果把nums分成两半，小的一半放在index为偶数位置，大的一半放在index为奇数位置则肯定满足题目要求
        1. 分析后转换问题为，求第nums.length/2+1个最大元素记为i（小于等于它的数放在index为偶数位置即可）
        以上不管nums.length为偶数还是奇数都成立
        2. 采用双指针i和j，i指向index为偶数位置，j指向index为奇数位置
        找到nums[i]>i的位置，找到nums[j]<=i的位置，交换它们，直到i或j到达末尾
        时间复杂度o(n) 空间复杂度o(1)
     */
    public void wiggleSort(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        int len = nums.length, i = 0, j = 1;
        // 1. 求第nums.length/2+1个最大元素，采用改版的快速排序(每次只排目标区域)，时间复杂度o(n)
        int mid = specialQuickSort(nums, 0, len - 1, len - (len / 2 + 1));
        // 2. 双指针法换位时间复杂度o(n)
        while (j < len) {
            while (j < len && nums[j] >= mid) {
                j += 2;
            }
            // 如果j>=len了i就没有必要再遍历了
            if (j < len) {
                while (i < len && nums[i] < mid) {
                    i += 2;
                }
                // 此处不需要判断，因为肯定有i满足条件
                swap(nums, i, j);
            }
        }
        // 3. 经过1和2处理后可以保证比i小的数都在偶数位置上（并且在i之后，i和i之前的数需要放上i）
        // 记录i位置，偶数位置上i和i之后肯定都为i
        j = 1;
        while (i < len) {
            while (i < len && nums[i] <= mid) {
                i += 2;
            }
            // 如果i>=len了j就没有必要再遍历了
            if (i < len) {
                while (j < len && nums[j] != mid) {
                    j += 2;
                }
                // 此处不需要判断，因为肯定有j满足条件
                swap(nums, i, j);
            }
        }
        // 4. 经过1、2和3后，偶数位置的数还是有可能等于奇数位置的数，所以还要进行一轮处理o(n)
        // (在第nums.length/2+1个最大元素等于nums.length/2+2个最大元素的情况下)
        // 5. 将奇数位的i都调到末尾
        i = j;
        j = len - 1 - len % 2;
        while (i < j) {
            while (i < j && nums[j] == mid) {
                j -= 2;
            }
            while (i < j && nums[i] != mid) {
                i += 2;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
        // 6. 将偶数位的i都调到开头
        i = 0;
        j = len - 2 + len % 2;
        while (i < j) {
            while (i < j && nums[j] != i) {
                j -= 2;
            }
            while (i < j && nums[i] == i) {
                i += 2;
            }
            if (i < j) {
                swap(nums, i, j);
            }
        }
    }

    /*private int specialQuickSort(int[] nums, int l, int r, int k) {
        int i = l, pivot = nums[l];
        for (int i = l + 1; i < r + 1; i++) {
            if (nums[i] < pivot) {
                swap(nums, i, ++i);
            }
        }
        nums[l] = nums[i];
        nums[i] = pivot;
        if (i == k) return pivot;
        if (i < k)
            return specialQuickSort(nums, i + 1, r, k);
        else
            return specialQuickSort(nums, l, i - 1, k);
    }*/

    private int specialQuickSort(int[] nums, int l, int r, int k) {
        if (l >= r) return nums[k];
        int pivot = nums[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && nums[j] >= pivot) j--;
            while (i < j && nums[i] <= pivot) i++;
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        // 基准数归位
        nums[l] = nums[i];
        nums[i] = pivot;
        if (i < k)
            return specialQuickSort(nums, i + 1, r, k);
        else
            return specialQuickSort(nums, l, i - 1, k);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        WiggleSort wiggleSort = new WiggleSort();
        int[] nums = {4, 5, 5, 6};
        wiggleSort.wiggleSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}