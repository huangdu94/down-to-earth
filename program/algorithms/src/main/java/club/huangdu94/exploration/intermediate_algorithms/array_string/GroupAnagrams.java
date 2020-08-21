package club.huangdu94.exploration.intermediate_algorithms.array_string;

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
 * @author duhuang@iflytek.com
 * @version 2020/7/2 10:37
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> strMap = new HashMap<>();
        for (String str : strs) {
            String key = this.sortStr(str);
            List<String> value = strMap.get(key);
            if (value == null) {
                value = new ArrayList<>();
                value.add(str);
                strMap.put(key, value);
            } else {
                value.add(str);
            }
        }
        return new ArrayList<>(strMap.values());
    }

    /**
     * 得到排序字符串
     *
     * @param str 原字符串
     * @return 排序后字符串
     */
    private String sortStr(String str) {
        char[] chars = str.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }
}
