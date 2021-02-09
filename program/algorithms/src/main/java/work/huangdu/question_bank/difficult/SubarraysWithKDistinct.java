package work.huangdu.question_bank.difficult;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 992. K 个不同整数的子数组
 * 给定一个正整数数组 A，如果 A 的某个子数组中不同整数的个数恰好为 K，则称 A 的这个连续、不一定独立的子数组为好子数组。
 * （例如，[1,2,3,1,2] 中有 3 个不同的整数：1，2，以及 3。）
 * 返回 A 中好子数组的数目。
 * 示例 1：
 * 输入：A = [1,2,1,2,3], K = 2
 * 输出：7
 * 解释：恰好由 2 个不同整数组成的子数组：[1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 * 示例 2：
 * 输入：A = [1,2,1,3,4], K = 3
 * 输出：3
 * 解释：恰好由 3 个不同整数组成的子数组：[1,2,1,3], [2,1,3], [1,3,4].
 * 提示：
 * 1 <= A.length <= 20000
 * 1 <= A[i] <= A.length
 * 1 <= K <= A.length
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/9
 */
public class SubarraysWithKDistinct {
    // 暴力法，枚举每一个开始位置(超时)
    public int subarraysWithKDistinct2(int[] A, int K) {
        int n = A.length, result = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < n; i++) {
            set.clear();
            int j = i;
            while (j < n && set.size() < K) {
                set.add(A[j++]);
            }
            while (j < n && set.size() == K) {
                set.add(A[j++]);
                result++;
            }
            if (set.size() == K) {
                result++;
            }
        }
        return result;
    }

    public int subarraysWithKDistinct(int[] A, int K) {
        return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
    }

    /**
     * @return 最多包含 K 个不同整数的子区间的个数
     */
    private int atMostKDistinct(int[] A, int K) {
        int n = A.length;
        int[] freq = new int[n + 1];

        int left = 0;
        int right = 0;
        // [left, right) 里不同整数的个数
        int count = 0;
        int res = 0;
        // [left, right) 包含不同整数的个数小于等于 K
        while (right < n) {
            if (freq[A[right]] == 0) {
                count++;
            }
            freq[A[right]]++;
            right++;

            while (count > K) {
                freq[A[left]]--;
                if (freq[A[left]] == 0) {
                    count--;
                }
                left++;
            }
            // [left, right) 区间的长度就是对结果的贡献
            res += right - left;
        }
        return res;
    }

    public static void main(String[] args) {
        SubarraysWithKDistinct swkd = new SubarraysWithKDistinct();
        int[] A = {1, 2, 1, 2, 3};
        System.out.println(swkd.subarraysWithKDistinct(A, 2));
    }
}
