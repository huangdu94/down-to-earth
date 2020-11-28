package work.huangdu.question_bank.difficult;

/**
 * 493. 翻转对
 * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
 * 你需要返回给定数组中的重要翻转对的数量。
 * 示例 1:
 * 输入: [1,3,2,3,1]
 * 输出: 2
 * 示例 2:
 * 输入: [2,4,3,5,1]
 * 输出: 3
 * 注意:
 * 给定数组的长度不会超过50000。
 * 输入数组中的所有数字都在32位整数的表示范围内。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/28 10:18
 */
public class ReversePairs {
    // 暴力 时间复杂度o(n^2) 空间复杂度o(1) 超时
    public int reversePairs2(int[] nums) {
        int n = nums.length, count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (nums[i] > 2L * nums[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    // 归并排序
    public int reversePairs(int[] nums) {
        int n = nums.length;
        return merge(nums, 0, n - 1);
    }

    private int merge(int[] nums, int left, int right) {
        if (left >= right) return 0;
        int mid = left + (right - left) / 2;
        int res1 = merge(nums, left, mid);
        int res2 = merge(nums, mid + 1, right);
        // 统计数量
        int i = left, j = mid + 1, count = res1 + res2;
        while (i <= mid) {
            int pivot = nums[i];
            while (j <= right && pivot > 2L * nums[j]) {
                j++;
            }
            count += j - (mid + 1);
            i++;
        }
        // 排序
        int[] copys = new int[right - left + 1];
        int index = 0;
        i = left;
        j = mid + 1;
        while (i <= mid || j <= right) {
            if (i > mid) {
                copys[index++] = nums[j++];
            } else if (j > right) {
                copys[index++] = nums[i++];
            } else if (nums[i] <= nums[j]) {
                copys[index++] = nums[i++];
            } else {
                copys[index++] = nums[j++];
            }
        }
        System.arraycopy(copys, 0, nums, left, copys.length);
        return count;
    }

    public static void main(String[] args) {
        ReversePairs pp = new ReversePairs();
        System.out.println(pp.reversePairs(new int[]{1, 2, 3, 4}));
    }
}
