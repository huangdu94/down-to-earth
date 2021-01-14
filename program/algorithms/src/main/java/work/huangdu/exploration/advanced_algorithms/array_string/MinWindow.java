package work.huangdu.exploration.advanced_algorithms.array_string;

import java.util.HashMap;
import java.util.Map;

/**
 * 最小覆盖子串
 * 给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字符的最小子串。
 * 示例：
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/27 20:29
 */
public class MinWindow {
    // 双指针法(通过)
    public String minWindow(String s, String t) {
        // 特殊情况处理，统统返回空字符串
        if (s == null || s.length() == 0 || t == null || t.length() == 0) return "";
        // String t预处理
        Map<Character, Integer> tMap = this.charCountForString(t);
        int sLen = s.length();
        int i = 0, j = -1;
        int minLen = -1, start = 0, end = 0;
        while (i < sLen) {
            // 抛弃没有用的字符
            while (i < sLen) {
                char c = s.charAt(i);
                Integer count = tMap.get(c);
                if (count == null) {
                    i++;
                } else if (count < 0) {
                    i++;
                    tMap.put(c, count + 1);
                } else if (count == 0) {
                    if (this.judge(tMap)) {
                        if (minLen == -1 || minLen > j - i) {
                            start = i;
                            end = j;
                            minLen = end - start;
                        }
                    }
                    // 如果j已经等于sLen了 再抛弃i位置字符后面不可能再有满足条件的字符串了
                    if (j < sLen) {
                        i++;
                        tMap.put(c, count + 1);
                    }
                    break;
                } else {
                    break;
                }
            }
            // 只有第一轮需要赋值j
            if (j == -1) j = i;
            if (j == sLen) break;
            while (j < sLen) {
                char c = s.charAt(j++);
                Integer count = tMap.get(c);
                if (count != null) {
                    tMap.put(c, count - 1);
                    if (count == 1)
                        if (this.judge(tMap)) {
                            if (minLen == -1 || minLen > j - i) {
                                start = i;
                                end = j;
                                minLen = end - start;
                            }
                            break;
                        }
                }
            }
        }
        return minLen == -1 ? "" : s.substring(start, end);
    }

    // 暴力解优化，从等于t长度大小的窗口开始寻找，找到位置 依然超时 继续优化
    public String minWindow2(String s, String t) {
        String result = "";
        // String t预处理
        Map<Character, Integer> tMap = this.charCountForString(t);
        outloop:
        for (int k = t.length(); k <= s.length(); k++) {
            for (int i = 0; i <= s.length() - k; i++) {
                String temp = this.isInclude(s.substring(i, i + k), tMap);
                if (!"".equals(temp)) {
                    result = temp;
                    break outloop;
                }
            }
        }
        return result;
    }

    // 暴力解,时间复杂度o(n^3) 超出时间限制
    public String minWindow3(String s, String t) {
        String result = "";
        // String t预处理
        Map<Character, Integer> tMap = this.charCountForString(t);
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String temp = this.isInclude(s.substring(i, j), tMap);
                if ("".equals(result))
                    result = temp;
                else if (!"".equals(temp) && temp.length() < result.length())
                    result = temp;
            }
        }
        return result;
    }

    // 统计一个字符串中各字母总共有多少个
    private Map<Character, Integer> charCountForString(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            map.merge(c, 1, Integer::sum);
        }
        return map;
    }

    // 判断是否已经满足包含
    private boolean judge(Map<Character, Integer> map) {
        for (int count : map.values())
            if (count > 0)
                return false;
        return true;
    }

    // 判断一个字符串是否包含另一个字符串
    private boolean isInclude(Map<Character, Integer> sMap, Map<Character, Integer> tMap) {
        boolean flag = true;
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            Integer amount = sMap.get(entry.getKey());
            if (amount == null || amount < entry.getValue()) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    private String isInclude(String sub, Map<Character, Integer> tMap) {
        Map<Character, Integer> subMap = this.charCountForString(sub);
        boolean flag = this.isInclude(subMap, tMap);
        return flag ? sub : "";
    }

    public static void main(String[] args) {
        MinWindow window = new MinWindow();
        String s = "BBAAC";
        String t = "ABA";
        System.out.println(window.minWindow(s, t));
    }

    public String minWindow4(String s, String t) {
        if (s == null || t == null) return "";
        if (s.length() < t.length()) return "";

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();

        int[] map = new int[256]; // 各字符的欠账表
        for (char c : str2) {
            map[c]++;
        }
        int all = str2.length; // 总欠账

        int L = 0;
        int R = 0;
        // [L, R)，str1[0]不在窗口里面

        int startIndex = -1;
        int len = Integer.MAX_VALUE;

        while (R < str1.length) {
            // [R]进入窗口
            map[str1[R]]--; // 欠账减1
            if (map[str1[R]] >= 0) { // 如果是一个有效的还账
                all--; // 总欠账减1
            }

            if (all == 0) { // 总欠账还清了，准备结算
                // 但此时的窗口不一定是最小的，再看下L能否向右缩窗口，获得一个以R结尾时最优的答案

                // L向右缩窗口，直到缩到不能再缩为止（再缩窗口，将不再达标）
                while (map[str1[L]] < 0) {
                    map[str1[L]]++; // [L]字符的欠账加1
                    L++;
                }

                // 窗口已经是以R结尾时的最优窗口，结算答案
                int thisLen = R - L + 1;
                if (thisLen < len) {
                    len = thisLen;
                    startIndex = L;
                }

                // 结算完答案，[L]出窗口 （还完账了，L出窗口，又将欠账了）
                map[str1[L]]++;
                L++;
                all++; // 又欠账了
            }

            // 窗口继续向右扩，继续尝试以新的R结尾时，有没有答案
            R++;

        }

        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + len);
    }

    public String minWindow5(String s, String t) {
        int[] countsT = new int[128], countsS = new int[128];
        int ns = s.length(), nt = t.length(), satisfy = 0, start = 0, end = 0, minStart = -1, minLen = -1;
        for (int i = 0; i < nt; i++) {
            countsT[t.charAt(i)]++;
        }
        while (end < ns) {
            char c = s.charAt(end);
            if (++countsS[c] <= countsT[c]) {
                satisfy++;
            }
            if (satisfy == nt) {
                while (countsS[s.charAt(start)] > countsT[s.charAt(start)]) {
                    countsS[s.charAt(start++)]--;
                }
                if (minStart == -1 || minLen > end - start + 1) {
                    minStart = start;
                    minLen = end - start + 1;
                }
            }
            while (satisfy == nt || minStart != -1 && end - start + 1 >= minLen) {
                if (--countsS[s.charAt(start)] < countsT[s.charAt(start)]) {
                    satisfy--;
                }
                start++;
            }
            end++;
        }
        return minStart == -1 ? "" : s.substring(minStart, minStart + minLen);
    }
}
