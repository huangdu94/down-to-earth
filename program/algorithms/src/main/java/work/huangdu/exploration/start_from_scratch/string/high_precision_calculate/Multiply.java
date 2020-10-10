package work.huangdu.exploration.start_from_scratch.string.high_precision_calculate;

/**
 * 43. 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/13 10:22
 */
public class Multiply {

    // 将乘法转化为num1×num2的每一位，然后再求和的问题
    public String multiply(String num1, String num2) {
        if (num1.length() < num2.length()) return multiply(num2, num1);
        if ("0".equals(num1) || "0".equals(num2)) return "0";
        String res = "";
        for (int i = num2.length() - 1; i >= 0; i--) {
            res = addition(res, fillZero(
                    multiplySingleDigit(num1, charToInt(num2.charAt(i))), num2.length() - 1 - i));
        }
        return res;
    }

    // num1×(n2为 String num2中的一位)
    private String multiplySingleDigit(String num1, int n2) {
        char[] product = new char[num1.length() + 1];
        int index = product.length - 1, carry = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = charToInt(num1.charAt(i));
            int p = n1 * n2 + carry;
            carry = p / 10;
            product[index--] = intToChar(p % 10);
        }
        if (carry != 0) product[index--] = intToChar(carry);
        return new String(product, index + 1, product.length - 1 - index);
    }

    // 加法
    private String addition(String res, String next) {
        if ("".equals(res)) return next;
        char[] sum = new char[next.length() + 1];
        int index = sum.length - 1, carry = 0;
        for (int i = next.length() - 1, j = res.length() - 1; i >= 0; i--, j--) {
            int n1 = charToInt(next.charAt(i));
            int n2 = j >= 0 ? charToInt(res.charAt(j)) : 0;
            int s = n1 + n2 + carry;
            carry = s / 10;
            sum[index--] = intToChar(s % 10);
        }
        if (carry != 0) sum[index--] = intToChar(carry);
        return new String(sum, index + 1, sum.length - 1 - index);
    }

    // 末尾补0
    private String fillZero(String product, int i) {
        if (i == 0) return product;
        StringBuilder sb = new StringBuilder(product.length() + i);
        sb.append(product);
        for (int k = 0; k < i; k++) sb.append('0');
        return sb.toString();
    }

    // 字符转数字
    private int charToInt(char c) {
        return c - '0';
    }

    // 数字转字符
    private char intToChar(int i) {
        return (char) (i + '0');
    }

    public static void main(String[] args) {
        Multiply multiply = new Multiply();
        String num1 = "99";
        String num2 = "99";
        String res = multiply.multiply(num1, num2);
        System.out.println(res);
    }
}
