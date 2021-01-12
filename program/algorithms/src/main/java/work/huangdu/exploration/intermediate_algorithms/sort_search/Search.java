package work.huangdu.exploration.intermediate_algorithms.sort_search;

/**
 * 搜索旋转排序数组
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * ( 例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] )。
 * 搜索一个给定的目标值，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。
 * 你可以假设数组中不存在重复的元素。
 * 你的算法时间复杂度必须是 O(log n) 级别。
 * 示例 1:
 * 输入: nums = [4,5,6,7,0,1,2], target = 0
 * 输出: 4
 * 示例 2:
 * 输入: nums = [4,5,6,7,0,1,2], target = 3
 * 输出: -1
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/7/12 15:50
 */
public class Search {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length < 1 || (nums.length == 1 && nums[0] != target))
            return -1;
        int l = 0;
        int r = nums.length - 1;
        int lValue = nums[l];
        int rValue = nums[r];
        if (target == lValue)
            return l;
        else if (target == rValue)
            return r;
        // 说明没翻转 正常的二分法即可
        if (lValue < rValue) {
            while (l <= r) {
                int mid = (r + l) / 2;
                if (nums[mid] < target)
                    l = mid + 1;
                else if (nums[mid] > target)
                    r = mid - 1;
                else
                    return mid;
            }
        } else {
            if (target > lValue) {
                while (l <= r) {
                    int mid = (r + l) / 2;
                    if (nums[mid] < lValue) {
                        r = mid - 1;
                        continue;
                    }
                    if (nums[mid] < target)
                        l = mid + 1;
                    else if (nums[mid] > target)
                        r = mid - 1;
                    else
                        return mid;
                }
            } else if (target < rValue) {
                while (l <= r) {
                    int mid = (r + l) / 2;
                    if (nums[mid] > rValue) {
                        l = mid + 1;
                        continue;
                    }
                    if (nums[mid] < target)
                        l = mid + 1;
                    else if (nums[mid] > target)
                        r = mid - 1;
                    else
                        return mid;
                }
            }
        }
        return -1;
    }

    public int search2(int[] nums, int target) {
        if (nums == null || nums.length < 1)
            return -1;
        int l = 0;
        int r = nums.length - 1;
        int lValue = nums[l];
        int rValue = nums[r];
        // flag为true说明翻转了，需要对二分法特殊处理
        boolean reverseFlag = lValue > rValue;
        // 如果翻转了可直接排除 target<lValue && target>rValue的情况
        if (reverseFlag && (target < lValue && target > rValue))
            return -1;
        // 是否在左边flag
        boolean flagL = target >= lValue;
        boolean flagR = !flagL;
        while (l <= r) {
            int mid = (r + l) / 2;
            if (reverseFlag) {
                if (flagL && nums[mid] < lValue) {
                    r = mid - 1;
                    continue;
                } else if (flagR && nums[mid] > rValue) {
                    l = mid + 1;
                    continue;
                }
            }
            if (nums[mid] < target)
                l = mid + 1;
            else if (nums[mid] > target)
                r = mid - 1;
            else
                return mid;
        }
        return -1;
    }
}
