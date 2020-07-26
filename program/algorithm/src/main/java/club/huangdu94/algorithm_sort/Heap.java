package club.huangdu94.algorithm_sort;

import java.util.Arrays;

/**
 * 堆排序
 * 时间复杂度
 * 平均 O(nlog2 n) 最好 O(nlog2 n) 最坏 O(nlog2 n)
 * 空间复杂度
 * O(1)
 * 不稳定
 * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。堆积是一个近似完全二叉树的结构，并同时满足堆积的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/17 23:34
 */
public class Heap {
    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        int len = nums.length;
        buildMaxHeap(nums, len);
        for (int i = len - 1; i > 0; i--) {
            int temp = nums[i];
            nums[i] = nums[0];
            nums[0] = temp;
            heapify(nums, --len, 0);
        }
        long end = System.currentTimeMillis();
        System.out.println(String.format("堆排序耗时: %s ms", end - start));
        System.out.println(String.format("排序结果: %s", Arrays.toString(nums)));
    }

    /**
     * 构造大顶堆
     * 根 n 左子树 2n 右子树 2n+1
     * 转换为index要减一,根 n-1 左子树 2(n-1)+1 右 2(n-1)+2
     * 由子节点index找根节点index公式:root=(child+1)/2-1
     * 找规律可得所有有子节点的index范围为 nums.length/2-1 -> 0
     */
    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(arr, len, i);
        }
    }

    /**
     * 调整以index为根的子树为大顶堆
     */
    private static void heapify(int[] arr, int len, int index) {
        int l = 2 * index + 1;
        int r = 2 * index + 2;
        int largeIndex = index;
        if (l < len && arr[l] > arr[largeIndex])
            largeIndex = l;
        if (r < len && arr[r] > arr[largeIndex])
            largeIndex = r;
        if (largeIndex != index) {
            int temp = arr[index];
            arr[index] = arr[largeIndex];
            arr[largeIndex] = temp;
            // largeIndex位置数改变,所以以其为根的子大顶堆重新调整
            heapify(arr, len, largeIndex);
        }
    }
}
