package work.huangdu.exploration.primary_algorithms.math;

import work.huangdu.question_bank.easy.IsPowerOfFour;
import work.huangdu.question_bank.easy.IsPowerOfTwo;

/**
 * 3的幂
 * 给定一个整数，写一个函数来判断它是否是 3 的幂次方。
 * 示例 1:
 * 输入: 27
 * 输出: true
 * 示例 2:
 * 输入: 0
 * 输出: false
 * 示例 3:
 * 输入: 9
 * 输出: true
 * 示例 4:
 * 输入: 45
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/1 16:15
 * @see IsPowerOfTwo
 * @see IsPowerOfFour
 */
public class IsPowerOfThree {
    public boolean isPowerOfThree1(int n) {
        switch (n) {
            case 1:
            case 3:
            case 9:
            case 27:
            case 81:
            case 243:
            case 729:
            case 2187:
            case 6561:
            case 19683:
            case 59049:
            case 177147:
            case 531441:
            case 1594323:
            case 4782969:
            case 14348907:
            case 43046721:
            case 129140163:
            case 387420489:
            case 1162261467:
                return true;
        }
        return false;
    }

    public boolean isPowerOfThree(int n) {
        for (int i = 1; i <= n; i *= 3) {
            if (i == n)
                return true;
        }
        return false;
    }

    public boolean isPowerOfThree3(int n) {
        if (n == 0) return false;
        while (n % 3 == 0) {
            n /= 3;
        }
        return n == 1;
    }
}
