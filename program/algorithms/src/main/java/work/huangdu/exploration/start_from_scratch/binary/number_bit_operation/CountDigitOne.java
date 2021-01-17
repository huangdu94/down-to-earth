package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 233. 数字 1 的个数
 * 给定一个整数 n，计算所有小于等于 n 的非负整数中数字 1 出现的个数。
 * 示例:
 * 输入: 13
 * 输出: 6
 * 解释: 数字 1 出现在以下数字中: 1, 10, 11, 12, 13 。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/19 16:15
 */
public class CountDigitOne {
    // 111
    // 12次个位是1
    // 12次十位是1
    // 12次百位是1
    public int countDigitOne(int n) {
        if (n < 1) return 0;
        int count = 0, bits = 1, tempN = n;
        while (tempN != 0) {
            int curBit = tempN % 10;
            tempN /= 10;
            if (curBit == 0) {
                count += tempN * bits;
            } else if (curBit == 1) {
                count += tempN * bits + n % bits + 1;
            } else {
                count += (tempN + 1) * bits;
            }
            bits *= 10;
        }
        return count;
    }
}
