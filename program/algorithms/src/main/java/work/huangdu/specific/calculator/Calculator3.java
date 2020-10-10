package work.huangdu.specific.calculator;

import java.util.Stack;

import static work.huangdu.specific.calculator.Tools.isDigit;

/**
 * 加减乘除法计算器(支持括号版本)
 * 输入的这个算式只包含加减法(只包含正数，空格忽略)
 * 相比于Calculator2多了一个功能
 * 1. 支持括号
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/1 12:24
 */
public class Calculator3 {
    // 全局index，记录已经处理到哪个字符了
    private int index = 0;

    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        int len = s.length();
        char sign = '+';
        int num = 0;
        while (index < len) {
            char c = s.charAt(index++);
            if (isDigit(c))
                num = num * 10 + (c - '0');
            else if (c == '(')
                num = this.calculate(s);
            if ((!isDigit(c) && c != ' ') || index == len) {
                switch (sign) {
                    case '+':
                        numStack.push(num);
                        break;
                    case '-':
                        numStack.push(-num);
                        break;
                    case '*':
                        numStack.push(numStack.pop() * num);
                        break;
                    case '/':
                        numStack.push(numStack.pop() / num);
                        break;
                }
                if (c == ')')
                    break;
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

    public static void main(String[] args) {
        Calculator3 calculator3 = new Calculator3();
        String expression = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(calculator3.calculate(expression));
    }
}
