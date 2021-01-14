package work.huangdu.exploration.advanced_algorithms.array_string;

/**
 * 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 说明：你不能倾斜容器，且 n 的值至少为 2。
 * 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 * 示例：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/27 14:12
 */
public class MaxArea {
    // 暴力解(时间复杂度o(n^2))
    public int maxArea1(int[] height) {
        int max = -1;
        for (int i = 0; i < height.length; i++)
            for (int j = 1; j < height.length; j++) {
                int area = Math.min(height[i], height[j]) * (j - i);
                if (area > max)
                    max = area;
            }
        return max;
    }

    // 暴力解(时间复杂度o(n^2)) 稍做优化,减少j-i和乘法运算
    public int maxArea2(int[] height) {
        int maxArea = -1;
        for (int wide = 1; wide < height.length; wide++) {
            int max = -1;
            for (int i = 0, j = wide; j < height.length; i++, j++) {
                int area = Math.min(height[i], height[j]);
                if (area > max)
                    max = area;
            }
            max *= wide;
            if (max > maxArea)
                maxArea = max;
        }
        return maxArea;
    }

    /**
     * 最优解,双指针法(时间复杂度o(n))
     */
    public int maxArea(int[] height) {
        int i = 0, j = height.length - 1, maxArea = -1;
        while (i < j) {
            int left = height[i];
            int right = height[j];
            int curArea;
            // 移动短的那边，因为移动长的那边只有可能减少
            // 1.总宽度肯定减少
            // 2.长边变得更大也减少，因为短边不变
            // 3.长边变得更小也减少，那就小上加小
            // 移动短边的话，如果短边变得很长还是有可能增大的
            if (left > right) {
                curArea = right * (j - i);
                j--;
            } else {
                i++;
                curArea = left * (j - i);
                if (left == right)
                    j--;
            }
            if (maxArea < curArea)
                maxArea = curArea;
        }
        return maxArea;
    }

    // 双指针法，只有移动短边才有可能使最后结果变大
    public int maxArea3(int[] height) {
        int i = 0, j = height.length - 1, maxArea = Integer.MIN_VALUE;
        while (i < j) {
            maxArea = Math.max(maxArea, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }
}
