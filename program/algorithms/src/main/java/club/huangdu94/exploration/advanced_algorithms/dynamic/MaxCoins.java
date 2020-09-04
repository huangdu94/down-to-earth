package club.huangdu94.exploration.advanced_algorithms.dynamic;

/**
 * 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组nums中。
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得nums[left] * nums[i] * nums[right]个硬币。这里的left和right代表和i相邻的两个气球的序号。注意当你戳破了气球 i 后，气球left和气球right就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 * 说明:
 * 你可以假设nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/4 15:20
 */
public class MaxCoins {
    public int maxCoins(int[] nums) {
        return 0;
    }
}