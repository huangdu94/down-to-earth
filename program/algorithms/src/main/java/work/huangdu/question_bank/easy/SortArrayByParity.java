package work.huangdu.question_bank.easy;

/**
 * 905. 按奇偶排序数组
 * 给定一个非负整数数组 A，返回一个数组，在该数组中， A 的所有偶数元素之后跟着所有奇数元素。
 * 你可以返回满足此条件的任何数组作为答案。
 * 示例：
 * 输入：[3,1,2,4]
 * 输出：[2,4,3,1]
 * 输出 [4,2,3,1]，[2,4,1,3] 和 [4,2,1,3] 也会被接受。
 * 提示：
 * 1 <= A.length <= 5000
 * 0 <= A[i] <= 5000
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/11/12 15:41
 */
public class SortArrayByParity {
    // 双指针法
    // 1. i找到第一个奇数
    // 2. j从i开始找第一个偶数，如果找到进入3，否则结束
    // 3. i与j交换位置
    public int[] sortArrayByParity(int[] A) {
        int n = A.length, i = 0, j = 0;
        while (j < n) {
            // 1. i找到第一个奇数
            while (i < n && (A[i] & 1) == 0) {
                i++;
            }
            // 2. 只有第一次会用到，后面j肯定在i后面
            if (j <= i) {
                j = i + 1;
            }
            // 3. j找到第一个偶数
            while (j < n && (A[j] & 1) == 1) {
                j++;
            }
            // 4. 交换i和j
            if (j < n) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i++;
                j++;
            }
        }
        return A;
    }
}
