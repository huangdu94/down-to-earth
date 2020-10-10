package work.huangdu.specific.calculator;

import java.util.Stack;

/**
 * 加减法计算器
 * 输入的这个算式只包含加减法，而且不存在空格(只包含正数)
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/1 11:46
 */
public class Calculator1 {
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        int len = s.length();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Tools.isDigit(c))
                num = num * 10 + (c - '0');
            if (!Tools.isDigit(c) || i == len - 1) {
                switch (sign) {
                    case '+':
                        numStack.push(num);
                        break;
                    case '-':
                        numStack.push(-num);
                        break;
                }
                sign = c;
                num = 0;
            }
        }
        int result = 0;
        while (!numStack.empty()) {
            result += numStack.pop();
        }
        return result;
    }

    public int calculate2(String s) {
        int len = s.length();
        char sign = '+';
        int result = 0;
        int num = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Tools.isDigit(c))
                num = num * 10 + (c - '0');
            if (!Tools.isDigit(c) || i == len - 1) {
                switch (sign) {
                    case '+':
                        result += num;
                        break;
                    case '-':
                        result += -num;
                        break;
                }
                sign = c;
                num = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Calculator1 calculator1 = new Calculator1();
        String expression = "12+1-123";
        System.out.println(calculator1.calculate(expression));
        System.out.println(calculator1.calculate2(expression));
    }
}