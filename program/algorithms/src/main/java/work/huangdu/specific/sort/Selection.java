package work.huangdu.specific.sort;

import java.util.Arrays;

/**
 * 选择排序
 * 时间复杂度
 * 平均 O(n^2) 最好 O(n^2) 最坏 O(n^2)
 * 空间复杂度
 * O(1)
 * 不稳定
 * 选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/16 18:53
 */
public class Selection {
    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums.length - 1; i++) {
            int min = nums[i];
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (min > nums[j]) {
                    //if (nums[minIndex] > nums[j]) {
                    min = nums[j];
                    minIndex = j;
                }
            }
            //int min = nums[minIndex];
            nums[minIndex] = nums[i];
            nums[i] = min;
        }
        long end = System.currentTimeMillis();
        System.out.printf("选择排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }
}
