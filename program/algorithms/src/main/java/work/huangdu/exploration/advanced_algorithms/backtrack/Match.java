package work.huangdu.exploration.advanced_algorithms.backtrack;

/**
 * 正则表达式匹配
 * 给你一个字符串s和一个字符规律p，请你来实现一个支持 '.'和'*'的正则表达式匹配。
 * '.' 匹配任意单个字符
 * '*' 匹配零个或多个前面的那一个元素
 * 所谓匹配，是要涵盖整个字符串s的，而不是部分字符串。
 * 说明:
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符.和*。
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释:因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * 示例3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释:".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释:因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/19 22:49
 */
public class Match {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        if (p.length() == 0) return s.length() == 0;
        // 预处理p
        // 1. 如果一个字母后面跟着一个*,则转换其为大写字母
        // 2. 如果一个.后面跟着一个*,则记为*
        int sLen = s.length(), pLen = p.length(), pArrLen = 0;
        char[] pArray = new char[pLen];
        for (int i = 0; i < pLen; i++) {
            char ch = p.charAt(i);
            if (i + 1 < pLen && p.charAt(i + 1) == '*') {
                pArray[pArrLen++] = ch == '.' ? '*' : lowerToUpper(ch);
                i++;
            } else {
                pArray[pArrLen++] = ch;
            }
        }
        // 动态规划
        boolean[][] dp = new boolean[pArrLen + 1][sLen + 1];
        dp[0][0] = true;
        // 当前s走到的位置，为上一层第一个true的位置
        int start = 0;
        for (int i = 1; i <= pArrLen; i++) {
            char pChar = pArray[i - 1];
            // 记录这一层是否有true
            boolean haveTrue = false;
            for (int j = start; j <= sLen; j++) {
                if (pChar == '*') {
                    haveTrue = true;
                    // 该层从start位置开始后面全都为true
                    for (int k = j; k <= sLen; k++) {
                        dp[i][k] = true;
                    }
                    break;
                } else if (isUpperCase(pChar)) {
                    pChar = upperToLower(pChar);
                    haveTrue = true;
                    // 该层start位置可以为true
                    dp[i][j] = true;
                    /*
                       start后面：
                       1. 如果上层k位置为true肯定可以为true；
                       2. 如果本层上一个位置为true，则查看k位置(对应index k-1)s是否与p相等
                           2.1 如果相等则为true
                           2.2 不相等循环不可以结束(后面不一定都为false)
                       3. 如果本层上一个位置也不为true，循环不可以结束，说不定后面还有机会
                       */
                    for (int k = j + 1; k <= sLen; k++) {
                        if (dp[i - 1][k]) {
                            dp[i][k] = true;
                        } else if (dp[i][k - 1] && s.charAt(k - 1) == pChar) {
                            dp[i][k] = true;
                        }
                    }
                    break;
                } else {
                    // 如果上一层的j位置为true，这一层pChar与s的j+1位置(对应index为j)字符匹配
                    // 则这一层j+1位置为true(注意判断j+1<=sLen 即j<=sLen-1)
                    // 记录第一个为true的位置做为下一层开始位置
                    if (dp[i - 1][j] && j <= sLen - 1 && match(pChar, s.charAt(j))) {
                        dp[i][j + 1] = true;
                        if (!haveTrue) {
                            start = j + 1;
                            haveTrue = true;
                        }
                    }
                }
            }
            // 如果一层没有true则直接返回false
            if (!haveTrue) return false;
        }
        return dp[pArrLen][sLen];
    }

    /**
     * 小写字母转大写字母
     */
    private char lowerToUpper(char ch) {
        return (char) (ch - 32);
    }

    /**
     * 大写字母转小写字母
     */
    private char upperToLower(char ch) {
        return (char) (ch + 32);
    }

    /**
     * 是大写字母
     */
    private boolean isUpperCase(char ch) {
        return ch >= 'A' && ch <= 'Z';
    }

    /**
     * 是小写字母
     */
    private boolean isLowerCase(char ch) {
        return ch >= 'a' && ch <= 'z';
    }

    /**
     * 两字母是否匹配
     */
    private boolean match(char ch1, char ch2) {
        return ch1 == '.' || ch1 == ch2;
    }

    public static void main(String[] args) {
        Match match = new Match();
        String s = "cbaacacaaccbaabcb";
        String p = "c*b*b*.*ac*.*bc*a*";
        System.out.println(match.isMatch(s, p));
    }
}
