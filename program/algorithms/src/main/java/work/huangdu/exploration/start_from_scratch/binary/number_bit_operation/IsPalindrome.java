package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 9. 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * 示例 1:
 * 输入: 121
 * 输出: true
 * 示例 2:
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * 你能不将整数转为字符串来解决这个问题吗？
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/6 16:55
 */
public class IsPalindrome {
    public boolean isPalindrome(int x) {
        if (x < 0 || x % 10 == 0 && x != 0) return false;
        if (x < 10) return true;
        int bits = 0;
        int[] digits = new int[10];
        while (x > 0) {
            digits[bits++] = x % 10;
            x /= 10;
        }
        int i = 0, j = bits - 1;
        while (i < j) {
            if (digits[i] != digits[j]) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        IsPalindrome isPalindrome = new IsPalindrome();
        System.out.println(isPalindrome.isPalindrome(1334331));
    }
}
