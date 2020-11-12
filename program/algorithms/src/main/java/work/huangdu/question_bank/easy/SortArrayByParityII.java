package work.huangdu.question_bank.easy;

/**
 * 922. 按奇偶排序数组 II
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 * 你可以返回任何满足上述条件的数组作为答案。
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 * 提示：
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/12 17:25
 */
public class SortArrayByParityII {
    // 双指针法
    // 一个指针在偶数位置跳动
    // 另一个指针在奇数位置跳动
    // 遇到不符合要求的停下，交换两个指针的数
    public int[] sortArrayByParityII(int[] A) {
        if (A.length < 2) return A;
        int n = A.length, i = 0, j = 1;
        while (i < n && j < n) {
            while (i < n && (A[i] & 1) == 0) {
                i += 2;
            }
            while (j < n && (A[j] & 1) == 1) {
                j += 2;
            }
            if (i < n && j < n) {
                int temp = A[i];
                A[i] = A[j];
                A[j] = temp;
                i += 2;
                j += 2;
            }
        }
        return A;
    }
}
