package club.huangdu94.specific.sort;

import java.util.Arrays;

/**
 * 计数排序
 * 时间复杂度
 * 平均 O(n+k) 最好 O(n+k) 最坏 O(n+k)
 * 空间复杂度
 * O(n+k)
 * 稳定
 * 计数排序不是基于比较的排序算法，其核心在于将输入的数据值转化为键存储在额外开辟的数组空间中。 作为一种线性时间复杂度的排序，计数排序要求输入的数据必须是有确定范围的整数
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/18 15:39
 */
public class Counting {
    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        if (nums.length == 0)
            return;
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max)
                max = n;
            if (n < min)
                min = n;
        }
        int[] count = new int[max - min + 1];
        for (int n : nums) {
            count[n - min]++;
        }
        int i = 0, k = 0;
        while (i < count.length) {
            if (count[i] <= 0) {
                i++;
            } else {
                nums[k++] = i + min;
                count[i]--;
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("计数排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }
}
