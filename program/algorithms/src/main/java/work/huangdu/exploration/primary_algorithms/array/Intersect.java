package work.huangdu.exploration.primary_algorithms.array;

import work.huangdu.exploration.start_from_scratch.hashmap.search_insert_delete.Intersection;

import java.util.*;

/**
 * 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 * 说明：
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，磁盘内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/26 15:20
 * @see Intersection
 */
public class Intersect {
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums2 == null)
            return new int[0];
        if (nums1.length >= nums2.length)
            this.intersect(nums2, nums1);
        int[] result;
        boolean[] flagArr;
        int index = 0;
        result = new int[nums1.length];
        flagArr = new boolean[nums1.length];
        for (int value : nums2) {
            for (int j = 0; j < nums1.length; j++) {
                if (value == nums1[j]) {
                    if (flagArr[j])
                        continue;
                    result[index++] = value;
                    flagArr[j] = true;
                    break;
                }
            }
        }
        if (index < 1)
            return new int[0];
        return Arrays.copyOf(result, index);
    }

    public int[] intersect2(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums1Map = new HashMap<>();
        Map<Integer, Integer> nums2Map = new HashMap<>();
        for (int num : nums1) {
            nums1Map.merge(num, 1, Integer::sum);
        }
        for (int num : nums2) {
            nums2Map.merge(num, 1, Integer::sum);
        }
        List<Integer> res = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : nums1Map.entrySet()) {
            int num = entry.getKey();
            if (nums2Map.containsKey(num)) {
                int count = Math.min(entry.getValue(), nums2Map.get(num));
                for (int k = 0; k < count; k++) {
                    res.add(num);
                }
            }
        }
        int[] result = new int[res.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = res.get(i);
        }
        return result;
    }
}
