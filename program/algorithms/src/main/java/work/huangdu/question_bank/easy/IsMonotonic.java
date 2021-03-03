package work.huangdu.question_bank.easy;

/**
 * 896. 单调数列
 * 如果数组是单调递增或单调递减的，那么它是单调的。
 * 如果对于所有 i <= j，A[i] <= A[j]，那么数组 A 是单调递增的。 如果对于所有 i <= j，A[i]> = A[j]，那么数组 A 是单调递减的。
 * 当给定的数组 A 是单调数组时返回 true，否则返回 false。
 * 示例 1：
 * 输入：[1,2,2,3]
 * 输出：true
 * 示例 2：
 * 输入：[6,5,4,4]
 * 输出：true
 * 示例 3：
 * 输入：[1,3,2]
 * 输出：false
 * 示例 4：
 * 输入：[1,2,4,5]
 * 输出：true
 * 示例 5：
 * 输入：[1,1,1]
 * 输出：true
 * 提示：
 * 1 <= A.length <= 50000
 * -100000 <= A[i] <= 100000
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/28
 */
public class IsMonotonic {
    enum Status {
        INITIALIZATION,
        DECREASING,
        INCREASING
    }

    public boolean isMonotonic(int[] A) {
        int n = A.length;
        Status status = Status.INITIALIZATION;
        for (int i = 1; i < n; i++) {
            if (A[i - 1] != A[i]) {
                if (A[i - 1] > A[i]) {
                    switch (status) {
                        case INCREASING:
                            return false;
                        case INITIALIZATION:
                            status = Status.DECREASING;
                    }
                } else {
                    switch (status) {
                        case DECREASING:
                            return false;
                        case INITIALIZATION:
                            status = Status.INCREASING;
                    }
                }
            }
        }
        return true;
    }
}
