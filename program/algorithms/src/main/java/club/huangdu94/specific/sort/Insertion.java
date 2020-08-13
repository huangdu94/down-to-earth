package club.huangdu94.specific.sort;

import java.util.Arrays;

/**
 * 插入排序
 * 时间复杂度
 * 平均 O(n^2) 最好 O(n) 最坏 O(n^2)
 * 空间复杂度
 * O(1)
 * 稳定
 * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/16 19:10
 */
public class Insertion {
    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums.length - 1; i++) {
            int temp = nums[i + 1];
            int j = i;
            while (temp < nums[j]) {
                nums[j + 1] = nums[j];
                if (--j < 0)
                    break;
            }
            nums[j + 1] = temp;
        }
        long end = System.currentTimeMillis();
        System.out.printf("插入排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }
}
