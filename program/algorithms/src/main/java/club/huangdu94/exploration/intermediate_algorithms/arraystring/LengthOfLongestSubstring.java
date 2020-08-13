package club.huangdu94.exploration.intermediate_algorithms.arraystring;

import java.util.HashMap;
import java.util.Map;

/**
 * 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/2 10:38
 */
public class LengthOfLongestSubstring {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int i = 0;
        int result = 0;
        int subLen = 0;
        Map<Character, Integer> charIndexMap = new HashMap<>();
        while (i < s.length()) {
            char cur = s.charAt(i);
            Integer pre = charIndexMap.get(cur);
            if (pre == null) {
                charIndexMap.put(cur, ++i);
                subLen++;
            } else {
                if (result < subLen)
                    result = subLen;
                subLen = 0;
                charIndexMap.clear();
                i = pre;
            }
        }
        if (result < subLen)
            result = subLen;
        return result;
    }
}
