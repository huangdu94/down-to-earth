package work.huangdu.question_bank.medium;

/**
 * 978. 最长湍流子数组
 * 当 A 的子数组 A[i], A[i+1], ..., A[j] 满足下列条件时，我们称其为湍流子数组：
 * 若 i <= k < j，当 k 为奇数时， A[k] > A[k+1]，且当 k 为偶数时，A[k] < A[k+1]；
 * 或 若 i <= k < j，当 k 为偶数时，A[k] > A[k+1] ，且当 k 为奇数时， A[k] < A[k+1]。
 * 也就是说，如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 * 返回 A 的最大湍流子数组的长度。
 * 示例 1：
 * 输入：[9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：(A[1] > A[2] < A[3] > A[4] < A[5])
 * 示例 2：
 * 输入：[4,8,12,16]
 * 输出：2
 * 示例 3：
 * 输入：[100]
 * 输出：1
 * 提示：
 * 1 <= A.length <= 40000
 * 0 <= A[i] <= 10^9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/8
 */
public class MaxTurbulenceSize {
    public int maxTurbulenceSize(int[] arr) {
        // flag -1 下降 0 初始 1 上升
        int n = arr.length, start = 0, last = 0, max = 1;
        for (int i = 1; i < n; i++) {
            int cur = getTendency(arr[i - 1], arr[i]);
            if (cur == 0 || cur == last) {
                max = Math.max(max, i - start);
                start = i + (cur != 0 ? -1 : 0);
            }
            last = cur;
        }
        return Math.max(max, n - start);
    }

    private int getTendency(int pre, int cur) {
        if (pre == cur) return 0;
        return pre > cur ? -1 : 1;
    }

    public static void main(String[] args) {
        MaxTurbulenceSize mts = new MaxTurbulenceSize();
        int[] arr = {0, 8, 45, 88, 48, 68, 28, 55, 17, 24};
        System.out.println(mts.maxTurbulenceSize(arr));
    }
}
