package club.huangdu94.algorithm_sort;

import java.util.Arrays;

/**
 * 希尔排序
 * 时间复杂度
 * 平均 O(n^1.3) 最好 O(n) 最坏 O(n^2)
 * 空间复杂度
 * O(1)
 * 不稳定
 * 1959年Shell发明，第一个突破O(n^2)的排序算法，是简单插入排序的改进版。它与插入排序的不同之处在于，它会优先比较距离较远的元素。希尔排序又叫缩小增量排序。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/16 19:54
 */
public class Shell {
    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        int len = nums.length;
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (int i = 0, j = i; i < len - gap; j = ++i) {
                int temp = nums[i + gap];
                while (temp < nums[j]) {
                    nums[j + gap] = nums[j];
                    if ((j -= gap) < 0)
                        break;
                }
                nums[j + gap] = temp;
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("希尔排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }
}
