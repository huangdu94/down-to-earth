package work.huangdu.exploration.start_from_scratch.greedy.subsequence;

/**
 * 659. 分割数组为连续子序列
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个子序列，其中每个子序列都由连续整数组成且长度至少为 3 。
 * 如果可以完成上述分割，则返回 true ；否则，返回 false 。
 * 示例 1：
 * 输入: [1,2,3,3,4,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3
 * 3, 4, 5
 * 示例 2：
 * 输入: [1,2,3,3,4,4,5,5]
 * 输出: True
 * 解释:
 * 你可以分割出这样两个连续子序列 :
 * 1, 2, 3, 4, 5
 * 3, 4, 5
 * 示例 3：
 * 输入: [1,2,3,4,4,5]
 * 输出: False
 * 提示：
 * 输入的数组长度范围为 [1, 10000]
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/19 18:35
 */
public class IsPossible {
    public boolean isPossible(int[] nums) {
        int n = nums.length, i = 0;
        if (n < 3) return false;
        // 当前长度为1，2的序列的数量，当前能用的多于2的序列数量
        int one = 0, two = 0, more = 0;
        // 初始化one
        while (i < n && nums[i] == nums[0]) {
            one++;
            i++;
        }
        while (i < n) {
            int pre = nums[i - 1];
            // 下一个数与上一个数断了的情况，即count为0的特殊情况
            if (nums[i] - pre > 1) {
                // 有one或two则直接返回false
                if (one > 0 || two > 0) {
                    return false;
                }
                // more全部断
                more = 0;
                int start = nums[i];
                // 初始化one
                while (i < n && nums[i] == start) {
                    one++;
                    i++;
                }
            } else {
                int count = 0;
                // 查找比上一个数大1的数的数量
                while (i < n && nums[i] == pre + 1) {
                    count++;
                    i++;
                }
                int remain = count - one - two, temp;
                if (remain < 0) {
                    // 长度1和长度为2的是一定需要被满足的，所以说如果count不够，返回false
                    return false;
                } else {
                    // 满足长度1和2后，贪心算法，如果有more没必要另起一个one，所以remain尽可能给more
                    // 原来的two也变成more，remain如果小于more，那么有一部分more断了不再讨论
                    temp = remain - more;
                    more = Math.min(remain, more) + two;
                }
                // 原来的one变成two
                two = one;
                // 如果remain大于more，则多的另起one，否则没有one
                one = Math.max(0, temp);
            }
        }
        return one + two == 0;
    }

    public static void main(String[] args) {
        IsPossible isPossible = new IsPossible();
        System.out.println(isPossible.isPossible(new int[]{1, 2, 3, 4, 6, 7, 8, 9, 10, 11}));
    }
}
