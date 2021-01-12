package work.huangdu.exploration.start_from_scratch.hashmap.search_insert_delete;

import java.util.*;

/**
 * 290. 单词规律
 * 给定一种规律 pattern 和一个字符串 str ，判断 str 是否遵循相同的规律。
 * 这里的 遵循 指完全匹配，例如， pattern 里的每个字母和字符串 str 中的每个非空单词之间存在着双向连接的对应规律。
 * 示例1:
 * 输入: pattern = "abba", str = "dog cat cat dog"
 * 输出: true
 * 示例 2:
 * 输入:pattern = "abba", str = "dog cat cat fish"
 * 输出: false
 * 示例 3:
 * 输入: pattern = "aaaa", str = "dog cat cat dog"
 * 输出: false
 * 示例 4:
 * 输入: pattern = "abba", str = "dog dog dog dog"
 * 输出: false
 * 说明:
 * 你可以假设 pattern 只包含小写字母， str 包含了由单个空格分隔的小写字母。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/18 21:11
 */
public class WordPattern {
    public boolean wordPattern(String pattern, String str) {
        // 1. 如果参数有null则直接返回false
        if (pattern == null || str == null) return false;
        // 2. 去首尾空格
        str = str.trim();
        // 3. 如果它们有一个是空字符串则直接返回false
        int pLen = pattern.length(), sLen = str.length();
        if (pLen == 0 || sLen == 0) return false;
        // 4. 按空格分隔
        int start = 0, k = 0, index, i;
        String[] map = new String[26];
        Set<String> set = new HashSet<>(pLen);
        String temp, s;
        for (i = 1; i < sLen && k < pLen; i++) {
            if (str.charAt(i) == ' ') {
                index = pattern.charAt(k++) - 'a';
                temp = map[index];
                s = str.substring(start, i);
                if (temp == null) {
                    if (set.contains(s)) return false;
                    map[index] = s;
                    set.add(s);
                } else if (!temp.equals(s)) {
                    return false;
                }
                start = i + 1;
            }
        }
        // 5.1 k到尾了，str还没到尾
        if (i < sLen) return false;
        // 5.2 str到尾了，k还差多于一个到尾
        if (i == sLen && pLen - k > 1) return false;
        // 5.3 str到尾了，k还有一个到尾，比较最后一个str片段是否满足要求
        if (i == sLen && pLen - k == 1) {
            s = str.substring(start, i);
            temp = map[pattern.charAt(k) - 'a'];
            if (temp == null) return !set.contains(s);
            return temp.equals(s);
        }
        return false;
    }

    public boolean wordPattern2(String pattern, String s) {
        Set<String> exist = new HashSet<>();
        String[] wordPattern = new String[26];
        char[] patterns = pattern.toCharArray();
        String[] strArr = s.split(" ");
        if (patterns.length != strArr.length) {
            return false;
        }
        int n = patterns.length;
        for (int i = 0; i < n; i++) {
            int p = patterns[i] - 'a';
            String t = strArr[i];
            if (wordPattern[p] == null && !exist.contains(t)) {
                wordPattern[p] = t;
                exist.add(t);
            } else if (!t.equals(wordPattern[p])) {
                return false;
            }
        }
        return true;
    }

    public boolean wordPattern3(String pattern, String s) {
        String[] sArr = s.split("\\s");
        if (pattern.length() != sArr.length) return false;
        int n = pattern.length();
        Map<Character, Integer> pMap = new HashMap<>();
        Map<String, Integer> sMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            if (Objects.equals(pMap.put(pattern.charAt(i), i), sMap.put(sArr[i], i))) {
                return false;
            }
        }
        return true;
    }
}
