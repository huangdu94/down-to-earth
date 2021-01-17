package work.huangdu.exploration.advanced_algorithms.array_string;

import work.huangdu.exploration.intermediate_algorithms.array_string.ThreeSum;
import work.huangdu.exploration.start_from_scratch.hashmap.index.TwoSum2;
import work.huangdu.exploration.start_from_scratch.hashmap.statistics.FourSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -2^28 到 2^28 - 1 之间，最终结果不会超过 2^31 - 1 。
 * 例如:
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 * 输出:
 * 2
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/26 20:02
 * @see work.huangdu.exploration.primary_algorithms.array.TwoSum
 * @see TwoSum2
 * @see ThreeSum
 * @see FourSum
 */
public class FourSumCount {
    // 暴力解(超出时间限制),时间复杂度o(n^4)
    public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {
        int result = 0;
        for (int a : A)
            for (int b : B)
                for (int c : C)
                    for (int d : D)
                        if (a + b + c + d == 0)
                            result++;
        return result;
    }

    // 先从小到大排序,三重循环,最后一层循环使用双指针法(虽然通过但是耗时太久),时间复杂度时间复杂度o(n^3)
    public int fourSumCount2(int[] A, int[] B, int[] C, int[] D) {
        Arrays.sort(A);
        Arrays.sort(B);
        Arrays.sort(C);
        Arrays.sort(D);
        int result = 0;
        for (int first = 0; first < A.length; first++) {
            int resultA = 1;
            while (first < A.length - 1 && A[first + 1] == A[first]) {
                first++;
                resultA++;
            }
            for (int second = 0; second < B.length; second++) {
                int resultB = 1, resultCD = 0;
                while (second < B.length - 1 && B[second + 1] == B[second]) {
                    second++;
                    resultB++;
                }
                int third = 0, fourth = C.length - 1, target = -A[first] - B[second];
                while (third < C.length && fourth >= 0) {
                    if (C[third] + D[fourth] < target)
                        third++;
                    else if (C[third] + D[fourth] > target)
                        fourth--;
                    else {
                        int resultC = 1, resultD = 1;
                        while (third < C.length - 1 && C[third + 1] == C[third]) {
                            third++;
                            resultC++;
                        }
                        while (fourth >= 1 && D[fourth - 1] == D[fourth]) {
                            fourth--;
                            resultD++;
                        }
                        resultCD += (resultC * resultD);
                        third++;
                        fourth--;
                    }
                }
                result += resultA * resultB * resultCD;
            }
        }
        return result;
    }

    // HashMap 将计算四数之和问题转化为求两个两数之和问题时间复杂度降为o(n^2)
    public int fourSumCount3(int[] A, int[] B, int[] C, int[] D) {
        int result = 0;
        int capacity = A.length * A.length;
        // 初始化容量设到可能用到的最大值,避免扩容消耗时间
        HashMap<Integer, Integer> abSum = new HashMap<>(capacity);
        HashMap<Integer, Integer> cdSum = new HashMap<>(capacity);
        for (int a : A)
            for (int b : B)
                abSum.merge(a + b, 1, Integer::sum);
        for (int c : C)
            for (int d : D)
                cdSum.merge(c + d, 1, Integer::sum);
        for (Map.Entry<Integer, Integer> entry : abSum.entrySet()) {
            Integer cdCount = cdSum.get(-entry.getKey());
            if (cdCount != null)
                result += (entry.getValue() * cdCount);
        }
        return result;
    }

    // HashMap 将计算四数之和问题转化为求两个两数之和问题时间复杂度降为o(n^2)
    public int fourSumCount4(int[] A, int[] B, int[] C, int[] D) {
        int result = 0;
        int capacity = A.length * A.length;
        // 初始化容量设到可能用到的最大值,避免扩容消耗时间
        HashMap<Integer, Integer> abSum = new HashMap<>(capacity);
        for (int a : A)
            for (int b : B)
                abSum.merge(a + b, 1, Integer::sum);
        for (int c : C)
            for (int d : D) {
                Integer abCount = abSum.get(-c - d);
                if (abCount != null)
                    result += abCount;
            }
        return result;
    }

    public static void main(String[] args) {
        int[] A = {-1, -1};
        int[] B = {-1, 1};
        int[] C = {-1, 1};
        int[] D = {1, -1};
        FourSumCount fourSumCount = new FourSumCount();
        System.out.println(fourSumCount.fourSumCount1(A, B, C, D));
        System.out.println(fourSumCount.fourSumCount2(A, B, C, D));
        System.out.println(fourSumCount.fourSumCount3(A, B, C, D));
        System.out.println(fourSumCount.fourSumCount4(A, B, C, D));
    }

    // 暴力o(n^4)
    // 用一个HashMap存一个数组的数及数量o(n^3)
    // 用一个HashMap存两个数组的组合和及数量o(n^2)
    public int fourSumCount5(int[] A, int[] B, int[] C, int[] D) {
        int sum = 0;
        Map<Integer, Integer> abMap = new HashMap<>();
        for (int a : A) {
            for (int b : B) {
                abMap.merge(a + b, 1, Integer::sum);
            }
        }
        for (int c : C) {
            for (int d : D) {
                Integer count = abMap.get(-c - d);
                if (count != null) {
                    sum += count;
                }
            }
        }
        return sum;
    }
}
