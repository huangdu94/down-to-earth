package work.huangdu.exploration.start_from_scratch.stack_recursion.bracket_match;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 32. 最长有效括号
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * 示例 1:
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/25 10:35
 */
public class LongestValidParentheses {

    // 最后一个未匹配的右括号入栈，左括号的下标也要入栈，并且不止是占位用处
    public int longestValidParentheses2(String s) {
        int n = s.length(), max = 0;
        Deque<Integer> stack = new LinkedList<>();
        stack.push(-1);
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }

    // 暴力 时间复杂度o(n^3) 超出时间限制
    public int longestValidParentheses(String s) {
        int n = s.length(), max = 0;
        for (int i = 0; n - i > max; i++) {
            while (i < n && s.charAt(i) == ')') {
                i++;
            }
            for (int j = (n - i) % 2 == 0 ? n : n - 1; j - i > max; j -= 2) {
                if (isValid(s.substring(i, j))) {
                    max = j - i;
                }
            }
        }
        return max;
    }

    private boolean isValid(String sub) {
        int n = sub.length(), l = 0;
        for (int i = 0; i < n; i++) {
            if (sub.charAt(i) == '(') {
                l++;
            } else if (l > 0) {
                l--;
            } else {
                return false;
            }
        }
        return l == 0;
    }
}
