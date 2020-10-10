package work.huangdu.exploration.primary_algorithms.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/1 22:19
 */
public class Valid {
    private static final Map<Character, Character> map = new HashMap<>();

    static {
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
    }

    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        if (s.length() == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (stack.empty() || stack.peek() != '(')
                    return false;
                else
                    stack.pop();
            } else if (c == '}') {
                if (stack.empty() || stack.peek() != '{')
                    return false;
                else
                    stack.pop();
            } else if (c == ']') {
                if (stack.empty() || stack.peek() != '[')
                    return false;
                else
                    stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }

    public static void main(String[] args) {
        System.out.println(new Valid().isValid("[])"));
    }

    public boolean isValid2(String s) {
        if (s == null || s.length() == 0) return true;
        if (s.length() == 1) return false;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                if (stack.empty() || stack.pop() != map.get(c))
                    return false;
            } else {
                stack.push(c);
            }
        }
        return stack.empty();
    }
}
