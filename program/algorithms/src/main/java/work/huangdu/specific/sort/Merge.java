package work.huangdu.specific.sort;

import java.util.Arrays;

/**
 * 归并排序
 * 时间复杂度
 * 平均 O(nlog2 n) 最好 O(nlog2 n) 最坏 O(nlog2 n)
 * 空间复杂度
 * O(n)
 * 稳定
 * 归并排序是建立在归并操作上的一种有效的排序算法。该算法是采用分治法（Divide and Conquer）的一个非常典型的应用。将已有序的子序列合并，得到完全有序的序列；即先使每个子序列有序，再使子序列段间有序。若将两个有序表合并成一个有序表，称为2-路归并。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/17 11:44
 */
public class Merge {
    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        sort(nums, 0, nums.length - 1);
        long end = System.currentTimeMillis();
        System.out.printf("归并排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }

    private static void sort(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int m = (r + l) / 2;
        sort(arr, l, m);
        sort(arr, m + 1, r);
        merge(arr, l, m, r);
    }

    //将arr[l...mid] 和arr[mid+1....r] 两部分进行归并
    private static void merge(int[] arr, int l, int m, int r) {
        int[] original = Arrays.copyOfRange(arr, l, r + 1);
        //初始化，i指向左半部分的起始；j指向右半部分其实索引位置mid+1
        int i = l, j = m + 1;
        for (int k = l; k < r + 1; k++) {
            if (i > m) {
                //左半部分元素已经全部处理完毕
                arr[k] = original[j++ - l];
            } else if (j > r) {
                //右半部分元素已经全部处理完毕
                arr[k] = original[i++ - l];
            } else if (original[i - l] <= original[j - l]) {
                //左半部分所指元素<=右半部分所指元素
                arr[k] = original[i++ - l];
            } else {
                arr[k] = original[j++ - l];
            }
        }
    }
}