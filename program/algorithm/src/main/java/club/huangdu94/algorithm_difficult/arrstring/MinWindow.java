package club.huangdu94.algorithm_difficult.arrstring;

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
 * @author duhuang@iflytek.com
 * @version 2020/7/27 20:29
 */
public class MinWindow {
    // 暴力解优化，从等于t长度大小的窗口开始寻找，找到位置 依然超时 继续优化
    public String minWindow(String s, String t) {
        return null;
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
        String s = "ADOBECODEBANC";
        String t = "ABC";
        System.out.println(window.minWindow(s, t));
    }
}
