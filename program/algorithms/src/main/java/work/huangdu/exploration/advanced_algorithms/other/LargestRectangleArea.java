package work.huangdu.exploration.advanced_algorithms.other;

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
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/8 12:42
 */
public class LargestRectangleArea {
    public int largestRectangleArea1(int[] heights) {
        int n = heights.length, max = 0;
        for (int i = 0; i < n; i++) {
            int minHeight = heights[i];
            for (int j = i; j < n; j++) {
                if (minHeight > heights[j]) {
                    minHeight = heights[j];
                }
                int area = minHeight * (j - i + 1);
                if (max < area) {
                    max = area;
                }
            }
        }
        return max;
    }

    public int largestRectangleArea2(int[] heights) {
        int n = heights.length, max = -1;
        for (int i = 0; i < n; i++) {
            int height = heights[i], left = i, right = i, area;
            while (left - 1 >= 0 && heights[left - 1] >= height) {
                left--;
            }
            while (right + 1 < n && heights[right + 1] >= height) {
                right++;
            }
            area = height * (right - left + 1);
            if (area > max) {
                max = area;
            }
        }
        return max;
    }

    public int largestRectangleArea3(int[] heights) {
        int n = heights.length, maxArea = 0;
        int[] left = new int[n];
        int[] right = new int[n];
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            left[i] = monoStack.isEmpty() ? -1 : monoStack.peek();
            monoStack.push(i);
        }
        monoStack.clear();
        for (int i = n - 1; i >= 0; i--) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                monoStack.pop();
            }
            right[i] = monoStack.isEmpty() ? n : monoStack.peek();
            monoStack.push(i);
        }
        for (int i = 0; i < n; i++) {
            int area = heights[i] * (right[i] - left[i] - 1);
            if (maxArea < area) maxArea = area;
        }
        return maxArea;
    }

    public int largestRectangleArea(int[] heights) {
        int n = heights.length, maxArea = 0;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(right, n);
        Deque<Integer> monoStack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!monoStack.isEmpty() && heights[monoStack.peek()] >= heights[i]) {
                right[monoStack.pop()] = i;
            }
            left[i] = monoStack.isEmpty() ? -1 : monoStack.peek();
            monoStack.push(i);
        }
        for (int i = 0; i < n; i++) {
            int area = heights[i] * (right[i] - left[i] - 1);
            if (maxArea < area) maxArea = area;
        }
        return maxArea;
    }

    public static void main(String[] args) {
        LargestRectangleArea area = new LargestRectangleArea();
        //int[] heights = {2, 1, 5, 6, 2, 3};
        int[] heights = {1, 1, 3, 3, 3, 1};
        System.out.println(area.largestRectangleArea(heights));
    }
}
