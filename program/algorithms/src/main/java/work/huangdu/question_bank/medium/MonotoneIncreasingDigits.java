package work.huangdu.question_bank.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 738. 单调递增的数字
 * 给定一个非负整数 N，找出小于或等于 N 的最大的整数，同时这个整数需要满足其各个位数上的数字是单调递增。
 * （当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时，我们称这个整数是单调递增的。）
 * 示例 1:
 * 输入: N = 10
 * 输出: 9
 * 示例 2:
 * 输入: N = 1234
 * 输出: 1234
 * 示例 3:
 * 输入: N = 332
 * 输出: 299
 * 说明: N 是在 [0, 10^9] 范围内的一个整数。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/12/15 9:53
 */
public class MonotoneIncreasingDigits {
    public int monotoneIncreasingDigits(int n) {
        // 如果n为个位数则一定是单调递增的直接返回
        if (n < 10) return n;
        List<Integer> nums = new ArrayList<>(10);
        while (n != 0) {
            nums.add(n % 10);
            n /= 10;
        }
        int size = nums.size();
        for (int i = 0; i + 1 < size; i++) {
            if (nums.get(i) < nums.get(i + 1)) {
                nums.set(i, 9);
                for (int k = 0; k < i; k++) {
                    nums.set(k, 9);
                }
                while (i + 1 < size && nums.get(i + 1) == 0) {
                    nums.set(++i, 9);
                }
                nums.set(i + 1, nums.get(i + 1) - 1);
            }
        }
        int result = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (nums.get(i) == 0) continue;
            result = result * 10 + nums.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        MonotoneIncreasingDigits mid = new MonotoneIncreasingDigits();
        System.out.println(mid.monotoneIncreasingDigits(100));
    }
}
