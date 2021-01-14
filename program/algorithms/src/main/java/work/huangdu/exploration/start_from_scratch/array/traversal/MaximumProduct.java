package work.huangdu.exploration.start_from_scratch.array.traversal;

/**
 * 628. 三个数的最大乘积
 * 给定一个整型数组，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
 * 示例 1:
 * 输入: [1,2,3]
 * 输出: 6
 * 示例 2:
 * 输入: [1,2,3,4]
 * 输出: 24
 * 注意:
 * 给定的整型数组长度范围是[3,10^4]，数组中所有的元素范围是[-1000, 1000]。
 * 输入的数组中任意三个数的乘积不会超出32位有符号整数的范围。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/13 14:47
 */
public class MaximumProduct {
    // 暴力解超时
    public int maximumProduct1(int[] nums) {
        int len = nums.length, max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    int product = nums[i] * nums[j] * nums[k];
                    if (product > max) max = product;
                }
            }
        }
        return max;
    }

    /**
     * 三个数的最大乘积有以下几种可能
     * 1. 三个最大的非负数的乘积
     * 2. 两个最小的负数的乘积加一个最大正数的乘积
     * 3. 三个最大负数的乘积（此种情况与第一种合并）
     */
    public int maximumProduct(int[] nums) {
        long max = Long.MIN_VALUE, second = Long.MIN_VALUE, third = Long.MIN_VALUE, min = Long.MAX_VALUE, secondMin = Long.MAX_VALUE;
        for (int num : nums) {
            if (max < num) {
                third = second;
                second = max;
                max = num;
            } else if (second < num) {
                third = second;
                second = num;
            } else if (third < num) {
                third = num;
            }
            if (num < min) {
                secondMin = min;
                min = num;
            } else if (num < secondMin) {
                secondMin = num;
            }
        }
        return (int) Math.max(max * second * third, max * secondMin * min);
    }

    public static void main(String[] args) {
        int[] nums = {-925, -429, 515, 260, 538, -338, -453, 159, 683, 782, 597, 433, 349, -846, 816, 55};
        MaximumProduct maximumProduct = new MaximumProduct();
        System.out.println(maximumProduct.maximumProduct(nums));
    }
}
