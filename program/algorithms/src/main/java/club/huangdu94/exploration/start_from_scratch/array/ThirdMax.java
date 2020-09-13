package club.huangdu94.exploration.start_from_scratch.array;

/**
 * 414. 第三大的数
 * 给定一个非空数组，返回此数组中第三大的数。如果不存在，则返回数组中最大的数。要求算法时间复杂度必须是O(n)。
 * 示例 1:
 * 输入: [3, 2, 1]
 * 输出: 1
 * 解释: 第三大的数是 1.
 * 示例 2:
 * 输入: [1, 2]
 * 输出: 2
 * 解释: 第三大的数不存在, 所以返回最大的数 2 .
 * 示例 3:
 * 输入: [2, 2, 3, 1]
 * 输出: 1
 * 解释: 注意，要求返回第三大的数，是指第三大且唯一出现的数。
 * 存在两个值为2的数，它们都排第二。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/13 14:32
 */
public class ThirdMax {
    public int thirdMax(int[] nums) {
        long max = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE;
        for (int num : nums) {
            if (max < num) {
                third = second;
                second = max;
                max = num;
            } else if (max == num) {
            } else if (second < num) {
                third = second;
                second = num;
            } else if (second == num) {
            } else if (third < num) {
                third = num;
            }
        }
        return (int) (third == Long.MIN_VALUE ? max : third);
    }
}