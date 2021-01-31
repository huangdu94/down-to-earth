package work.huangdu.question_bank.easy;

/**
 * 441. 排列硬币
 * 你总共有 n 枚硬币，你需要将它们摆成一个阶梯形状，第 k 行就必须正好有 k 枚硬币。
 * 给定一个数字 n，找出可形成完整阶梯行的总行数。
 * n 是一个非负整数，并且在32位有符号整型的范围内。
 * 示例 1:
 * n = 5
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤
 * 因为第三行不完整，所以返回2.
 * 示例 2:
 * n = 8
 * 硬币可排列成以下几行:
 * ¤
 * ¤ ¤
 * ¤ ¤ ¤
 * ¤ ¤
 * 因为第四行不完整，所以返回3.
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/30
 */
public class ArrangeCoins {
    public int arrangeCoins(int n) {
        if (n == 0) return 0;
        int l = 0, r = 65536, result = 0;
        while (l <= r) {
            int mid = l + (r - l) / 2;
            long count = (long) (mid + 1) * mid / 2;
            if (count == n) {
                return mid;
            } else if (count < n) {
                result = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return result;
    }

    // 二分
    public int arrangeCoins2(int n) {
        if (n == 0) return 0;
        int l = 0, r = 65536;
        while (r - l > 1) {
            int mid = l + (r - l) / 2;
            long count = count(mid);
            if (count == n) {
                return mid;
            } else if (count < n) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return count(r) > n ? l : r;
    }

    private long count(int level) {
        return (long) (level + 1) * level / 2;
    }

    public static void main(String[] args) {
//        System.out.println(Math.sqrt((long) Integer.MAX_VALUE * 2));
//        long k = 65535;
//        System.out.println((1 + k) * k / 2 < Integer.MAX_VALUE);
        System.out.println(new ArrangeCoins().arrangeCoins(5));
    }
}
