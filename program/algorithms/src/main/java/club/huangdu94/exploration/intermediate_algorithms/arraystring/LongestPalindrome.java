package club.huangdu94.exploration.intermediate_algorithms.arraystring;

/**
 * 最长回文子串
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * 示例 1：
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/2 10:39
 */
public class LongestPalindrome {
    public String longestPalindrome(String s) {
        // 边界情况
        if (s == null)
            return null;
        int len = s.length();
        if (len <= 1)
            return s;
        int begin = 0;
        int subLen = 1;
        char[] chars = s.toCharArray();
        // (len-i)*2+1>=result.length()
        for (int i = 0; i < len - (subLen - 1) / 2; i++) {
            // 1. 以i为中心检测回文
            int curLen1 = this.getPlalindrome(chars, len, i, i);
            // 2. 以i和i+1为中心检测回文
            int curLen2 = this.getPlalindrome(chars, len, i, i + 1);
            int max = Math.max(curLen1, curLen2);
            if (max > subLen) {
                subLen = max;
                begin = i - (subLen - 1) / 2;
            }
        }
        return s.substring(begin, begin + subLen);
    }

    /**
     * 得到以i为中心的最长回文子串
     *
     * @param chars 字符串转换的字符数字
     * @param len   字符串长度
     * @param left  左指针
     * @param right 右指针
     * @return 以i(或i和i + 1)为中心最大回文子串的长度
     */
    private int getPlalindrome(char[] chars, int len, int left, int right) {
        while (left >= 0 && right < len) {
            if (chars[left] != chars[right])
                break;
            left--;
            right++;
        }
        //subLen = (right - 1) - (left + 1) + 1;
        return right - left - 1;
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome("babad"));
    }
}
