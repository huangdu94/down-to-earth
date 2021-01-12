package work.huangdu.exploration.intermediate_algorithms.other;

/**
 * 两整数之和
 * 不使用运算符 + 和 - ​​​​​​​，计算两整数 ​​​​​​​a 、b ​​​​​​​之和。
 * 示例 1:
 * 输入: a = 1, b = 2
 * 输出: 3
 * 示例 2:
 * 输入: a = -2, b = 3
 * 输出: 1
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/7/22 0:22
 */
public class GetSum {
    /*public int getSum(int a, int b) {
        return a + b;
    }*/
    public int getSum(int a, int b) {
        int sum = 0;
        int mask = 1;
        // 是否进位
        boolean carry = false;
        while (mask != 0) {
            int aBit = a & mask;
            int bBit = b & mask;
            if (aBit == 0 && bBit == 0) {
                if (carry) {
                    sum |= mask;
                    carry = false;
                }
            } else if (aBit == 0 || bBit == 0) {
                if (!carry)
                    sum |= mask;
            } else {
                if (carry)
                    sum |= mask;
                carry = true;
            }
            mask <<= 1;
        }
        return sum;
    }
}
