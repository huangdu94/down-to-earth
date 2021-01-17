package work.huangdu.exploration.advanced_algorithms.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 通配符匹配
 * 给定一个字符串(s) 和一个字符模式(p) ，实现一个支持'?'和'*'的通配符匹配。
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * 说明:
 * s可能为空，且只包含从a-z的小写字母。
 * p可能为空，且只包含从a-z的小写字母，以及字符?和*。
 * 示例1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例2:
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释:'*' 可以匹配任意字符串。
 * 示例3:
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释:'?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例4:
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释:第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例5:
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输出: false
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/15 11:50
 */
public class IsMatch {
    public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        if ("".equals(p)) return "".equals(s);
        // 按*分割字符串p
        List<String> plist = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                if (start != i) {
                    plist.add(p.substring(start, i));
                    start = i;
                }
                start++;
            }
        }
        if (start < p.length()) plist.add(p.substring(start));
        if (plist.isEmpty()) return true;
        boolean startFlag = p.charAt(0) == '*', endFlag = p.charAt(p.length() - 1) == '*', flag;
        if (!startFlag && !endFlag && plist.size() == 1) return compare(s, plist.get(0));
        if (startFlag) {
            flag = backtrack(s, s.length(), 0, plist, plist.size(), 0);
        } else {
            String first = plist.get(0);
            if (!startsWith(s, first, 0)) return false;
            flag = backtrack(s, s.length(), first.length(), plist, plist.size(), 1);
        }
        if (flag) {
            if (endFlag) return true;
            String last = plist.get(plist.size() - 1);
            return startsWith(s, last, s.length() - last.length());
        } else {
            return false;
        }
    }

    private boolean backtrack(String s, int sLen, int sIndex, List<String> plist, int pLen, int pIndex) {
        if (pIndex >= pLen) return true;
        String p = plist.get(pIndex);
        sIndex = indexOf(s, sLen, p, p.length(), sIndex);
        if (sIndex == -1) return false;
        return backtrack(s, sLen, sIndex + p.length(), plist, pLen, pIndex + 1);
    }

    /**
     * 从s的fromIndex位置向后寻找下一个p的位置，找不到返回-1
     *
     * @param s           源字符串
     * @param sourceCount 源字符串长度
     * @param p           目标字符串
     * @param targetCount 目标字符串长度
     * @param fromIndex   开始位置
     * @return p的开始下标，找不到返回-1
     */
    private int indexOf(String s, int sourceCount, String p, int targetCount, int fromIndex) {
        char first = p.charAt(0);
        int max = sourceCount - targetCount;
        for (int i = fromIndex; i <= max; i++) {
            //Look for first character.
            if (first != '?' && first != s.charAt(i))
                while (++i <= max && first != s.charAt(i)) ;
            //Found first character, now look at the rest of v2
            if (i <= max) {
                int j = i + 1;
                int end = j + targetCount - 1;
                for (int k = 1; j < end && (p.charAt(k) == '?'
                        || s.charAt(j) == p.charAt(k)); j++, k++)
                    ;
                //Found whole string.
                if (j == end) return i;
            }
        }
        return -1;
    }

    /**
     * 判断s和p是否相等
     *
     * @param s 源字符串
     * @param p 目标字符串
     * @return 源和目标是否相等
     */
    private boolean compare(String s, String p) {
        if (s.length() != p.length()) return false;
        int k = 0;
        while (k < s.length()) {
            char c1 = s.charAt(k);
            char c2 = p.charAt(k);
            if (c2 != '?' && c1 != c2) {
                return false;
            }
            k++;
        }
        return true;
    }

    /**
     * s的指定toffset位置开始与p是否匹配
     * 0的话 p是否匹配s的前缀
     * s.length() - prefix.length() p是否匹配s的后缀
     *
     * @param s       源字符串
     * @param prefix  目标字符串
     * @param toffset 偏移值
     * @return 是否匹配
     */
    public boolean startsWith(String s, String prefix, int toffset) {
        if (prefix.length() > s.length()) return false;
        int sIndex = toffset, pIndex = 0, pLen = prefix.length();
        char pC;
        while (--pLen >= 0) {
            pC = prefix.charAt(pIndex++);
            if (pC != '?' && pC != s.charAt(sIndex))
                return false;
            sIndex++;
        }
        return true;
    }

    /*public boolean isMatch(String s, String p) {
        if (s == null || p == null) return false;
        if ("".equals(p)) return "".equals(s);
        // 预处理，连续的*转化成一个*
        StringBuilder pSb = new StringBuilder(p.length());
        char pre = p.charAt(0), cur;
        pSb.append(pre);
        // 非*号的字符数
        int count = pre == '*' ? 0 : 1;
        for (int i = 1; i < p.length(); i++) {
            cur = p.charAt(i);
            if (cur != '*' || cur != pre) {
                pSb.append(cur);
                if (cur != '*') count++;
            }
            pre = cur;
        }
        p = pSb.toString();
        if ("*".equals(p)) return true;
        return backtrack(s, s.length(), 0, p, p.length(), 0, count);
    }*/

    /**
     * 回溯算法(超时)
     */
    /*private boolean backtrack(String s, int sLen, int sIndex, String p, int pLen, int pIndex, int count) {
        if (count > sLen - sIndex) return false;
        if (sIndex >= sLen || pIndex >= pLen) {
            return sIndex >= sLen && (pIndex >= pLen || count == 0);
        }
        char sC = s.charAt(sIndex);
        char pC = p.charAt(pIndex);
        if (pC == '*') {
            for (int i = sIndex; i <= sLen; i++) {
                if (backtrack(s, sLen, i, p, pLen, pIndex + 1, count)) {
                    return true;
                }
            }
        } else if (pC == '?' || pC == sC) {
            return backtrack(s, sLen, sIndex + 1, p, pLen, pIndex + 1, count - 1);
        }
        return false;
    }*/
    public static void main(String[] args) {
        IsMatch isMatch = new IsMatch();
        //String s = "abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb";
        //String p = "**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb";
        //String s = "adceb";
        //String p = "*a*b";
        String s = "aa";
        String p = "a";
        System.out.println(isMatch.isMatch(s, p));
    }
}
