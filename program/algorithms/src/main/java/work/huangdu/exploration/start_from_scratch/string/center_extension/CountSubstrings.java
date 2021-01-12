package work.huangdu.exploration.start_from_scratch.string.center_extension;

/**
 * 647. 回文子串
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 * 示例 1：
 * 输入："abc"
 * 输出：3
 * 解释：三个回文子串: "a", "b", "c"
 * 示例 2：
 * 输入："aaa"
 * 输出：6
 * 解释：6个回文子串: "a", "a", "a", "aa", "aa", "aaa"
 * 提示：
 * 输入的字符串长度不会超过 1000 。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/19 10:12
 */
public class CountSubstrings {
    // 学习马拉车算法
    /*
        对于每一个字符，首先视其为中心来计算回文字符串数量，然后以其和它右边的字符为中心计算回文字符串数量
        当遇到非回文或者遇到边界的时候停止
     */
    public int countSubstrings(String s) {
        if (s == null || s.length() == 0) return 0;
        int len = s.length(), count = len, left, right;
        for (int i = 0; i < len; i++) {
            left = i - 1;
            right = i + 1;
            while (left >= 0 && right < len && s.charAt(left--) == s.charAt(right++)) count++;
            left = i;
            right = i + 1;
            while (left >= 0 && right < len && s.charAt(left--) == s.charAt(right++)) count++;
        }
        return count;
    }
}
