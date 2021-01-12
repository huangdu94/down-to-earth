package work.huangdu.exploration.start_from_scratch.stack_recursion.calculator;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 224. 基本计算器
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式可以包含左括号 ( ，右括号 )，加号 + ，减号 -，非负整数和空格  。
 * 示例 1:
 * 输入: "1 + 1"
 * 输出: 2
 * 示例 2:
 * 输入: " 2-1 + 2 "
 * 输出: 3
 * 示例 3:
 * 输入: "(1+(4+5+2)-3)+(6+8)"
 * 输出: 23
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/10/23 20:25
 */
public class Calculate {
    private int index = 0;

    public int calculate2(String s) {
        int n = s.length(), res = 0, num = 0, top = 0;
        char sign = '+';
        int[] stack = new int[n / 2 + 1];
        while (index < n) {
            char c = s.charAt(index++);
            if ('0' <= c && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '(') {
                num = calculate2(s);
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || index == n) {
                switch (sign) {
                    case '+':
                        stack[top++] = num;
                        break;
                    case '-':
                        stack[top++] = -num;
                }
                if (index == n || c == ')') {
                    break;
                }
                sign = c;
                num = 0;
            }
        }
        for (int i = 0; i < top; i++) {
            res += stack[i];
        }
        return res;
    }

    public int calculate(String s) {
        int n = s.length(), res = 0, num = 0;
        char sign = '+';
        Deque<Integer> stack = new ArrayDeque<>();
        while (index < n) {
            char c = s.charAt(index++);
            if ('0' <= c && c <= '9') {
                num = num * 10 + (c - '0');
            } else if (c == '(') {
                num = calculate(s);
            }
            if (c == '+' || c == '-' || c == '*' || c == '/' || c == ')' || index == n) {
                switch (sign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                }
                if (index == n || c == ')') {
                    break;
                }
                sign = c;
                num = 0;
            }
        }
        while (!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
