package work.huangdu.specific.calculator;

import java.util.Stack;

/**
 * 加减乘除法计算器
 * 输入的这个算式只包含加减法(只包含正数，空格忽略)
 * 相比于Calculator1多了两个功能
 * 1. 支持乘除法，并满足四则运算法则要求
 * 2. 支持空格(会自动忽略数字与符号之间的空格)
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/1 12:24
 */
public class Calculator2 {
    public int calculate(String s) {
        Stack<Integer> numStack = new Stack<>();
        int len = s.length();
        char sign = '+';
        int num = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (Tools.isDigit(c))
                num = num * 10 + (c - '0');
            if ((!Tools.isDigit(c) && c != ' ') || i == len - 1) {
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
}
