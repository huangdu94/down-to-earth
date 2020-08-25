package club.huangdu94.exploration.primary_algorithms.sort_search;

/**
 * 给你两个升序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个升序数组。
 * <p>
 * <p>
 * <p>
 * 说明:
 * <p>
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。
 * 你可以假设 nums1 有足够的空间（空间大小大于或等于 m + n）来保存 nums2 中的元素。
 * <p>
 * <p>
 * 示例:
 * <p>
 * 输入:
 * nums1 = [1,2,3,0,0,0], m = 3
 * nums2 = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 *
 * @author duhuang@iflytek.com
 * @version 2020/6/23 15:05
 */
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n <= 0)
            return;
        int i = -1;
        int j = 0;
        while (++i < m + j)
            if (nums1[i] > nums2[j]) {
                /*for (int k = i; k < m + j; k++) {
                    nums1[k + 1] = nums1[k];
                }*/
                System.arraycopy(nums1, i, nums1, i + 1, m - i + j);
                nums1[i] = nums2[j++];
                if (j >= n)
                    return;
            }
        while (j < n)
            nums1[i++] = nums2[j++];
    }
}