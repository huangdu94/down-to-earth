package work.huangdu.exploration.intermediate_algorithms.array_string;

import java.util.HashMap;
import java.util.Map;

/**
 * 3. 无重复字符的最长子串
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
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/2 10:38
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

    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), max = 0, start = 0, end;
        boolean[] exists = new boolean[128];
        for (end = 0; end < n; end++) {
            int c = s.charAt(end);
            if (!exists[c]) {
                exists[c] = true;
            } else {
                max = Math.max(max, end - start);
                char temp;
                while ((temp = s.charAt(start++)) != c) {
                    exists[temp] = false;
                }
            }
        }
        return Math.max(max, end - start);
    }

    public static void main(String[] args) {
        LengthOfLongestSubstring lols = new LengthOfLongestSubstring();
        System.out.println(lols.lengthOfLongestSubstring("abcabcbb"));
    }
}
