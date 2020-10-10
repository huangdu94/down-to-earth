package work.huangdu.exploration.intermediate_algorithms.sort_search;

import java.util.Arrays;

/**
 * 颜色分类
 * 给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 * 注意:
 * 不能使用代码库中的排序函数来解决这道题。
 * 示例:
 * 输入: [2,0,2,1,1,0]
 * 输出: [0,0,1,1,2,2]
 * 进阶：
 * 一个直观的解决方案是使用计数排序的两趟扫描算法。
 * 首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/12 15:45
 */
public class SortColors {
    public void sortColors(int[] nums) {
        if (nums == null || nums.length <= 1)
            return;
        int len = nums.length;
        int first = 0;
        int last = len - 1;
        while (first < last && nums[first] == 0) {
            first++;
        }
        if (first == last)
            return;
        while (last > first && nums[last] == 2) {
            last--;
        }
        if (last == first)
            return;
        while (true) {
            if (nums[first] == 2 && nums[last] == 0) {
                nums[first] = 0;
                nums[last] = 2;
                while (first < last && nums[first] == 0) {
                    first++;
                }
                if (first == last)
                    return;
                while (last > first && nums[last] == 2) {
                    last--;
                }
                if (last == first)
                    return;
            } else if (nums[first] == 2 && nums[last] == 1) {
                nums[first] = 1;
                nums[last] = 2;
                while (last > first && nums[last] == 2) {
                    last--;
                }
                if (last == first)
                    return;
            } else if (nums[first] == 1 && nums[last] == 0) {
                nums[first] = 0;
                nums[last] = 1;
                while (first < last && nums[first] == 0) {
                    first++;
                }
                if (first == last)
                    return;
            } else if (nums[first] == 1 && nums[last] == 1) {
                int i = first + 1;
                while (i < last && nums[i] == 1) {
                    i++;
                }
                if (i == last)
                    return;
                if (nums[i] == 0) {
                    nums[first] = 0;
                    nums[i] = 1;
                    if (++first == last)
                        return;
                } else if (nums[i] == 2) {
                    nums[i] = 1;
                    nums[last] = 2;
                    while (last > first && nums[last] == 2) {
                        last--;
                    }
                    if (last == first)
                        return;
                }
            }
        }
    }

    public void sortColors2(int[] nums) {
        int n = nums.length;
        // 三指针
        int i = 0, j = n - 1, k = 0;
        while (k < j) {
            while (i < j && nums[i] == 0) {
                i++;
            }
            while (i < j && nums[j] == 2) {
                j--;
            }
            k = i;
            while (k <= j && nums[k] == 1) {
                k++;
            }
            if (k < n && nums[k] == 0 && i < k) {
                nums[k] = nums[i];
                nums[i] = 0;
            } else if (k < j && nums[k] == 2) {
                nums[k] = nums[j];
                nums[j] = 2;
            }
        }
    }

    public static void main(String[] args) {
//        int[] nums = {2, 0, 2, 1, 1, 0};
        int[] nums = {1, 1};
        long start = System.currentTimeMillis();
        new SortColors().sortColors2(nums);
        long end = System.currentTimeMillis();
        System.out.println("结果：" + Arrays.toString(nums));
        System.out.println("时间(ms)：" + (end - start));
    }
}
