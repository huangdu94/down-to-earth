package work.huangdu.exploration.start_from_scratch.double_pointer.homonymous_sliding_window;

/**
 * 567. 字符串的排列
 * 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
 * 换句话说，第一个字符串的排列之一是第二个字符串的子串。
 * 示例1:
 * 输入: s1 = "ab" s2 = "eidbaooo"
 * 输出: True
 * 解释: s2 包含 s1 的排列之一 ("ba").
 * 示例2:
 * 输入: s1= "ab" s2 = "eidboaoo"
 * 输出: False
 * 注意：
 * 输入的字符串只包含小写字母
 * 两个字符串的长度都在 [1, 10,000] 之间
 *
 * @author duhuang@iflytek.com
 * @version 2020/12/16 10:48
 */
public class CheckInclusion {
    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length(), left = 0, right = 0;
        int[] counts1 = new int[26], counts2 = new int[26];
        for (int i = 0; i < n1; i++) {
            counts1[s1.charAt(i) - 'a']++;
        }
        while (right < n2) {
            int c = s2.charAt(right) - 'a';
            counts2[c]++;
            while (counts2[c] > counts1[c]) {
                counts2[s2.charAt(left++) - 'a']--;
            }
            if (right - left + 1 == n1) {
                return true;
            }
            right++;
        }
        return false;
    }
}