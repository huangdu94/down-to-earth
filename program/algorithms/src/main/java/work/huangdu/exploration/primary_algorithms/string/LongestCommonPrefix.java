package work.huangdu.exploration.primary_algorithms.string;

/**
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 示例 1:
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 * 所有输入只包含小写字母 a-z 。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 17:45
 */
public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0)
            return "";
        int minLen = Integer.MAX_VALUE;
        for (String str : strs)
            if (minLen > str.length())
                minLen = str.length();
        int prefixLen = 0;
        outloop:
        for (int i = 0; i < minLen; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (c != strs[j].charAt(i))
                    break outloop;
            }
            prefixLen++;
        }
        return strs[0].substring(0, prefixLen);
    }

    // 时间复杂度 o(n*k) (n为字符串个数，k为最长公共前缀长度)
    public String longestCommonPrefix2(String[] strs) {
        if (strs.length == 0) return "";
        for (int k = 0; ; k++) {
            Character c = null;
            for (String str : strs) {
                if (k == str.length() || c != null && str.charAt(k) != c) {
                    return str.substring(0, k);
                }
                if (c == null) {
                    c = str.charAt(k);
                }
            }
        }
    }
}
