package work.huangdu.exploration.start_from_scratch.tree.recursion;

/**
 * 87. 扰乱字符串
 * 给定一个字符串 s1，我们可以把它递归地分割成两个非空子字符串，从而将其表示为二叉树。
 * 下图是字符串 s1 = "great" 的一种可能的表示形式。
 * *     great
 * *    /    \
 * *   gr    eat
 * *  / \    /  \
 * * g   r  e   at
 * *            / \
 * *           a   t
 * 在扰乱这个字符串的过程中，我们可以挑选任何一个非叶节点，然后交换它的两个子节点。
 * 例如，如果我们挑选非叶节点 "gr" ，交换它的两个子节点，将会产生扰乱字符串 "rgeat" 。
 * *     rgeat
 * *    /    \
 * *   rg    eat
 * *  / \    /  \
 * * r   g  e   at
 * *            / \
 * *           a   t
 * 我们将 "rgeat” 称作 "great" 的一个扰乱字符串。
 * 同样地，如果我们继续交换节点 "eat" 和 "at" 的子节点，将会产生另一个新的扰乱字符串 "rgtae" 。
 * *     rgtae
 * *    /    \
 * *   rg    tae
 * *  / \    /  \
 * * r   g  ta  e
 * *        / \
 * *       t   a
 * 我们将 "rgtae” 称作 "great" 的一个扰乱字符串。
 * 给出两个长度相等的字符串 s1 和 s2，判断 s2 是否是 s1 的扰乱字符串。
 * 示例 1:
 * 输入: s1 = "great", s2 = "rgeat"
 * 输出: true
 * 示例 2:
 * 输入: s1 = "abcde", s2 = "caebd"
 * 输出: false
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/1 17:13
 */
public class IsScramble {
    public boolean isScramble(String s1, String s2) {
        int n = s1.length();
        if (n == 1 && s1.equals(s2)) return true;
        for (int i = 1; i < n; i++) {
            String left1 = s1.substring(0, i), right1 = s1.substring(i),
                    left2head = s2.substring(0, i), left2tail = s2.substring(n - i);
            if (isAnagram(left1, left2head)) {
                if (isScramble(left1, left2head) && isScramble(right1, s2.substring(i))) {
                    return true;
                }
            } else if (isAnagram(left1, left2tail)) {
                if (isScramble(left1, left2tail) && isScramble(right1, s2.substring(0, n - i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isAnagram(String s, String t) {
        if (s.length() == 1 && s.equals(t)) return true;
        int[] recode = new int[26];
        for (int i = 0; i < s.length(); i++) {
            recode[s.charAt(i) - 'a']++;
            recode[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (recode[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
