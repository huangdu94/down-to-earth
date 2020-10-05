package club.huangdu94.exploration.primary_algorithms.string;

/**
 * 7. 整数反转
 * 给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。
 * 示例 1:
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * 输入: 120
 * 输出: 21
 * 注意:
 * 假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 16:45
 */
public class Reverse {
    public int reverse(int x) {
        if (-10 < x && x < 10)
            return x;
        int symbol = 1;
        if (x < 0) {
            x = -x;
            symbol = -1;
        }
        int[] nums = new int[10];
        int len = 0;
        while (x != 0) {
            nums[len++] = x % 10;
            x /= 10;
        }
        long result = 0L;
        long times = 1;
        for (int i = len - 1; i >= 0; i--) {
            result += (nums[i] * times);
            times *= 10;
        }
        result *= symbol;
        if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE)
            return 0;
        return (int) result;
    }

    public int reverse2(int x) {
        int sign = x < 0 ? -1 : 1;
        if (x < 0) x *= -1;
        int reverse = 0;
        while (x > 0) {
            if (reverse > Integer.MAX_VALUE / 10
                    || reverse == Integer.MAX_VALUE / 10 && (sign == -1 && x % 10 > 8 || sign == 1 && x % 10 > 7)) {
                return 0;
            }
            reverse = reverse * 10 + (x % 10);
            x /= 10;
        }
        return sign * reverse;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }
}
