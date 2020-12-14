package work.huangdu.exploration.start_from_scratch.double_pointer.sliding_window;

import java.util.Arrays;

/**
 * 611. 有效三角形的个数
 * 给定一个包含非负整数的数组，你的任务是统计其中可以组成三角形三条边的三元组个数。
 * 示例 1:
 * 输入: [2,2,3,4]
 * 输出: 3
 * 解释:
 * 有效的组合是:
 * 2,3,4 (使用第一个 2)
 * 2,3,4 (使用第二个 2)
 * 2,2,3
 * 注意:
 * 数组长度不超过1000。
 * 数组里整数的范围为 [0, 1000]。
 *
 * @author duhuang@iflytek.com
 * @version 2020/12/14 12:59
 */
public class TriangleNumber {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length, count = 0;
        for (int first = 0; first < n; first++) {
            if (nums[first] == 0) continue;
            int firstLen = nums[first], second = first + 1, third = second + 1;
            while (second + 1 < n) {
                int secondLen = nums[second];
                while (third < n && firstLen + secondLen > nums[third]) {
                    third++;
                }
                count += (third - second - 1);
                second++;
            }
        }
        return count;
    }
}
