package work.huangdu.exploration.start_from_scratch.hashmap.search_insert_delete;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 205. 同构字符串
 * 给定两个字符串 s 和 t，判断它们是否是同构的。
 * 如果 s 中的字符可以被替换得到 t ，那么这两个字符串是同构的。
 * 所有出现的字符都必须用另一个字符替换，同时保留字符的顺序。两个字符不能映射到同一个字符上，但字符可以映射自己本身。
 * 示例 1:
 * 输入: s = "egg", t = "add"
 * 输出: true
 * 示例 2:
 * 输入: s = "foo", t = "bar"
 * 输出: false
 * 示例 3:
 * 输入: s = "paper", t = "title"
 * 输出: true
 * 说明:
 * 你可以假设 s 和 t 具有相同的长度。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/14 1:23
 */
public class IsIsomorphic {
    public boolean isIsomorphic(String s, String t) {
        if (s.isEmpty()) return true;
        Map<Character, Character> s_t_map = new HashMap<>(s.length());
        Set<Character> t_exist = new HashSet<>(t.length());
        for (int i = 0; i < s.length(); i++) {
            char sc = s.charAt(i);
            char tc = t.charAt(i);
            Character t_expect = s_t_map.get(sc);
            if (t_expect == null && t_exist.contains(tc)
                    || t_expect != null && t_expect != tc) return false;
            s_t_map.put(sc, tc);
            t_exist.add(tc);
        }
        return true;
    }

    public boolean isIsomorphic2(String s, String t) {
        Map<Character, Integer> map_s = new HashMap<>();
        Map<Character, Integer> map_t = new HashMap<>();
        if (s.length() != t.length()) {
            return false;
        }
        for (Integer i = 0, n = s.length(); i < n; i++) {
            if (map_s.put(s.charAt(i), i) != map_t.put(t.charAt(i), i)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IsIsomorphic isIsomorphic = new IsIsomorphic();
        String s = "ab";
        String t = "aa";
        System.out.println(isIsomorphic.isIsomorphic(s, t));
    }
}
