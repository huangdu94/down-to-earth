package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 693. 交替位二进制数
 * 给定一个正整数，检查他是否为交替位二进制数：换句话说，就是他的二进制数相邻的两个位数永不相等。
 * 示例 1:
 * 输入: 5
 * 输出: True
 * 解释:
 * 5的二进制数是: 101
 * 示例 2:
 * 输入: 7
 * 输出: False
 * 解释:
 * 7的二进制数是: 111
 * 示例 3:
 * 输入: 11
 * 输出: False
 * 解释:
 * 11的二进制数是: 1011
 * 示例 4:
 * 输入: 10
 * 输出: True
 * 解释:
 * 10的二进制数是: 1010
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/16 12:44
 */
public class HasAlternatingBits {
    // 暴力 转换为字符串 再判断字符串
    public boolean hasAlternatingBits(int n) {
        String nStr = Integer.toBinaryString(n);
        char pre = 0;
        for (int i = 0, len = nStr.length(); i < len; i++) {
            if (i != 0 && nStr.charAt(i) == pre) {
                return false;
            }
            pre = nStr.charAt(i);
        }
        return true;
    }

    // 位操作
    public boolean hasAlternatingBits2(int n) {
        int bit = n & 1;
        n >>= 1;
        while (n != 0) {
            if ((n & 1) == bit) {
                return false;
            }
            bit = n & 1;
            n >>= 1;
        }
        return true;
    }

    public static void main(String[] args) {
        HasAlternatingBits bits = new HasAlternatingBits();
        int n = 5;
        System.out.println(bits.hasAlternatingBits2(n));
    }
}
