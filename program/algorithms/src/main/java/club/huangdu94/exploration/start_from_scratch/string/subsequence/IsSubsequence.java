package club.huangdu94.exploration.start_from_scratch.string.subsequence;

/**
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 你可以认为 s 和 t 中仅包含英文小写字母。字符串 t 可能会很长（长度 ~= 500,000），而 s 是个短字符串（长度 <=100）。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 * 示例 1:
 * s = "abc", t = "ahbgdc"
 * 返回 true.
 * 示例 2:
 * s = "axc", t = "ahbgdc"
 * 返回 false.
 * 后续挑战 :
 * 如果有大量输入的 S，称作S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/30 12:51
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        int from = 0;
        for (char c : s.toCharArray()) {
            if ((from = t.indexOf(c, from) + 1) == 0) {
                return false;
            }
        }
        return true;
    }

    public boolean isSubsequence2(String s, String t) {
        int n = t.length();
        int[][] dp = new int[n + 1][26];
        for (int i = 0; i < 26; i++) {
            dp[n][i] = n;
        }
        for (int i = n - 1; i >= 0; i--) {
            int c = t.charAt(i) - 'a';
            for (int j = 0; j < 26; j++) {
                if (j == c) {
                    dp[i][c] = i;
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        int len = s.length(), add = 0, i = 0;
        while (i < len) {
            int c = s.charAt(i) - 'a';
            add = dp[add][c] + 1;
            if (add > n) break;
            i++;
        }
        return i == len;
    }

    public static void main(String[] args) {
        IsSubsequence subsequence = new IsSubsequence();
        String s = "abc";
        String t = "ahbgdc";
        System.out.println(subsequence.isSubsequence2(s, t));
    }
}
