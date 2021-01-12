package work.huangdu.exploration.intermediate_algorithms.math;

import java.util.HashMap;
import java.util.Map;

/**
 * 166. 分数到小数
 * 给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。
 * 如果小数部分为循环小数，则将循环的部分括在括号内。
 * 示例 1:
 * 输入: numerator = 1, denominator = 2
 * 输出: "0.5"
 * 示例 2:
 * 输入: numerator = 2, denominator = 1
 * 输出: "2"
 * 示例 3:
 * 输入: numerator = 2, denominator = 3
 * 输出: "0.(6)"
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/7/22 0:21
 */
public class FractionToDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == Integer.MIN_VALUE && denominator == -1)
            return "2147483648";
        StringBuilder sb = new StringBuilder();
        // 处理小数点前的部分
        int pre = numerator / denominator;
        if (pre == 0 && (numerator < 0 && denominator > 0 || numerator > 0 && denominator < 0))
            sb.append('-');
        sb.append(pre);
        if ((numerator = (numerator % denominator) * 10) != 0) {
            // numerator和denominator转化为正数,防止Integer.MIN_VALUE溢出所以用long
            long numeratorLong = Math.abs((long) numerator);
            long denominatorLong = Math.abs((long) denominator);
            sb.append('.');
            // 用来记录循环小数括号的插入位置
            int index = sb.length();
            Map<Long, Integer> map = new HashMap<>();
            do {
                if (map.containsKey(numeratorLong)) {
                    sb.insert(map.get(numeratorLong).intValue(), '(').append(')');
                    break;
                }
                map.put(numeratorLong, index++);
                long cur = numeratorLong / denominatorLong;
                sb.append(cur);
                numeratorLong = (numeratorLong % denominatorLong) * 10;
            } while (numeratorLong != 0);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FractionToDecimal fractionToDecimal = new FractionToDecimal();
        String result = fractionToDecimal.fractionToDecimal(100000, 3);
        System.out.println(result);
        System.out.println(-(long) Integer.MIN_VALUE);
    }

    public String fractionToDecimal2(int n, int d) {
        // 1. 特殊情况处理
        if (n == 0) return "0";
        // 2. 处理非特殊情况(int转long避免处理溢出繁琐)
        StringBuilder sb = new StringBuilder();
        // 2.1 处理符号
        if (n < 0 ^ d < 0) sb.append('-');
        long numerator = Math.abs((long) n);
        long denominator = Math.abs((long) d);
        // 2.2 处理整数部分
        sb.append(numerator / denominator);
        // 2.3 处理小数部分
        if (numerator % denominator != 0) {
            sb.append('.');
            int start = sb.length();
            long remain = numerator % denominator;
            Map<Long, Integer> map = new HashMap<>();
            while (remain != 0 && !map.containsKey(remain)) {
                map.put(remain, start++);
                sb.append((remain *= 10) / denominator);
                remain %= denominator;
            }
            // 2.4 处理循环小数括号
            if (remain != 0) {
                sb.insert(map.get(remain), "(");
                sb.append(')');
            }
        }
        return sb.toString();
    }
}
