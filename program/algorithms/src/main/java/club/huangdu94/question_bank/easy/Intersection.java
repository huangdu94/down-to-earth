package club.huangdu94.question_bank.easy;

import java.util.*;

/**
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/2 13:56
 * @see club.huangdu94.exploration.primary_algorithms.array.Intersect
 */
public class Intersection {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        Set<Integer> nums2Set = new HashSet<>();
        // 1. set 去重
        for (int n : nums1) {
            nums1Set.add(n);
        }
        for (int n : nums2) {
            nums2Set.add(n);
        }
        // 2. 遍历元素数量少的set,两个都有的加入list
        if (nums2Set.size() > nums1Set.size()) {
            Set<Integer> temp = nums1Set;
            nums1Set = nums2Set;
            nums2Set = temp;
        }
        int index = 0;
        int[] res = new int[nums1Set.size()];
        for (int n : nums1Set) {
            if (nums2Set.contains(n)) {
                res[index++] = n;
            }
        }
        return Arrays.copyOf(res, index);
    }
}
