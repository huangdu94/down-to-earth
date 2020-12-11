package work.huangdu.exploration.advanced_algorithms.other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 42. 接雨水
 * 给定n个非负整数表示每个宽度为1的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * 上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。感谢 Marcos 贡献此图。
 * 示例:
 * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出: 6
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/8 11:43
 */
public class Trap {
    public int trap1(int[] height) {
        int volume = 0, leftHeight = 0;
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            int minSide = Math.min(leftHeight, height[i]);
            while (!monoStack.isEmpty() && height[monoStack.peek()] <= height[i]) {
                int index = monoStack.pop();
                if (monoStack.isEmpty()) break;
                volume += (minSide - height[index]) * (index - monoStack.peek());
            }
            if (monoStack.isEmpty()) leftHeight = height[i];
            monoStack.push(i);
        }
        return volume;
    }

    public int trap2(int[] height) {
        int volume = 0;
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < height.length; i++) {
            while (!monoStack.isEmpty() && height[monoStack.peek()] <= height[i]) {
                int index = monoStack.pop();
                if (monoStack.isEmpty()) break;
                volume += (Math.min(height[monoStack.peek()], height[i]) - height[index]) * (i - monoStack.peek() - 1);
            }
            monoStack.push(i);
        }
        return volume;
    }

    public int trap(int[] height) {
        int volume = 0, i = 0, j = height.length - 1, left = 0, right = 0;
        while (i < j) {
            if (height[i] <= height[j]) {
                if (left < height[i]) left = height[i];
                else if (left > height[i]) volume += (left - height[i]);
                i++;
            } else {
                if (right < height[j]) right = height[j];
                else if (right > height[j]) volume += (right - height[j]);
                j--;
            }
        }
        return volume;
    }

    public static void main(String[] args) {
        Trap trap = new Trap();
        int[] heights = {5, 4, 1, 2};
        System.out.println(trap.trap(heights));
    }

    public int trap5(int[] height) {
        int n = height.length, top = 0, result = 0;
        int[] stack = new int[n];
        for (int i = 0; i < n; i++) {
            int h = height[i], bound = Math.min(height[stack[0]], h);
            while (top != 0 && h >= height[stack[top - 1]]) {
                if (top > 1) {
                    result += (bound - height[stack[top - 1]]) * (stack[top - 1] - stack[top - 2]);
                }
                top--;
            }
            stack[top++] = i;
        }
        return result;
    }
}
