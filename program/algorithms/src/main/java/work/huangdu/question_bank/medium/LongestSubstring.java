package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 395. 至少有 K 个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 * 示例 1：
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 * 示例 2：
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅由小写英文字母组成
 * 1 <= k <= 10^5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/27
 */
public class LongestSubstring {
    private static final int LOWERCASE_LETTER_COUNT = 26;

    public int longestSubstring(String s, int k) {
        int n = s.length();
        int[] ints = new int[n];
        for (int i = 0; i < n; i++) {
            ints[i] = s.charAt(i) - 'a';
        }
        return longestSubstring(ints, k, 0, n);
    }

    private int longestSubstring(int[] ints, int k, int start, int end) {
        if (start >= end) { return 0; }
        int longest = 0;
        int[] counts = new int[LOWERCASE_LETTER_COUNT];
        for (int i = start; i < end; i++) {
            counts[ints[i]]++;
        }
        boolean allMeet = true;
        for (int i = start; i < end; i++) {
            if (counts[ints[i]] < k) {
                longest = Math.max(longest, longestSubstring(ints, k, start, i));
                start = i + 1;
                allMeet = false;
            }
        }
        if (allMeet) { return end - start; }
        return Math.max(longest, longestSubstring(ints, k, start, end));
    }

    public static void main(String[] args) {
        LongestSubstring ls = new LongestSubstring();
        String s = "bbaaacbd";
        int k = 3;
        int result = ls.longestSubstring(s, k);
        System.out.println(result);
    }
}
