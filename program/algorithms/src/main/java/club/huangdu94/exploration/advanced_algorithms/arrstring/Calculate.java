package club.huangdu94.exploration.advanced_algorithms.arrstring;

import java.util.Arrays;
import java.util.List;

/**
 * 基本计算器 II
 * 实现一个基本的计算器来计算一个简单的字符串表达式的值。
 * 字符串表达式仅包含非负整数，+， - ，*，/ 四种运算符和空格  。 整数除法仅保留整数部分。
 * 示例 1:
 * 输入: "3+2*2"
 * 输出: 7
 * 示例 2:
 * 输入: " 3/2 "
 * 输出: 1
 * 示例 3:
 * 输入: " 3+5 / 2 "
 * 输出: 5
 * 说明：
 * 你可以假设所给定的表达式都是有效的。
 * 请不要使用内置的库函数 eval。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/27 19:17
 */
public class Calculate {
    private static final List<Character> symbolList = Arrays.asList('+', '-', '*', '/');
    private int index;

    // + - * / 43 45 42 47
    public int calculate(String s) {
        int[] suffixArr = this.transformSuffix(s);
        this.index = suffixArr.length - 1;
        return this.calculate(suffixArr);
    }

    private int calculate(int[] suffixArr) {
        int a, b, cur = suffixArr[index--];
        switch (cur) {
            case -43:
                b = this.calculate(suffixArr);
                a = this.calculate(suffixArr);
                return a + b;
            case -45:
                b = this.calculate(suffixArr);
                a = this.calculate(suffixArr);
                return a - b;
            case -42:
                b = this.calculate(suffixArr);
                a = this.calculate(suffixArr);
                return a * b;
            case -47:
                b = this.calculate(suffixArr);
                a = this.calculate(suffixArr);
                return a / b;
            default:
                return cur;
        }
    }

    // 中缀表达式 -> 后缀表达式
    // 逆波兰式（Reverse Polish notation，RPN，或逆波兰记法）
    private int[] transformSuffix(String s) {
        int len = s.length();
        int resultIndex = 0, symbolIndex = 0, i = 0;
        int[] result = new int[len];
        char[] symbol = new char[2];
        while (i < len) {
            char c = s.charAt(i++);
            if (c != ' ') {
                if (symbolList.contains(c)) {
                    // 1. 如果pre为*/ c为+-则栈中全部弹出
                    // 2. 如果pre为*/ c为*/则栈中弹出pre
                    // 3. 如果pre为+- c为+-则栈中弹出pre
                    // 4. 如果pre为+- c为*/ 不弹
                    // 以上都压入c (综上 栈中最多存两个符号)
                    if (symbolIndex != 0) {
                        char pre = symbol[symbolIndex - 1];
                        if ((pre == '*' || pre == '/') && (c == '+' || c == '-')) {
                            while (symbolIndex != 0) {
                                pre = symbol[--symbolIndex];
                                result[resultIndex++] = -pre;
                            }
                        } else if ((pre == '*' || pre == '/') && (c == '*' || c == '/')
                                || (pre == '+' || pre == '-') && (c == '+' || c == '-')) {
                            pre = symbol[--symbolIndex];
                            result[resultIndex++] = -pre;
                        }
                    }
                    symbol[symbolIndex++] = c;
                } else {
                    int num = c - '0';
                    while (i < len && s.charAt(i) <= '9' && s.charAt(i) >= '0') {
                        int cNum = s.charAt(i++) - '0';
                        num = (num * 10 + cNum);
                    }
                    result[resultIndex++] = num;
                }
            }
        }
        while (symbolIndex != 0) {
            char pre = symbol[--symbolIndex];
            result[resultIndex++] = -pre;
        }
        return Arrays.copyOf(result, resultIndex);
    }

    public static void main(String[] args) {
        String s = "1+1/2+213";
        Calculate calculate = new Calculate();
        System.out.println(calculate.calculate(s));
    }

    // 基本思想 问题拆解
}
