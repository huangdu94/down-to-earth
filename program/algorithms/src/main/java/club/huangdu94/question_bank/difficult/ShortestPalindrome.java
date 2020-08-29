package club.huangdu94.question_bank.difficult;

/**
 * 214. 最短回文串
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * 示例 1:
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 * 示例 2:
 * 输入: "abcd"
 * 输出: "dcbabcd"
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/29 20:45
 */
public class ShortestPalindrome {
    public String shortestPalindrome(String s) {
        // 马拉车算法来一遍
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
        // 从p字符串的后面往前面找，从index0开始的最长的回文子串
        int k = len - 1;
        while (k > 0 && (k - p[k]) / 2 != 0) {
            k--;
        }
        // 0 -> p[i]-1;
        /*char[] complete = new char[s.length() - p[k]];
        for (int i = s.length() - 1, j = 0; i >= p[k]; i--, j++) {
            complete[j] = s.charAt(i);
        }
        return new String(complete).concat(s);*/
        return new StringBuilder(s.substring(p[k])).reverse().append(s).toString();
    }

    public static void main(String[] args) {
        ShortestPalindrome shortestPalindrome = new ShortestPalindrome();
        String s = "aacecaaa";
        String res = shortestPalindrome.shortestPalindrome(s);
        System.out.println(res);
    }
}
