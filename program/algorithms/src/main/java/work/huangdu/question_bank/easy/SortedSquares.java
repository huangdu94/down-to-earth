package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 977. 有序数组的平方
 * 给定一个按非递减顺序排序的整数数组 A，返回每个数字的平方组成的新数组，要求也按非递减顺序排序。
 * 示例 1：
 * 输入：[-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 示例 2：
 * 输入：[-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 * 提示：
 * 1 <= A.length <= 10000
 * -10000 <= A[i] <= 10000
 * A 已按非递减顺序排序。
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/16 12:23
 */
public class SortedSquares {
    // 1. 暴力 先求平方 再排序 时间复杂度o(nlog n)
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int[] res = new int[n];
        for (int i = 0; i < n; i++) {
            res[i] = A[i] * A[i];
        }
        Arrays.sort(res);
        return res;
    }

    // 2. 双指针法，直接按照非递减顺序输出 o(n)
    public int[] sortedSquares2(int[] A) {
        int n = A.length, i = -1, j, k = 0;
        int[] res = new int[n];
        while (i < n - 1 && A[i + 1] < 0) {
            i++;
        }
        j = i + 1;
        while (i != -1 || j != n) {
            if (i == -1 || j != n && -A[i] > A[j]) {
                res[k++] = A[j] * A[j++];
            } else {
                res[k++] = A[i] * A[i--];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SortedSquares squares = new SortedSquares();
        int[] A = {-1};
        System.out.println(Arrays.toString(squares.sortedSquares2(A)));
    }
}
