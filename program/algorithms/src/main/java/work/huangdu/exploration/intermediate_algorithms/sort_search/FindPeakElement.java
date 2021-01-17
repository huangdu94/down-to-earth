package work.huangdu.exploration.intermediate_algorithms.sort_search;

/**
 * 寻找峰值
 * 峰值元素是指其值大于左右相邻值的元素。
 * 给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。
 * 数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞。
 * 示例 1:
 * 输入: nums = [1,2,3,1]
 * 输出: 2
 * 解释: 3 是峰值元素，你的函数应该返回其索引 2。
 * 示例 2:
 * 输入: nums = [1,2,1,3,5,6,4]
 * 输出: 1 或 5
 * 解释: 你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 * 说明:
 * 你的解法应该是 O(logN) 时间复杂度的。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/12 15:47
 */
public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        if (nums == null || nums.length < 1)
            return -1;
        int l = 0, r = nums.length - 1;
        while (l < r) {
            int m = (l + r) / 2;
            int ml = m - 1;
            int mr = m + 1;
            if (ml < 0)
                if (nums[m] > nums[mr])
                    return m;
                else
                    l = m + 1;
            else if (mr > nums.length - 1)
                if (nums[ml] < nums[m])
                    return m;
                else
                    r = m - 1;
            else if (nums[ml] > nums[m])
                r = m - 1;
            else if (nums[m] < nums[mr])
                l = m + 1;
            else
                return m;
        }
        return l;
    }

    public int findPeakElement2(int[] nums) {
        if (nums == null || nums.length < 1)
            return -1;
        return findPeak(nums, 0, nums.length - 1);
    }

    private int findPeak(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int m = (l + r) / 2;
        int ml = m - 1;
        int mr = m + 1;
        int result;
        if (ml < 0)
            if (nums[m] > nums[mr])
                result = m;
            else
                result = findPeak(nums, m + 1, r);
        else if (mr > nums.length - 1)
            if (nums[ml] < nums[m])
                result = m;
            else
                result = findPeak(nums, l, m - 1);
        else if (nums[ml] > nums[m])
            result = findPeak(nums, l, m - 1);
        else if (nums[m] < nums[mr])
            result = findPeak(nums, m + 1, r);
        else
            result = m;
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        int peek = new FindPeakElement().findPeakElement(nums);
        System.out.println(peek);
    }
}
