package club.huangdu94.exploration.intermediate_algorithms.arraystring;

/**
 * 递增的三元子序列
 * 给定一个未排序的数组，判断这个数组中是否存在长度为 3 的递增子序列。
 * 数学表达式如下:
 * 如果存在这样的 i, j, k,  且满足 0 ≤ i < j < k ≤ n-1，
 * 使得 arr[i] < arr[j] < arr[k] ，返回 true ; 否则返回 false 。
 * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1) 。
 * 示例 1:
 * 输入: [1,2,3,4,5]
 * 输出: true
 * 示例 2:
 * 输入: [5,4,3,2,1]
 * 输出: false
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/2 10:40
 */
public class IncreasingTriplet {
    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3)
            return false;
        int first = nums[0];
        int second = 0; // 不会使用到的初始化值(保证程序可编译)
        int index = 1;
        // 1.找到第一个second
        while (index < nums.length) {
            int cur = nums[index++];
            if (cur < first) {
                first = cur;
            } else if (cur > first) {
                second = cur;
                break;
            }
        }
        // 2.继续判断
        while (index < nums.length) {
            int cur = nums[index++];
            if (cur > second)
                return true;
            else if (cur < first)
                first = cur;
            else if (first < cur && cur < second)
                second = cur;
        }
        return false;
    }
}
