package work.huangdu.specific.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基数排序
 * 时间复杂度
 * 平均 O(n*k) 最好 O(n*k) 最坏 O(n*k)
 * 空间复杂
 * O(n+k)
 * 稳定
 * 基数排序是按照低位先排序，然后收集；再按照高位排序，然后再收集；依次类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序。最后的次序就是高优先级高的在前，高优先级相同的低优先级高的在前。
 * 难以支持负数
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/18 19:32
 */
public class Radix {
    private static final int[] maxDigitTable = {9, 99, 999, 9999, 99999, 999999, 9999999,
            99999999, 999999999, Integer.MAX_VALUE};

    public static void sort(int[] nums) {
        long start = System.currentTimeMillis();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int n : nums) {
            if (n > max)
                max = n;
            if (n < min)
                min = n;
        }
        // 支持负数(将所有数转化为>=0的数排序，有溢出风险)
        max -= min;
        int maxDigit;
        for (int i = 0; ; i++) {
            if (Math.abs(max) <= maxDigitTable[i]) {
                maxDigit = i + 1;
                break;
            }
        }
        List<List<Integer>> preList = null;
        List<List<Integer>> curList = null;
        int pos = 1;
        while (maxDigit-- > 0) {
            curList = new ArrayList<List<Integer>>(10) {
                {
                    for (int i = 0; i < 10; i++) {
                        add(new ArrayList<>());
                    }
                }
            };
            if (pos == 1) {
                for (int n : nums) {
                    int radix = (n - min) / pos % 10;
                    curList.get(radix).add(n);
                }
            } else {
                for (List<Integer> numList : preList) {
                    for (int n : numList) {
                        int radix = (n - min) / pos % 10;
                        curList.get(radix).add(n);
                    }
                }
            }
            preList = curList;
            pos *= 10;
        }
        int k = 0;
        for (List<Integer> numList : curList) {
            for (int n : numList) {
                nums[k++] = n;
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("基数排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }
}
