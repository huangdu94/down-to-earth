package work.huangdu.exploration.start_from_scratch.double_pointer.segment;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/12/20 13:07
 */
public class Merge {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = m + n - 1, i1 = m - 1, i2 = n - 1;
        while (j >= 0) {
            if (i1 < 0) {
                nums1[j] = nums2[i2--];
            } else if (i2 < 0) {
                nums1[j] = nums1[i1--];
            } else if (nums1[i1] >= nums2[i2]) {
                nums1[j] = nums1[i1--];
            } else {
                nums1[j] = nums2[i2--];
            }
            j--;
        }
    }
}
