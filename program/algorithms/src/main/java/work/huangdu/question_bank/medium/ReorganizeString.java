package work.huangdu.question_bank.medium;

/**
 * 767. 重构字符串
 * 给定一个字符串S，检查是否能重新排布其中的字母，使得两相邻的字符不同。
 * 若可行，输出任意可行的结果。若不可行，返回空字符串。
 * 示例 1:
 * 输入: S = "aab"
 * 输出: "aba"
 * 示例 2:
 * 输入: S = "aaab"
 * 输出: ""
 * 注意:
 * S 只包含小写字母并且长度在[1, 500]区间内。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/11/30 0:12
 */
public class ReorganizeString {
    public String reorganizeString(String s) {
        if (s.length() < 2) return s;
        int n = s.length(), max = 0;
        char[] chars = s.toCharArray();
        int[] counts = new int[128];
        for (char c : chars) {
            if (max < ++counts[c]) {
                max = counts[c];
            }
        }
        if (max > (n + 1) / 2) return "";
        int even = 0, odd = 1, half = n / 2;
        for (char i = 'a'; i <= 'z'; i++) {
            if (counts[i] != 0) {
                if (counts[i] <= half) {
                    while (counts[i] > 0 && odd < n) {
                        chars[odd] = i;
                        odd += 2;
                        counts[i]--;
                    }
                }
                while (counts[i] > 0) {
                    chars[even] = i;
                    even += 2;
                    counts[i]--;
                }
            }
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        ReorganizeString rs = new ReorganizeString();
        System.out.println(rs.reorganizeString("bfrbs"));
    }
}
