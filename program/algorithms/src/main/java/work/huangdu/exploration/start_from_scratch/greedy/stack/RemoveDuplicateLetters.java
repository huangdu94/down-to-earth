package work.huangdu.exploration.start_from_scratch.greedy.stack;

/**
 * 316. 去除重复字母
 * 1081. 不同字符的最小子序列
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 注意：该题与 1081 https://leetcode-cn.com/problems/smallest-subsequence-of-distinct-characters 相同
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * 提示：
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/22 19:55
 */
public class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        int top = 0;
        char[] chars = s.toCharArray(), stack = new char[chars.length];
        int[] map = new int[128];
        boolean[] exits = new boolean[128];
        for (char c : chars) {
            map[c]++;
        }
        for (char c : chars) {
            if (exits[c]) {
                map[c]--;
                continue;
            }
            while (top != 0 && stack[top - 1] > c && map[stack[top - 1]] > 1) {
                map[stack[--top]]--;
                exits[stack[top]] = false;
            }
            stack[top++] = c;
            exits[c] = true;
        }
        return new String(stack, 0, top);
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters rdl = new RemoveDuplicateLetters();
        System.out.println(rdl.removeDuplicateLetters("bcabc"));
    }
}
