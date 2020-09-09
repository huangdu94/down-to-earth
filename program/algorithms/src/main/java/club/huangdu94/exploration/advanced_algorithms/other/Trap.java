package club.huangdu94.exploration.advanced_algorithms.other;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 接雨水
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
    public int trap(int[] height) {
        int len = height.length, volume = 0, maxLeft = 0;
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            int minSide = Math.min(height[maxLeft], height[i]), right = i, prevHeight = -1;
            while (!monoStack.isEmpty() && height[monoStack.peek()] < height[i]) {
                int prevIndex = monoStack.pop();
                volume += (minSide - height[prevIndex]);
                if (right - prevIndex > 1) {
                    volume += (minSide - prevHeight) * (right - prevIndex - 1);
                }
                prevHeight = height[prevIndex];
                right = prevIndex;
            }
            if (monoStack.isEmpty()) maxLeft = i;
            monoStack.push(i);
        }
        return volume;
    }

    public static void main(String[] args) {
        Trap trap = new Trap();
        int[] heights = {4, 2, 0, 3, 2, 4, 3, 4};
        System.out.println(trap.trap(heights));
    }
}
