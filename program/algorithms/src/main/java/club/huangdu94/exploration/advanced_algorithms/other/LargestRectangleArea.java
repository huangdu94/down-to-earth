package club.huangdu94.exploration.advanced_algorithms.other;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 84. 柱状图中最大的矩形
 * 给定 n 个非负整数，用来表示柱状图中各个柱子的高度。每个柱子彼此相邻，且宽度为 1 。
 * 求在该柱状图中，能够勾勒出来的矩形的最大面积。
 * 以上是柱状图的示例，其中每个柱子的宽度为 1，给定的高度为[2,1,5,6,2,3]。
 * 图中阴影部分为所能勾勒出的最大矩形面积，其面积为10个单位。
 * 示例:
 * 输入: [2,1,5,6,2,3]
 * 输出: 10
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/8 12:42
 */
public class LargestRectangleArea {
    public int largestRectangleArea1(int[] heights) {
        int len = heights.length, maxArea = 0, minHeight;
        for (int i = 0; i < len; i++) {
            minHeight = heights[i];
            if (maxArea < minHeight) maxArea = minHeight;
            for (int j = i + 1; j < len; j++) {
                if (minHeight > heights[j]) minHeight = heights[j];
                int area = minHeight * (j - i + 1);
                if (maxArea < area) maxArea = area;
            }
        }
        return maxArea;
    }

    public int largestRectangleArea2(int[] heights) {
        int len = heights.length, maxArea = 0;
        for (int k = 0; k < len; k++) {
            int height = heights[k], i = k, j = k;
            while (i - 1 >= 0 && heights[i - 1] >= height) i--;
            while (j + 1 < len && heights[j + 1] >= height) j++;
            int area = height * (j - i + 1);
            if (maxArea < area) maxArea = area;
        }
        return maxArea;
    }

    public int largestRectangleArea3(int[] heights) {
        int len = heights.length, maxArea = 0;
        int[] left = new int[len];
        int[] right = new int[len];
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            left[i] = monoStack.isEmpty() ? -1 : monoStack.peek();
            monoStack.push(i);
        }
        monoStack.clear();
        for (int i = len - 1; i >= 0; i--) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            right[i] = monoStack.isEmpty() ? len : monoStack.peek();
            monoStack.push(i);
        }
        for (int i = 0; i < len; i++) {
            int area = heights[i] * (right[i] - left[i] - 1);
            if (maxArea < area) maxArea = area;
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        int len = heights.length, maxArea = 0;
        int[] left = new int[len];
        int[] right = new int[len];
        Arrays.fill(right, len);
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < len; i++) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                right[monoStack.pop()] = i;
            }
            left[i] = monoStack.isEmpty() ? -1 : monoStack.peek();
            monoStack.push(i);
        }
        for (int i = 0; i < len; i++) {
            int area = heights[i] * (right[i] - left[i] - 1);
            if (maxArea < area) maxArea = area;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleArea area = new LargestRectangleArea();
        //int[] heights = {2, 1, 5, 6, 2, 3};
        int[] heights = {1, 1, 1, 1, 1, 1};
        System.out.println(area.largestRectangleArea(heights));
    }
}
