package work.huangdu.question_bank.easy;

/**
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * 你可以假设数组中无重复元素。
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/7 10:41
 */
public class SearchInsert {
    // 二分查找法
    /*public int searchInsert(int[] nums, int target) {
        int result = Arrays.binarySearch(nums, target);
        return result < 0 ? -(result + 1) : result;
    }*/

    // 二分查找法
    public int searchInsert(int[] nums, int target) {
        return this.binarySearch(nums, target, 0, nums.length);
    }

    private int binarySearch(int[] nums, int target, int left, int right) {
        if (left >= right)
            return left;
        int mid = left + (right - left) / 2;
        if (nums[mid] == target)
            return mid;
        else if (nums[mid] < target)
            return this.binarySearch(nums, target, mid + 1, right);
        else
            return this.binarySearch(nums, target, left, mid);
    }
}
