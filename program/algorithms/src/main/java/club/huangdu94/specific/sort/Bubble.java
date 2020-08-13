package club.huangdu94.specific.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * 时间复杂度
 * 平均 O(n^2) 最好 O(n) 最坏 O(n^2)
 * 空间复杂度
 * O(1)
 * 稳定
 * 冒泡排序是一种简单的排序算法。它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来。走访数列的工作是重复地进行直到没有再需要交换，也就是说该数列已经排序完成。这个算法的名字由来是因为越小的元素会经由交换慢慢“浮”到数列的顶端。
 * 注意优化版冒泡排序时间复杂度最好才是O(n)
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/16 17:32
 */
public class Bubble {
    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("冒泡排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }

    public static void sort2(int[] nums) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = true;
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    flag = false;
                    int temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
            }
            if (flag) {
                break;
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("优化版冒泡排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }
}
