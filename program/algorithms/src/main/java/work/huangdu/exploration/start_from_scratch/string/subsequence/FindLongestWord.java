package work.huangdu.exploration.start_from_scratch.string.subsequence;

import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * 给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。
 * 示例 1:
 * 输入:
 * s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 输出:
 * "apple"
 * 示例 2:
 * 输入:
 * s = "abpcplea", d = ["a","b","c"]
 * 输出:
 * "a"
 * 说明:
 * 所有输入的字符串只包含小写字母。
 * 字典的大小不会超过 1000。
 * 所有输入的字符串长度不会超过 1000。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/30 13:07
 */
public class FindLongestWord {
    public String findLongestWord(String s, List<String> d) {
        int n = s.length();
        int[][] dp = new int[n + 1][26];
        for (int i = 0; i < 26; i++) {
            dp[n][i] = n;
        }
        for (int i = n - 1; i >= 0; i--) {
            int c = s.charAt(i) - 'a';
            for (int j = 0; j < 26; j++) {
                if (j == c) {
                    dp[i][c] = i;
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        d.sort((o1, o2) -> {
            if (o1.length() == o2.length())
                return o1.compareTo(o2);
            return o2.length() - o1.length();
        });
        for (String t : d) {
            int len = t.length(), add = 0, i = 0;
            while (i < len) {
                int c = t.charAt(i) - 'a';
                add = dp[add][c] + 1;
                if (add > n) break;
                i++;
            }
            if (i == len) {
                return t;
            }
        }
        return "";
    }
}
