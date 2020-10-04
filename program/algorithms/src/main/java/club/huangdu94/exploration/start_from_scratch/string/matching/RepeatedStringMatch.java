package club.huangdu94.exploration.start_from_scratch.string.matching;

/**
 * 686. 重复叠加字符串匹配
 * 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 * 示例 1：
 * 输入：a = "abcd", b = "cdabcdab"
 * 输出：3
 * 解释：a 重复叠加三遍后为 "abcdabcdabcd", 此时 b 是其子串。
 * 示例 2：
 * 输入：a = "a", b = "aa"
 * 输出：2
 * 示例 3：
 * 输入：a = "a", b = "a"
 * 输出：1
 * 示例 4：
 * 输入：a = "abc", b = "wxyz"
 * 输出：-1
 * 提示：
 * 1 <= a.length <= 10^4
 * 1 <= b.length <= 10^4
 * a 和 b 由小写英文字母组成
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/4 19:33
 */
public class RepeatedStringMatch {
    public int repeatedStringMatch(String a, String b) {
        int na = a.length(), nb = b.length();
        int count = nb / na;
        if (nb % na != 0) {
            count++;
        }
        StringBuilder sb = new StringBuilder((count + 1) * nb);
        for (int k = 0; k < count; k++) {
            sb.append(a);
        }
        if (sb.toString().lastIndexOf(b) != -1) {
            return count;
        }
        sb.append(a);
        if (sb.toString().lastIndexOf(b) != -1) {
            return count + 1;
        }
        return -1;
    }
}
