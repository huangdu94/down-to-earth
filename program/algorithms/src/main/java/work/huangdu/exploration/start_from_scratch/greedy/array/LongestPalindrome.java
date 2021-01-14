package work.huangdu.exploration.start_from_scratch.greedy.array;

/**
 * 409. 最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 * 注意:
 * 假设字符串的长度不会超过 1010。
 * 示例 1:
 * 输入:
 * "abccccdd"
 * 输出:
 * 7
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/20 17:19
 */
public class LongestPalindrome {
    public int longestPalindrome(String s) {
        if (s == null || s.length() == 0) return 0;
        int[] counts = new int[52];
        for (int i = 0; i < s.length(); i++) {
            counts[getMapping(s.charAt(i))]++;
        }
        int res = 0;
        // 是否可以加1
        boolean flag = false;
        for (int c : counts) {
            if (c % 2 == 0)
                res += c;
            else {
                res += (c - 1);
                if (!flag) flag = true;
            }
        }
        return flag ? res + 1 : res;
    }

    /**
     * A-Z -> 0-25
     * a-z -> 26-51
     */
    private int getMapping(char ch) {
        return ch >= 'a' ? ch - 72 : ch - 65;
    }

    public int longestPalindrome2(String s) {
        int[] counts = new int[128];
        for (char c : s.toCharArray()) {
            counts[c]++;
        }
        boolean flag = false;
        int len = 0;
        for (int count : counts) {
            if (count != 0) {
                if ((count & 1) == 0) {
                    len += count;
                } else {
                    if (!flag) {
                        len += count;
                        flag = true;
                    } else {
                        len += (count - 1);
                    }
                }
            }
        }
        return len;
    }
}
