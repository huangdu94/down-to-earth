package work.huangdu.exploration.intermediate_algorithms.array_string;

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
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/2 10:39
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

    public String longestPalindrome2(String s) {
        // 边界情况
        if (s == null || s.length() <= 1) return s;
        int begin = 0, subLen = 1, len = s.length(), i, j;
        for (int k = 0; k < 2 * len - subLen; k++) {
            i = k / 2;
            j = k / 2 + k % 2;
            while (i >= 0 && j < len && s.charAt(i) == s.charAt(j)) {
                i--;
                j++;
            }
            if ((j - i - 1) > subLen) {
                subLen = j - i - 1;
                begin = i + 1;
            }
        }
        return s.substring(begin, begin + subLen);
    }

    // 马拉车
    public String longestPalindrome3(String s) {
        if (s == null || s.length() <= 1) return s;
        // 1. 马拉车算法预处理 开头^ 结尾$ 中间#
        int len = s.length() * 2 + 3;
        char[] chars = new char[len];
        chars[0] = '^';
        for (int i = 1; i < len - 1; i++) {
            chars[i] = i % 2 == 1 ? '#' : s.charAt(i / 2 - 1);
        }
        chars[len - 1] = '$';
        // 2. 马拉车算法计算部分
        int[] p = new int[len];
        int c = 0, r = 0;
        for (int i = 1; i < len - 1; i++) {
            int i_mirror = 2 * c - i;
            if (r > i) {
                // 超过了r的部分是不可以算的
                p[i] = Math.min(p[i_mirror], r - i);
            } else {
                // 如果i就超过了或者等于r的话，则p[i]赋值为0
                // i只有在初始时才有可能小于r，其它时候只有可能等于r
                p[i] = 0;
            }
            // 中心扩展法： 针对 1. i_mirror碰到了左边界 2. i碰到了右边界
            while (chars[i + p[i] + 1] == chars[i - p[i] - 1]) {
                p[i]++;
            }
            // 当i的回文右边界超过了r，更新c和r
            if (i + p[i] > r) {
                c = i;
                r = i + p[i];
            }
        }
        // 3. 寻找最大的子串(start开始记录中心位置，以避免重复计算)
        int maxLen = 1, start = 0;
        for (int i = 1; i < len - 1; i++) {
            if (p[i] > maxLen) {
                start = i;
                maxLen = p[i];
            }
        }
        start = (start - maxLen) / 2;
        // 4. 返回字符串
        return s.substring(start, start + maxLen);
    }

    public static void main(String[] args) {
        System.out.println(new LongestPalindrome().longestPalindrome2("bb"));
    }
}
