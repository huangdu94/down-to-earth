package club.huangdu94.exploration.start_from_scratch.string.high_precision_calculate;

/**
 * 415. 字符串相加
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * 提示：
 * num1 和num2 的长度都小于 5100
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/13 12:29
 */
public class AddStrings {
    public String addStrings(String num1, String num2) {
        if (num1.length() < num2.length()) return addStrings(num2, num1);
        char[] sum = new char[num1.length() + 1];
        int index = sum.length - 1, i = num1.length() - 1, j = num2.length() - 1, carry = 0;
        while (carry != 0 || j >= 0) {
            int n1 = i >= 0 ? charToInt(num1.charAt(i--)) : 0;
            int n2 = j >= 0 ? charToInt(num2.charAt(j--)) : 0;
            int s = n1 + n2 + carry;
            carry = s / 10;
            sum[index--] = intToChar(s % 10);
        }
        return num1.substring(0, i + 1) + new String(sum, index + 1, sum.length - 1 - index);
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
        AddStrings addStrings = new AddStrings();
        String num1 = "98";
        String num2 = "9";
        String res = addStrings.addStrings2(num1, num2);
        System.out.println(res);
    }

    public String addStrings2(String num1, String num2) {
        int n1 = num1.length(), n2 = num2.length(), i = n1 - 1, j = n2 - 1, add = 0;
        StringBuilder res = new StringBuilder();
        while (i >= 0 || j >= 0 || add != 0) {
            int unit1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int unit2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            int sum = unit1 + unit2 + add;
            res.append(sum % 10);
            add = sum / 10;
            i--;
            j--;
        }
        return res.reverse().toString();
    }
}
