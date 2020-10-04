package club.huangdu94.exploration.primary_algorithms.string;

/**
 * 28. 实现 strStr()
 * 实现 strStr() 函数。
 * 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。
 * 示例 1:
 * 输入: haystack = "hello", needle = "ll"
 * 输出: 2
 * 示例 2:
 * 输入: haystack = "aaaaa", needle = "bba"
 * 输出: -1
 * 说明:
 * 当 needle 是空字符串时，我们应当返回什么值呢？这是一个在面试中很好的问题。
 * 对于本题而言，当 needle 是空字符串时我们应当返回 0 。这与C语言的 strstr() 以及 Java的 indexOf() 定义相符。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 17:17
 */
public class StrStr {
    // KMP算法
/*    public int strStr(String haystack, String needle) {
        int needleLen = needle.length();
        if (needleLen == 0)
            return 0;
        int haystackLen = haystack.length();
        int i = 0, j = 0;
        int[] next = getNext(needle);
        while (i < haystackLen && j < needleLen)
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else if (j == 0)  // j已经到了模式串的第一个字符了，退无可退
                i++;      // 主串老老实实后移吧
            else       // 模式串的j还能后移
                j = next[j];
        return j == needleLen ? i - j : -1;
    }

    private int[] getNext(String s) {
        if (s.length() == 1)
            return new int[]{-1};
        int[] next = new int[s.length()];
        next[0] = -1;   // 人为规定为-1,这个数字永远不会用到
        next[1] = 0;    // 人为规定为0，因前面就一个字符，前后缀又不允许是字符串本身
        int i = 2;
        int p = 0;  //p为前缀的后一个字符位置
        while (i < s.length()) {
            if (s.charAt(i - 1) == s.charAt(p)) {
                next[i] = p + 1;  // 匹配到的前缀长度（为前缀最后一个字符下标 + 1）
                p++;
                i++;
            } else {
                if (p == 0) {      // 你连第一个字符都不匹配，前后缀没有，next为0
                    next[i] = 0;
                    i++;
                } else {
                    p = next[p];  // 前缀还很长，发生不匹配不慌，再找更小的前缀试试
                }
            }
        }
        return next;
    }*/

    // 暴力解
    public int strStr2(String haystack, String needle) {
        // 不会为null
        int needleLen = needle.length();
        if (needleLen == 0)
            return 0;
        int haystackLen = haystack.length();
        int pos = 0;
        while (pos < haystackLen - needleLen + 1) {
            int i = pos;
            int j = 0;
            while (j < needleLen) {
                char c1 = haystack.charAt(i);
                char c2 = needle.charAt(j);
                if (c1 == c2) {
                    i++;
                    j++;
                } else {
                    pos++;
                    break;
                }
            }
            if (j == needleLen)
                return pos;
        }
        return -1;
    }

    public int strStr3(String haystack, String needle) {
        int h = haystack.length(), n = needle.length();
        for (int i = 0; i < h - n + 1; i++) {
            int j = 0;
            while (j < n && haystack.charAt(i + j) == needle.charAt(j)) {
                j++;
            }
            if (j == n) {
                return i;
            }
        }
        return -1;
    }

    // KMP算法
    public int strStr(String haystack, String needle) {
        if (needle.length() == 0) return 0;
        int h = haystack.length(), n = needle.length(), i = 0, j = 0;
        int[] next = getNext(needle);
        while (i < h && j < n) {
            if (j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            } else {
                j = next[j];
            }
        }
        if (j == n) {
            return i - n;
        }
        return -1;
    }

    private int[] getNext(String needle) {
        int n = needle.length(), i = 0, j = -1;
        int[] next = new int[n];
        next[0] = -1;
        while (i < n - 1) {
            if (j == -1 || needle.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                next[i] = j;
            } else {
                j = next[j];
            }
        }
        return next;
    }
}
