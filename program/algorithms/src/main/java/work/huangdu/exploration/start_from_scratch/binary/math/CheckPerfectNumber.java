package work.huangdu.exploration.start_from_scratch.binary.math;

/**
 * 507. 完美数
 * 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
 * 示例 1：
 * 输入：28
 * 输出：True
 * 解释：28 = 1 + 2 + 4 + 7 + 14
 * 1, 2, 4, 7, 和 14 是 28 的所有正因子。
 * 示例 2：
 * 输入：num = 6
 * 输出：true
 * 示例 3：
 * 输入：num = 496
 * 输出：true
 * 示例 4：
 * 输入：num = 8128
 * 输出：true
 * 示例 5：
 * 输入：num = 2
 * 输出：false
 * 提示：
 * 1 <= num <= 10^8
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/20 16:46
 */
public class CheckPerfectNumber {
    public boolean checkPerfectNumber(int num) {
        if (num == 1) return false;
        int i = 2, sum = 1;
        while (true) {
            while (num % i != 0 && i < num / i) {
                i++;
            }
            int j = num / i;
            if (i < j) {
                sum += (i + j);
            } else if (i == j && num % i == 0) {
                sum += i;
            } else {
                break;
            }
            i++;
        }
        return sum == num;
    }

    public static void main(String[] args) {
        CheckPerfectNumber cpn = new CheckPerfectNumber();
        System.out.println(cpn.checkPerfectNumber(28));
    }
}
