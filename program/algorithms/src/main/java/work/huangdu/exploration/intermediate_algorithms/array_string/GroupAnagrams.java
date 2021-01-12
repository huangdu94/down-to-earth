package work.huangdu.exploration.intermediate_algorithms.array_string;

import java.util.*;

/**
 * 字母异位词分组
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 * 示例:
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出:
 * [
 * ["ate","eat","tea"],
 * ["nat","tan"],
 * ["bat"]
 * ]
 * 说明：
 * 所有输入均为小写字母。
 * 不考虑答案输出的顺序。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/7/2 10:37
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String key = getKey(str);
            List<String> list = map.computeIfAbsent(key, k -> new ArrayList<>());
            list.add(str);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 得到排序字符串
     *
     * @param s 原字符串
     * @return 排序后字符串
     */
    private String getKey(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs.length == 0) return new ArrayList<>();
        List<List<String>> res = new ArrayList<>();
        for (String str : strs) {
            boolean flag = false;
            for (List<String> list : res) {
                if (isAnagram(list.get(0), str)) {
                    list.add(str);
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                List<String> list = new ArrayList<>();
                list.add(str);
                res.add(list);
            }
        }
        return res;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;
        int len = s.length();
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            int si = s.charAt(i) - 'a';
            int ti = t.charAt(i) - 'a';
            if (si != ti) {
                count[si]++;
                count[ti]--;
            }
        }
        for (int c : count) {
            if (c != 0) {
                return false;
            }
        }
        return true;
    }
}
