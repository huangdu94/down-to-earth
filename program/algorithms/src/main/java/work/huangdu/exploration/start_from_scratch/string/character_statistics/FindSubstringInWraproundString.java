package work.huangdu.exploration.start_from_scratch.string.character_statistics;

/**
 * 467. 环绕字符串中唯一的子字符串
 * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
 * 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。
 * 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
 * 示例 1:
 * 输入: "a"
 * 输出: 1
 * 解释: 字符串 S 中只有一个"a"子字符。
 * 示例 2:
 * 输入: "cac"
 * 输出: 2
 * 解释: 字符串 S 中的字符串“cac”只有两个子串“a”、“c”。.
 * 示例 3:
 * 输入: "zab"
 * 输出: 6
 * 解释: 在字符串 S 中有六个子串“z”、“a”、“b”、“za”、“ab”、“zab”。.
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/25 10:50
 */
public class FindSubstringInWraproundString {
    // len表示以当前字符结尾的最长递增子串的长度，map数组里存的是当前状态以a-z字母结尾的最长子串长度。每访问一个字符，则首先更新len值，连续的则len+1， 否则len等于1。然后将len值与map里对应的值比较，大于的话说明有新的以当前字母结尾的子串，更新sum的值。
    public int findSubstringInWraproundString(String p) {
        if (p.length() == 0) return 0;
        char[] chars = p.toCharArray();
        int len = 0, sum = 0;
        int[] counts = new int[128];
        for (int i = 0, n = p.length(); i < n; i++) {
            if (i == 0 || chars[i - 1] == 'z' ? chars[i] == 'a' : chars[i] == chars[i - 1] + 1) {
                len++;
            } else {
                len = 1;
            }
            if (len > counts[chars[i]]) {
                sum += (len - counts[chars[i]]);
                counts[chars[i]] = len;
            }
        }
        return sum;
    }

    public int findSubstringInWraproundString2(String p) {
        if (p.length() == 0) return 0;
        char[] chars = p.toCharArray();
        char start = chars[0];
        int len = 1;
        int[] counts = new int[128], res = new int[128];
        for (int i = 1, n = p.length(); i < n; i++) {
            if (chars[i - 1] == 'z' ? chars[i] != 'a' : chars[i] != chars[i - 1] + 1) {
                if (len > counts[start]) {
                    counts[start] = len;
                }
                start = chars[i];
                len = 0;
            }
            len++;
        }
        if (len > counts[start]) {
            counts[start] = len;
        }
        for (char i = 'a'; i <= 'z'; i++) {
            if (counts[i] != 0) {
                char cc = i;
                int length = counts[i];
                while (length > 0) {
                    if (length > res[cc]) {
                        res[cc] = length;
                    }
                    cc = cc == 'z' ? 'a' : (char) (cc + 1);
                    length--;
                    if (cc == i) break;
                }
            }
        }
        int sum = 0;
        for (char i = 'a'; i <= 'z'; i++) {
            sum += res[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        FindSubstringInWraproundString find = new FindSubstringInWraproundString();
        String p = "zab";
        System.out.println(find.findSubstringInWraproundString(p));
    }
}
