package work.huangdu.exploration.start_from_scratch.greedy.stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 503. 下一个更大元素 II
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * 示例 1:
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * 注意: 输入数组的长度不会超过 10000。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/11/20 13:42
 */
public class NextGreaterElements2 {
    public int[] nextGreaterElements(int[] nums) {
        if (nums.length == 0) return new int[0];
        int n = nums.length, max = Integer.MIN_VALUE;
        int[] res = new int[n];
        Deque<int[]> stack = new ArrayDeque<>();
        // 先转一圈
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (max < num) {
                max = num;
            }
            while (!stack.isEmpty() && stack.peek()[0] < num) {
                res[stack.pop()[1]] = num;
            }
            stack.push(new int[]{num, i});
        }
        // 再从头开始转，遇到max后结束，这一轮num值没必要再压栈，因为已经赋值过了
        // 只有栈顶元素的值不为max的时候才有必要继续转
        if (stack.peek()[0] != max) {
            for (int i = 0; ; i++) {
                int num = nums[i];
                while (!stack.isEmpty() && stack.peek()[0] < num) {
                    res[stack.pop()[1]] = num;
                }
                // 遇到max后结束
                if (num == max) {
                    break;
                }
            }
        }
        // 弹出stack中剩余max赋值-1
        while (!stack.isEmpty()) {
            res[stack.pop()[1]] = -1;
        }
        return res;
    }

    public int[] nextGreaterElements2(int[] nums) {
        if (nums.length == 0) return new int[0];
        int n = nums.length, max = Integer.MIN_VALUE, top = 0;
        int[] res = new int[n], stack = new int[n];
        // 先转一圈
        for (int i = 0; i < n; i++) {
            int num = nums[i];
            if (max < num) {
                max = num;
            }
            while (top != 0 && nums[stack[top - 1]] < num) {
                res[stack[--top]] = num;
            }
            stack[top++] = i;
        }
        // 再从头开始转，遇到max后结束，这一轮num值没必要再压栈，因为已经赋值过了
        // 只有栈顶元素的值不为max的时候才有必要继续转
        if (nums[stack[top - 1]] != max) {
            for (int i = 0; ; i++) {
                int num = nums[i];
                while (top != 0 && nums[stack[top - 1]] < num) {
                    res[stack[--top]] = num;
                }
                // 遇到max后结束
                if (num == max) {
                    break;
                }
            }
        }
        // 弹出stack中剩余max赋值-1
        while (top != 0) {
            res[stack[--top]] = -1;
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {5, 3, 1, 4, 5, 6, 2, 4, 6, 9, 0, 3};
        NextGreaterElements2 nge2 = new NextGreaterElements2();
        System.out.println(Arrays.toString(nge2.nextGreaterElements(nums)));
    }
}
