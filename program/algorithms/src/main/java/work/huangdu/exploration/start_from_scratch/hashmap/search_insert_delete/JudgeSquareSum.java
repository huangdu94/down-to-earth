package work.huangdu.exploration.start_from_scratch.hashmap.search_insert_delete;

import java.util.HashSet;
import java.util.Set;

/**
 * 633. 平方数之和
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 * 示例 1：
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 * 输入：c = 3
 * 输出：false
 * 示例 3：
 * 输入：c = 4
 * 输出：true
 * 示例 4：
 * 输入：c = 2
 * 输出：true
 * 示例 5：
 * 输入：c = 1
 * 输出：true
 * 提示：
 * 0 <= c <= 2^31 - 1
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/31 13:30
 */
public class JudgeSquareSum {
    private static final Set<Integer> set = new HashSet<>();

    static {
        for (int i = 0, max = (int) Math.sqrt(Integer.MAX_VALUE); i <= max; i++) {
            set.add(i * i);
        }
    }

    public boolean judgeSquareSum(int c) {
        for (int i = 0, max = (int) Math.sqrt(c >> 1) + 1; i <= max; i++) {
            if (set.contains(c - i * i)) {
                return true;
            }
        }
        return false;
    }

    public boolean judgeSquareSum2(int c) {
        for (int i = 0, max = (int) Math.sqrt(c >> 1) + 1; i <= max; i++) {
            double num = Math.sqrt(c - i * i);
            if ((int) num == num) {
                return true;
            }
        }
        return false;
    }
}
