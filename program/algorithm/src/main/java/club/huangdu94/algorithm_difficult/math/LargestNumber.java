package club.huangdu94.algorithm_difficult.math;

import java.util.Arrays;
import java.util.Collections;

/**
 * 最大数
 * 给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。
 * 示例 1:
 * 输入: [10,2]
 * 输出: 210
 * 示例 2:
 * 输入: [3,30,34,5,9]
 * 输出: 9534330
 * 说明: 输出结果可能非常大，所以你需要返回一个字符串而不是整数。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/6 16:28
 */
public class LargestNumber {
    public String largestNumber(int[] nums) {
        String[] numStrings = new String[nums.length];
        for (int i = 0; i < nums.length; i++)
            numStrings[i] = Integer.toString(nums[i]);
        Arrays.sort(numStrings, Collections.reverseOrder());
        StringBuilder sb = new StringBuilder();
        for (String numString : numStrings)
            sb.append(numString);
        return sb.toString();
    }
    // 读错题
    /*
    public String largestNumber(int[] nums) {
        int[] count = new int[10];
        for (int num : nums)
            if (num == 0) count[0]++;
            else
                while (num != 0) {
                    count[num % 10]++;
                    num /= 10;
                }
        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--)
            while (count[i]-- > 0) sb.append(i);
        return sb.toString();
    }*/
}
