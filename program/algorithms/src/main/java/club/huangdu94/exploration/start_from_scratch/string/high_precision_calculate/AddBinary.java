package club.huangdu94.exploration.start_from_scratch.string.high_precision_calculate;

/**
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * 提示：
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/8 10:02
 */
public class AddBinary {
    public String addBinary(String a, String b) {
        if (a.length() < b.length())
            return addBinary(b, a);
        int aLen = a.length();
        int bLen = b.length();
        //是否进位
        boolean carry = false;
        int aIndex = aLen - 1;
        int bIndex = bLen - 1;
        char[] cChars = new char[aLen + 1];
        int cIndex = aLen;
        while (bIndex >= 0) {
            char aChar = a.charAt(aIndex--);
            char bChar = b.charAt(bIndex--);
            if (carry) {
                if (aChar == '0' && bChar == '0') {
                    cChars[cIndex--] = '1';
                    carry = false;
                } else if (aChar == '0' ^ bChar == '0') {
                    cChars[cIndex--] = '0';
                    carry = true;
                } else {
                    cChars[cIndex--] = '1';
                    carry = true;
                }
            } else {
                if (aChar == '0' && bChar == '0') {
                    cChars[cIndex--] = '0';
                    carry = false;
                } else if (aChar == '0' ^ bChar == '0') {
                    cChars[cIndex--] = '1';
                    carry = false;
                } else {
                    cChars[cIndex--] = '0';
                    carry = true;
                }
            }
        }
        while (aIndex >= 0 && carry) {
            char aChar = a.charAt(aIndex--);
            if (aChar == '0') {
                cChars[cIndex--] = '1';
                carry = false;
            } else {
                cChars[cIndex--] = '0';
                carry = true;
            }
        }
        while (aIndex >= 0) {
            cChars[cIndex--] = a.charAt(aIndex--);
        }
        cChars[cIndex] = carry ? '1' : '0';
        return carry ? new String(cChars, 0, aLen + 1) : new String(cChars, 1, aLen);
    }

    public static void main(String[] args) {
        AddBinary addBinary = new AddBinary();
        System.out.println(addBinary.addBinary("1", "111"));
    }

    public String addBinary2(String a, String b) {
        int na = a.length(), nb = b.length(), ia = na - 1, ib = nb - 1, add = 0;
        StringBuilder res = new StringBuilder();
        while (ia >= 0 || ib >= 0 || add != 0) {
            int num1 = ia >= 0 ? a.charAt(ia) - '0' : 0;
            int num2 = ib >= 0 ? b.charAt(ib) - '0' : 0;
            int sum = num1 + num2 + add;
            res.append(sum & 1);
            add = sum >> 1;
            ia--;
            ib--;
        }
        return res.reverse().toString();
    }
}
