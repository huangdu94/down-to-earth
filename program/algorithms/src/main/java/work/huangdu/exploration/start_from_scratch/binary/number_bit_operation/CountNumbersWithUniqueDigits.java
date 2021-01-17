package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 357. 计算各个位数不同的数字个数
 * 给定一个非负整数 n，计算各位数字都不同的数字 x 的个数，其中 0 ≤ x < 10^n 。
 * 示例:
 * 输入: 2
 * 输出: 91
 * 解释: 答案应为除去 11,22,33,44,55,66,77,88,99 外，在 [0,100) 区间内的所有数字。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/19 15:34
 */
public class CountNumbersWithUniqueDigits {
    public int countNumbersWithUniqueDigits(int n) {
        if (n == 0) return 1;
        if (n == 1) return 10;
        if (n > 10) return 0;
        int res = 9, i = 9, count = 1;
        while (count < n) {
            res *= i--;
            count++;
        }
        return res + countNumbersWithUniqueDigits(n - 1);
    }
}
