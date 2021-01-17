package work.huangdu.question_bank.easy;

import work.huangdu.exploration.primary_algorithms.math.IsPowerOfThree;

/**
 * 342. 4的幂
 * 给定一个整数 (32 位有符号整数)，请编写一个函数来判断它是否是 4 的幂次方。
 * 示例 1:
 * 输入: 16
 * 输出: true
 * 示例 2:
 * 输入: 5
 * 输出: false
 * 进阶：
 * 你能不使用循环或者递归来完成本题吗？
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/1 10:32
 * @see IsPowerOfTwo
 * @see IsPowerOfThree
 */
public class IsPowerOfFour {
    public boolean isPowerOfFour1(int num) {
        return Integer.toString(num, 4).matches("^10*$");
    }

    public boolean isPowerOfFour2(int num) {
        return Math.log(num) / Math.log(4) % 1 < 1e-6;
    }

    public boolean isPowerOfFour3(int num) {
        if (num < 1) {
            return false;
        }
        while (num % 4 == 0) {
            num /= 4;
        }
        return num == 1;
    }

    public static void main(String[] args) {
        int num = 1073741824;
        System.out.println(new IsPowerOfFour().isPowerOfFour2(num));
        for (int i = 0; i < 16; i++) {
            System.out.println((int) Math.pow(4, i));
        }
        System.out.println(Math.log(-1) <= 0);
    }
}
