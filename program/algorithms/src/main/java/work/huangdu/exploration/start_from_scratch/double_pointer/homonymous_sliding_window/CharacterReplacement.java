package work.huangdu.exploration.start_from_scratch.double_pointer.homonymous_sliding_window;

/**
 * 424. 替换后的最长重复字符
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * 注意:
 * 字符串长度 和 k 不会超过 10^4。
 * 示例 1:
 * 输入:
 * s = "ABAB", k = 2
 * 输出:
 * 4
 * 解释:
 * 用两个'A'替换为两个'B',反之亦然。
 * 示例 2:
 * 输入:
 * s = "AABABBA", k = 1
 * 输出:
 * 4
 * 解释:
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/12/18 12:58
 */
public class CharacterReplacement {
    public int characterReplacement(String s, int k) {
        int n = s.length();
        if (n <= k) return n;
        int max = 0, result = k + 1, start = 0;
        int[] counts = new int[26];
        char[] chars = s.toCharArray();
        for (int end = 0; end < n; end++) {
            max = Math.max(max, ++counts[chars[end] - 'A']);
            while (end - start + 1 > k + max) {
                int count = counts[chars[start++] - 'A']--;
                if (count == max) {
                    max = getMax(counts);
                }
            }
            result = Math.max(result, end - start + 1);
        }
        return result;
    }

    private int getMax(int[] counts) {
        int max = 0;
        for (int count : counts) {
            if (count > max) {
                max = count;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        CharacterReplacement cp = new CharacterReplacement();
        System.out.println(cp.characterReplacement("ABAB", 2));
    }
}
