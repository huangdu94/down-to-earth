package work.huangdu.exploration.start_from_scratch.string.character_statistics;

/**
 * 389. 找不同
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 * 请找出在 t 中被添加的字母。
 * 示例:
 * 输入：
 * s = "abcd"
 * t = "abcde"
 * 输出：
 * e
 * 解释：
 * 'e' 是那个被添加的字母。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/23 21:09
 */
public class FindTheDifference {
    public char findTheDifference(String s, String t) {
        if (t.length() == 1) return t.charAt(0);
        int len = s.length();
        int[] count = new int[26];
        for (int i = 0; i < len; i++) {
            count[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < len + 1; i++) {
            char ti = t.charAt(i);
            if (count[ti - 'a']-- == 0) {
                return ti;
            }
        }
        throw new RuntimeException();
    }

    public char findTheDifference2(String s, String t) {
        int len = s.length();
        char res = t.charAt(t.length() - 1);
        for (int i = 0; i < len; i++) {
            res ^= s.charAt(i);
            res ^= t.charAt(i);
        }
        return res;
    }
}
