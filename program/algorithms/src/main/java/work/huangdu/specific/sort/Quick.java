package work.huangdu.specific.sort;

import java.util.Arrays;

/**
 * 快速排序
 * 时间复杂度
 * 平均 O(nlog2 n) 最好 O(nlog2 n) 最坏 O(n2)
 * 空间复杂度
 * O(nlog2 n)
 * 不稳定
 * 快速排序的基本思想：通过一趟排序将待排记录分隔成独立的两部分，其中一部分记录的关键字均比另一部分的关键字小，则可分别对这两部分记录继续进行排序，以达到整个序列有序。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/17 23:09
 */
public class Quick {
    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        quick(nums, 0, nums.length - 1);
        long end = System.currentTimeMillis();
        System.out.printf("快速排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }

    public static void sort2(int[] nums) {
        long start = System.currentTimeMillis();
        quick2(nums, 0, nums.length - 1);
        long end = System.currentTimeMillis();
        System.out.printf("快速排序(版本2)耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }

    private static void quick(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int pivot = arr[l];
        int mid = l;
        for (int i = l + 1; i < r + 1; i++) {
            if (arr[i] < pivot) {
                int temp = arr[++mid];
                arr[mid] = arr[i];
                arr[i] = temp;
            }
        }
        arr[l] = arr[mid];
        arr[mid] = pivot;
        quick(arr, l, mid);
        quick(arr, mid + 1, r);
    }

    private static void quick2(int[] arr, int l, int r) {
        if (l >= r)
            return;
        int pivot = arr[l];
        int i = l, j = r;
        while (i < j) {
            // 一定要先右再左
            while (i < j && arr[j] >= pivot)
                j--;
            while (i < j && arr[i] <= pivot)
                i++;
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 基准数归位
        arr[l] = arr[i];
        arr[i] = pivot;
        quick2(arr, l, i);
        quick2(arr, i + 1, r);
    }
}
