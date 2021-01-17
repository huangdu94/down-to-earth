package work.huangdu.exploration.advanced_algorithms.sort_search;

/**
 * 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组nums1 和nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为O(log(m + n))。
 * 你可以假设nums1和nums2不会同时为空。
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/21 11:41
 */
public class FindMedianSortedArrays {
    // 时间复杂度o(m+n)
    public double findMedianSortedArrays1(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int i = 0, j = 0, len = m + n, k = ((len & 1) == 1) ? len / 2 : len / 2 - 1;
        while (i < m && j < n && k > 0) {
            if (nums1[i] <= nums2[j]) {
                i++;
            } else {
                j++;
            }
            k--;
        }
        if (i < m) {
            while (k > 0) {
                i++;
                k--;
            }
        } else {
            while (k > 0) {
                j++;
                k--;
            }
        }
        if ((len & 1) == 1) {
            if (i < m && j < n) {
                return Math.min(nums1[i], nums2[j]);
            } else if (i < m) {
                return nums1[i];
            } else {
                return nums2[j];
            }
        } else {
            int mid1, mid2;
            if (i < m && j < n) {
                mid1 = nums1[i] <= nums2[j] ? nums1[i++] : nums2[j++];
            } else if (i < m) {
                mid1 = nums1[i++];
            } else {
                mid1 = nums2[j++];
            }
            if (i < m && j < n) {
                mid2 = Math.min(nums1[i], nums2[j]);
            } else if (i < m) {
                mid2 = nums1[i];
            } else {
                mid2 = nums2[j];
            }
            return (mid1 + mid2) / 2.0;
        }
    }

    // 二分查找法(时间复杂度log(m+n))
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // m+n为奇数则中位数为第k小的元素，m+n为偶数则中位数为第k和k+1小的元素的平均值
        int k = (m + n + 1) / 2;
        int i = 0, j = 0, old_i = 0, old_j = 0;
        /*
          1. 表示在i和j之前有k-2个元素，那么i和j中较小的那个最多是第k-1小的，
             所以直接排除i和j位置上较小的那个和它之前所有的数，根据排除的个数更新k值；
          注：即使i和j相等也只能排除一个，因为其中一个是最多是第k-1小的数，另一个就有可能是第k小的数了
          2. 如果i和j有一个超出了边界（不可能都超出边界）那么就赋值其为末尾的index其它逻辑不变；
          3. 如果k等于1则返回i和j位置上较小的那个
          4. 如果一个数组全部删除完了，则返回另一个数组第k小的数（注意k每次删完是要更新的）
        */
        while (true) {
            if (i >= m) {
                if ((m + n) % 2 == 1) {
                    return nums2[old_j + k - 1];
                } else {
                    return (nums2[old_j + k - 1] + nums2[old_j + k]) / 2.0;
                }
            } else if (j >= n) {
                if ((m + n) % 2 == 1) {
                    return nums1[old_i + k - 1];
                } else {
                    return (nums1[old_i + k - 1] + nums1[old_i + k]) / 2.0;
                }
            }
            if (k == 1) {
                if ((m + n) % 2 == 1) {
                    return Math.min(nums1[i], nums2[j]);
                } else {
                    if (nums1[i] <= nums2[j]) {
                        if (i == m - 1) {
                            return (nums1[i] + nums2[j]) / 2.0;
                        } else {
                            return (nums1[i++] + Math.min(nums1[i], nums2[j])) / 2.0;
                        }
                    } else {
                        if (j == n - 1) {
                            return (nums2[j] + nums1[i]) / 2.0;
                        } else {
                            return (nums2[j++] + Math.min(nums2[j], nums1[i])) / 2.0;
                        }
                    }
                }
            }
            i = Math.min(old_i + k / 2 - 1, m - 1);
            j = Math.min(old_j + k / 2 - 1, n - 1);
            // 优化点，因为nums1[i] == nums2[j]时可以任选一个删，选择可以排除更多数的那个
            if (nums1[i] == nums2[j]) {
                if (i >= j) {
                    k -= ++i - old_i;
                    old_i = i;
                } else {
                    k -= ++j - old_j;
                    old_j = j;
                }
            } else if (nums1[i] > nums2[j]) {
                k -= ++j - old_j;
                old_j = j;
            } else {
                k -= ++i - old_i;
                old_i = i;
            }
        }
    }

    // 进阶划分数组，主要证明比较麻烦 时间复杂度最优O(log min(m,n))
    public static void main(String[] args) {
        FindMedianSortedArrays findMedianSortedArrays = new FindMedianSortedArrays();
        int[] nums1 = {1};
        int[] nums2 = {2, 3, 4, 5, 6, 7};
        double mid = findMedianSortedArrays.findMedianSortedArrays(nums1, nums2);
        System.out.println(mid);
    }
}
