package work.huangdu.data_structure;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 离散化数组
 * 去重后升序排序数组，用于离散原数组
 * value -> id 二分查找 返回index+1
 * id -> value id - 1 作为index查
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/4 11:50
 */
public class Discretization {
    // 离散数组
    private int[] a;

    public Discretization(int[] nums) {
        discretization(nums);
    }

    /**
     * 离散化原数组
     *
     * @param nums 原数组
     */
    private void discretization(int[] nums) {
        // 1. 集合去重
        Set<Integer> set = new HashSet<>();
        for (int num : nums) set.add(num);
        // 2. 集合转化为数组
        a = new int[set.size()];
        int index = 0;
        for (int num : set) {
            a[index++] = num;
        }
        // 3. 排序数组
        Arrays.sort(a);
    }

    /**
     * 返回非重复元素个数
     *
     * @return 离散后非重元素个数
     */
    public int length() {
        return a.length;
    }

    /**
     * 二分查找去重后排序数组得到value对应的id
     *
     * @param x value值
     * @return 对应的id
     */
    public int getId(int x) {
        return Arrays.binarySearch(a, x) + 1;
    }

    /**
     * 根据id查找值
     *
     * @param id 元素id
     * @return 值
     */
    public int getValue(int id) {
        return a[id - 1];
    }
}
