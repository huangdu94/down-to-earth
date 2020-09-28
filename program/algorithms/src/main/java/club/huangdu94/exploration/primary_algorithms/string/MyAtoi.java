package club.huangdu94.exploration.primary_algorithms.string;

/**
 * 8. 字符串转换整数 (atoi)
 * 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。接下来的转化规则如下：
 * 如果第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字字符组合起来，形成一个有符号整数。
 * 假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成一个整数。
 * 该字符串在有效的整数部分之后也可能会存在多余的字符，那么这些字符可以被忽略，它们对函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换，即无法进行有效转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0 。
 * 提示：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−2^31,  2^31 − 1]。如果数值超过这个范围，请返回  INT_MAX (2^31 − 1) 或 INT_MIN (−2^31) 。
 * 示例 1:
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−2^31) 。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 17:11
 */
public class MyAtoi {
    public static int INTEGER_MAX_DIVIDE10 = Integer.MAX_VALUE / 10;

    public int myAtoi(String str) {
        if (str == null || str.length() < 1)
            return 0;
        // + -号只能有一个,flag为false表示已经出现了不能再出现
        boolean flag = true;
        // true 表示+ false表示-
        boolean sign = true;
        int result = 0;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (flag && c == ' ')
                continue;
            if (flag) {
                if (c == '-')
                    sign = false;
                else if (c == '+') ;
                else if (c >= '0' && c <= '9')
                    result = c - '0';
                else
                    return 0;
                flag = false;
            } else {
                if (c >= '0' && c <= '9') {
                    if (result > INTEGER_MAX_DIVIDE10) {
                        if (sign)
                            return Integer.MAX_VALUE;
                        else
                            return Integer.MIN_VALUE;
                    }
                    if (result == INTEGER_MAX_DIVIDE10) {
                        if (sign) {
                            if (c > '7')
                                return Integer.MAX_VALUE;
                        } else {
                            if (c > '8')
                                return Integer.MIN_VALUE;
                        }
                    }
                    result = result * 10 + (c - '0');
                } else {
                    break;
                }
            }
        }
        return sign ? result : -result;
    }

    /**
     * 有限状态机
     * 符号分为：0.空格 1.正负号 2.数字 3.其它
     * 状态分为：
     * 一、 初始状态： 0->一 1、2->二 3->结束
     * 二、 数字状态：2->二 0、1、3->结束 (数字溢出时直接结束)
     * 三、 结束状态
     */
    public int myAtoi2(String str) {
        int len = str.length(), i = 0;
        while (i < len && str.charAt(i) == ' ') {
            i++;
        }
        if (i == len) return 0;
        char first = str.charAt(i++);
        if (first == '+' || first == '-' || first >= '0' && first <= '9') {
            // + true - false
            boolean sign = first != '-';
            int num = first >= '0' ? first - '0' : 0;
            char next;
            while (i < len && (next = str.charAt(i)) >= '0' && next <= '9') {
                if (num > 214748364 || num == 214748364 && (sign && next > '7' || !sign && next > '8')) {
                    return sign ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                }
                num = num * 10 + (next - '0');
                i++;
            }
            return sign ? num : -num;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE / 10);
        System.out.println(Integer.MIN_VALUE / 10);
        System.out.println(Integer.MAX_VALUE % 10);
        System.out.println(Integer.MIN_VALUE % 10);
    }
}
