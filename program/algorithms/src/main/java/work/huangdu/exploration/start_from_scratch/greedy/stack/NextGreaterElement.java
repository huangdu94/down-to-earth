package work.huangdu.exploration.start_from_scratch.greedy.stack;

import java.util.HashMap;
import java.util.Map;

/**
 * 496. 下一个更大元素 I
 * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
 * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
 * 示例 1:
 * 输入: nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出: [-1,3,-1]
 * 解释:
 * 对于num1中的数字4，你无法在第二个数组中找到下一个更大的数字，因此输出 -1。
 * 对于num1中的数字1，第二个数组中数字1右边的下一个较大数字是 3。
 * 对于num1中的数字2，第二个数组中没有下一个更大的数字，因此输出 -1。
 * 示例 2:
 * 输入: nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出: [3,-1]
 * 解释:
 * 对于 num1 中的数字 2 ，第二个数组中的下一个较大数字是 3 。
 * 对于 num1 中的数字 4 ，第二个数组中没有下一个更大的数字，因此输出 -1 。
 * 提示：
 * nums1和nums2中所有元素是唯一的。
 * nums1和nums2 的数组大小都不超过1000。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/11/20 13:23
 */
public class NextGreaterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // key value: nums2中key右边第一个比key大的数
        Map<Integer, Integer> map = new HashMap<>();
        int[] res = new int[nums1.length];
        int n = nums2.length, index = 0;
        for (int i = 0; i < n; i++) {
            int num = nums2[i];
            map.put(num, -1);
            for (int j = i + 1; j < n; j++) {
                if (nums2[j] > num) {
                    map.put(num, nums2[j]);
                    break;
                }
            }
        }
        for (int num : nums1) {
            res[index++] = map.get(num);
        }
        return res;
    }

    // 单调栈
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        if (nums1.length == 0) return new int[0];
        // key value: nums2中key右边第一个比key大的数
        int n = nums1.length, index = 0, top = 0;
        Map<Integer, Integer> map = new HashMap<>(n);
        int[] stack = new int[nums2.length], res = new int[n];
        for (int num : nums2) {
            while (top != 0 && num > stack[top - 1]) {
                map.put(stack[--top], num);
            }
            stack[top++] = num;
        }
        while (top != 0) {
            map.put(stack[--top], -1);
        }
        for (int num : nums1) {
            res[index++] = map.get(num);
        }
        return res;
    }
}
