package work.huangdu.question_bank.easy;

/**
 * 367. 有效的完全平方数
 * 给定一个正整数 num，编写一个函数，如果 num 是一个完全平方数，则返回 True，否则返回 False。
 * 说明：不要使用任何内置的库函数，如  sqrt。
 * 示例 1：
 * 输入：16
 * 输出：True
 * 示例 2：
 * 输入：14
 * 输出：False
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/2 15:58
 */
public class IsPerfectSquare {
    public boolean isPerfectSquare1(int num) {
        int i = 1;
        while (i < 46341 && i * i < num) {
            i++;
        }
        return i * i == num;
    }

    public boolean isPerfectSquare(int num) {
        int l = 1, r = 46340;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            int square = mid * mid;
            if (square == num) {
                return true;
            } else if (square < num) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        IsPerfectSquare isPerfectSquare = new IsPerfectSquare();
        int num = 16;
        System.out.println(isPerfectSquare.isPerfectSquare(num));
    }
}
