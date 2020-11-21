package work.huangdu.exploration.start_from_scratch.greedy.stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 456. 132模式
 * 给定一个整数序列：a1, a2, ..., an，一个132模式的子序列 ai, aj, ak 被定义为：当 i < j < k 时，ai < ak < aj。设计一个算法，当给定有 n 个数字的序列时，验证这个序列中是否含有132模式的子序列。
 * 注意：n 的值小于15000。
 * 示例1:
 * 输入: [1, 2, 3, 4]
 * 输出: False
 * 解释: 序列中不存在132模式的子序列。
 * 示例 2:
 * 输入: [3, 1, 4, 2]
 * 输出: True
 * 解释: 序列中有 1 个132模式的子序列： [1, 4, 2].
 * 示例 3:
 * 输入: [-1, 3, 2, 0]
 * 输出: True
 * 解释: 序列中有 3 个132模式的的子序列: [-1, 3, 2], [-1, 3, 0] 和 [-1, 2, 0].
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/21 14:34
 */
public class Find132pattern {
    public boolean find132pattern(int[] nums) {
        // 0. 如果数组长度小于3，一定返回false
        if (nums.length < 3) return false;
        Deque<int[]> stack = new ArrayDeque<>();
        int n = nums.length, first = nums[0], second = Integer.MIN_VALUE;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            if (num < first && second == Integer.MIN_VALUE) {
                // 1. 如果first比num要大，并且此时second还没有，则直接更新first值
                first = num;
            } else if (num < first && second != Integer.MIN_VALUE) {
                // 2. 如果first比num要大，并且此时second有的话
                // 那就需要对原来的first，second压栈，并更新更新first值，second值置为最小
                stack.push(new int[]{first, second});
                first = num;
                second = Integer.MIN_VALUE;
            } else if (num > first && num < second) {
                // 3. 如果first比num要小，而num比second要小，则直接返回true
                return true;
            } else if (num > first && num > second) {
                // 4.1. 如果first比num要小，而second比num也要小
                // 查看栈内元素的second，要知道从栈底到顶，first一定是依次减小的，因为只有num比first更小的时候才会去更新first
                // 所以说如果num不小于栈顶元素的second，则该数据没必要再存在了，直接弹出就好了
                // 因为更新后当前first，second的范围包含栈中元素的范围，栈中元素就没必要存在
                while (!stack.isEmpty() && stack.peek()[1] <= num) {
                    stack.pop();
                }
                // 4.2. 此时如果栈不为空，且num比栈顶元素的first要大，则返回true
                if (!stack.isEmpty() && num > stack.peek()[0]) {
                    return true;
                }
                // 4.3. 更新second
                second = num;
            }
        }
        return false;
    }
}
