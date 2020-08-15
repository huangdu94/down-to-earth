package club.huangdu94.question_bank.easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 231. 2的幂
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 * 示例 1:
 * 输入: 1
 * 输出: true
 * 解释: 20 = 1
 * 示例 2:
 * 输入: 16
 * 输出: true
 * 解释: 24 = 16
 * 示例 3:
 * 输入: 218
 * 输出: false
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/15 13:13
 */
public class IsPowerOfTwo {
    public boolean isPowerOfTwo2(int n) {
        if (n <= 0) return false;
        return (n & (-n)) == n;
    }

    private static final Set<Integer> powerOfTwoSet = new HashSet<>(31);

    static {
        powerOfTwoSet.add(0X00000001);
        powerOfTwoSet.add(0X00000002);
        powerOfTwoSet.add(0X00000004);
        powerOfTwoSet.add(0X00000008);
        powerOfTwoSet.add(0X00000010);
        powerOfTwoSet.add(0X00000020);
        powerOfTwoSet.add(0X00000040);
        powerOfTwoSet.add(0X00000080);
        powerOfTwoSet.add(0X00000100);
        powerOfTwoSet.add(0X00000200);
        powerOfTwoSet.add(0X00000400);
        powerOfTwoSet.add(0X00000800);
        powerOfTwoSet.add(0X00001000);
        powerOfTwoSet.add(0X00002000);
        powerOfTwoSet.add(0X00004000);
        powerOfTwoSet.add(0X00008000);
        powerOfTwoSet.add(0X00010000);
        powerOfTwoSet.add(0X00020000);
        powerOfTwoSet.add(0X00040000);
        powerOfTwoSet.add(0X00080000);
        powerOfTwoSet.add(0X00100000);
        powerOfTwoSet.add(0X00200000);
        powerOfTwoSet.add(0X00400000);
        powerOfTwoSet.add(0X00800000);
        powerOfTwoSet.add(0X01000000);
        powerOfTwoSet.add(0X02000000);
        powerOfTwoSet.add(0X04000000);
        powerOfTwoSet.add(0X08000000);
        powerOfTwoSet.add(0X10000000);
        powerOfTwoSet.add(0X20000000);
        powerOfTwoSet.add(0X40000000);
    }

    public boolean isPowerOfTwo(int n) {
        return powerOfTwoSet.contains(n);
    }
}
