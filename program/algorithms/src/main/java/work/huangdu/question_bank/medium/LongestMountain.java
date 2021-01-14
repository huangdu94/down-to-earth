package work.huangdu.question_bank.medium;

/**
 * 845. 数组中的最长山脉
 * 我们把数组 A 中符合下列属性的任意连续子数组 B 称为 “山脉”：
 * B.length >= 3
 * 存在 0 < i < B.length - 1 使得 B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * （注意：B 可以是 A 的任意子数组，包括整个数组 A。）
 * 给出一个整数数组 A，返回最长 “山脉” 的长度。
 * 如果不含有 “山脉” 则返回 0。
 * 示例 1：
 * 输入：[2,1,4,7,3,2,5]
 * 输出：5
 * 解释：最长的 “山脉” 是 [1,4,7,3,2]，长度为 5。
 * 示例 2：
 * 输入：[2,2,2]
 * 输出：0
 * 解释：不含 “山脉”。
 * 提示：
 * 0 <= A.length <= 10000
 * 0 <= A[i] <= 10000
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/10/25 8:42
 */
public class LongestMountain {
    public int longestMountain(int[] A) {
        // state 三种状态 0 1 2
        int n = A.length, start = 0, state = 0, res = 0;
        for (int i = 0; i < n - 1; i++) {
            if (state == 0) {
                if (A[i] < A[i + 1]) {
                    state = 1;
                } else {
                    start = i + 1;
                }
            } else if (state == 1) {
                if (A[i] > A[i + 1]) {
                    state = 2;
                } else if (A[i] == A[i + 1]) {
                    state = 0;
                    start = i--;
                }
            } else {
                if (A[i] <= A[i + 1]) {
                    state = 0;
                    if (res < (i - start + 1)) {
                        res = (i - start + 1);
                    }
                    start = i--;
                }
            }
        }
        if (state == 2) {
            if (res < (n - start)) {
                res = (n - start);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] A = {2, 1, 4, 7, 3, 2, 5};
        LongestMountain lm = new LongestMountain();
        System.out.println(lm.longestMountain(A));
    }
}
