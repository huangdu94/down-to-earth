package work.huangdu.exploration.primary_algorithms.sort_search;

import java.util.Arrays;

/**
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/6/23 12:29
 */
public class Test {
    public static void main(String[] args) {
//        int n=2126753390;
//        int v=1702766719;
//        System.out.println(new FirstBadVersion(n, v).firstBadVersion(n));
        int[] nums1 = {4, 5, 6, 0, 0, 0};
        int[] nums2 = {1, 2, 3};
        new Merge().merge(nums1, 3, nums2, 3);
        System.out.println(Arrays.toString(nums1));
    }
}
