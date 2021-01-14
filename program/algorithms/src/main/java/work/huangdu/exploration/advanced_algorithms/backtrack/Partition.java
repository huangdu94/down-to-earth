package work.huangdu.exploration.advanced_algorithms.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 分割回文串
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 * 返回 s 所有可能的分割方案。
 * 示例:
 * 输入:"aab"
 * 输出:
 * [
 * ["aa","b"],
 * ["a","a","b"]
 * ]
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/13 13:59
 */
public class Partition {
    private final List<List<String>> res = new ArrayList<>();
    private final List<String> combination = new ArrayList<>();

    public List<List<String>> partition(String s) {
        if (s == null || s.length() == 0) return res;
        backtrack(s, 0);
        return res;
    }

    /**
     * 回溯方法
     *
     * @param s     字符串
     * @param start 当前位置
     */
    private void backtrack(String s, int start) {
        for (int end = start + 1; end <= s.length(); end++) {
            if (isPlalindrome(s, start, end - 1)) {
                combination.add(s.substring(start, end));
                if (end == s.length()) {
                    res.add(new ArrayList<>(combination));
                } else {
                    backtrack(s, end);
                }
                combination.remove(combination.size() - 1);
            }
        }
    }

    /**
     * 判断字符串[i,j]子串是否为回文串
     *
     * @param s 字符串
     * @param i 子串开始
     * @param j 子串结尾
     * @return 是否为回文串
     */
    private boolean isPlalindrome(String s, int i, int j) {
        while (i < j)
            if (s.charAt(i++) != s.charAt(j--)) return false;
        return true;
    }
}
