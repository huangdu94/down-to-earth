package work.huangdu.exploration.advanced_algorithms.dynamic;

/**
 * 乘积最大子数组
 * 给你一个整数数组 nums，请你找出数组中乘积最大的连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 示例 1:
 * 输入: [2,3,-2,4]
 * 输出: 6
 * 解释:子数组 [2,3] 有最大乘积 6。
 * 示例 2:
 * 输入: [-2,0,-1]
 * 输出: 0
 * 解释:结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/25 15:12
 */
public class MaxProduct {
    // 时间复杂度o(n^2) 空间复杂度o(1)
    public int maxProduct1(int[] nums) {
        int len = nums.length;
        int pre, cur;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            pre = 1;
            for (int j = i; j < len; j++) {
                cur = pre * nums[j];
                if (cur > max) {
                    max = cur;
                }
                pre = cur;
            }
        }
        return max;
    }

    /**
     * 分析题目，有一条基本定理：
     * 0. 如果nums里全是正数，则答案为所有数相乘
     * 使以上规则复杂化的是nums数组中的负数和零，下面具体分析：
     * 1. 如果一个子数组中包含零，则乘积不管怎么样都为零
     * （如果数组中有至少一个零，则先记录乘积的最大值为零，然后再以零将数组分割成多个子数组，再单独分析每一个不包括零的子数组）
     * 2. 经过1处理后，我们得到了多个不包含零的子数组，对于每一个子数组，使规则复杂化的是其中的负数
     * （2.1 特殊情况，如果子数组仅有一个负数，则判断其是否为最大值；
     * 2.2 如果子数组负数个数为偶数，则结果就是该子数组所有元素的乘积；
     * 2.3 如果子数组负数个数为奇数，则结果为从该子数组第一个负数之后所有元素之积，或该子数组最后一个负数之前所有元素之积，具体证明如下：
     * 负数个数为奇数，则最后结果为负数，想结果最大化肯定要去除一个负数，去除的这个负数是第一个或者是最后一个才能保证积中的元素更多，
     * 因为这些元素的绝对值一定是大于等于一的，并且负数个数为偶数，所以元素更多积才有可能更大）
     * 分析以上几条，总结以下，时间复杂度o(n)，空间复杂度o(1)的算法：
     * 0. max变量记录乘积最大值，最后返回的也是该变量，初始时赋值其为nums[0]。设置变量product记录当前子数组的总乘积（以零来分割），
     * head记录子数组开头到第一个负数的总乘积，tail记录子数组最后一个负数到结尾的总乘积，sFlag记录子串是否已经开始，nFlag记录子数组是否已经遇到负数。
     * 1. 对于nums[0]若其为0，则遍历nums直到找到下一个不为0的数。
     * 若其为正数则更新product, head为nums[0]；若其为负数则flag设为true，更新product, head；
     * 下面继续非开头的元素，直到遍历到nums结尾
     */
    public int maxProduct2(int[] nums) {
        int max = nums[0], product = 1, head = 1, tail = 1;
        boolean nFlag = false, sFlag = false, uFlag = false;
        for (int n : nums) {
            if (n == 0) {
                if (n > max) max = n;
                if (sFlag) {
                    if (product > 0) {
                        if (product > max) max = product;
                    } else if (!uFlag) {
                        int temp = Math.max(product / head, product / tail);
                        if (temp > max) max = temp;
                    }
                    // 子串状态清零
                    product = head = tail = 1;
                    sFlag = nFlag = uFlag = false;
                }
            } else {
                if (uFlag) uFlag = false;
                product *= n;
                if (n < 0) {
                    tail = n;
                    if (!nFlag) {
                        nFlag = true;
                        head *= n;
                    }
                } else {
                    if (nFlag) {
                        tail *= n;
                    } else {
                        head *= n;
                    }
                }
                if (!sFlag) {
                    if (n < 0) uFlag = true;
                    sFlag = true;
                }
            }
        }
        if (sFlag) {
            if (product > 0) {
                if (product > max) max = product;
            } else if (!uFlag) {
                int temp = Math.max(product / head, product / tail);
                if (temp > max) max = temp;
            }
        }
        return max;
    }

    /**
     * 动态规划，时间复杂度o(n)，空间复杂度o(1)
     */
    public int maxProduct(int[] nums) {
        int res = nums[0], min = res, max = res;
        for (int i = 1; i < nums.length; i++) {
            int temp = Math.max(nums[i], Math.max(min * nums[i], max * nums[i]));
            min = Math.min(nums[i], Math.min(min * nums[i], max * nums[i]));
            max = temp;
            if (max > res) {
                res = max;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxProduct maxProduct = new MaxProduct();
        int[] nums = {-4, -3, -2};
        int max = maxProduct.maxProduct(nums);
        System.out.println(max);
    }

    public int maxProduct3(int[] nums) {
        int n = nums.length, result = nums[0], min = result, max = result;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            int temp = Math.min(num, Math.min(min * num, max * num));
            max = Math.max(num, Math.max(min * num, max * num));
            min = temp;
            result = Math.max(max, result);
        }
        return result;
    }
}
