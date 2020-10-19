package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 400. 第N个数字
 * 在无限的整数序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...中找到第 n 个数字。
 * 注意:
 * n 是正数且在32位整数范围内 ( n < 2^31)。
 * 示例 1:
 * 输入:
 * 3
 * 输出:
 * 3
 * 示例 2:
 * 输入:
 * 11
 * 输出:
 * 0
 * 说明:
 * 第11个数字在序列 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... 里是0，它是10的一部分。
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/19 15:45
 */
public class FindNthDigit {
    // 1位的数9个
    // 2位的数90个
    // 3位的数900个...
    public int findNthDigit(int n) {
        int count = 9, bits = 1;
        while (count != 900000000 && n > count * bits) {
            n -= (count * bits);
            count *= 10;
            bits++;
        }
        int targetNum = count / 9 + --n / bits, targetBit = n % bits + 1;
        while (targetBit < bits) {
            targetNum /= 10;
            targetBit++;
        }
        return targetNum % 10;
    }

    public static void main(String[] args) {
        FindNthDigit findNthDigit = new FindNthDigit();
        System.out.println(findNthDigit.findNthDigit(1000000000));
    }
}
