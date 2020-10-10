package work.huangdu.exploration.intermediate_algorithms.math;

import java.util.HashSet;
import java.util.Set;

/**
 * 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 * 「快乐数」定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 True ；不是，则返回 False 。
 * 示例：
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/22 0:13
 */
public class IsHappy {
    public boolean isHappy(int n) {
        if (n <= 0)
            return false;
        return isHappy(n, new HashSet<>());
    }

    /**
     * 递归判断是否是快乐数
     *
     * @param n   正整数
     * @param set 已经出现过的数(再出现就返回false)
     * @return 是否是快乐数
     */
    private boolean isHappy(int n, Set<Integer> set) {
        if (n == 1)
            return true;
        set.add(n);
        int next = next(n);
        if (set.contains(next))
            return false;
        return isHappy(next, set);
    }

    /**
     * 将一个正整数替换为它每个位置上的数字的平方和
     *
     * @param n 正整数
     * @return 每个位置上的数字的平方和
     */
    private int next(int n) {
        int next = 0;
        while (n > 0) {
            next += Math.pow(n % 10, 2);
            n /= 10;
        }
        return next;
    }
}
