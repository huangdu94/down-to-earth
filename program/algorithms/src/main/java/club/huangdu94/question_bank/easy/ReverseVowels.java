package club.huangdu94.question_bank.easy;

/**
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 * 示例 1：
 * 输入："hello"
 * 输出："holle"
 * 示例 2：
 * 输入："leetcode"
 * 输出："leotcede"
 * 提示：
 * 元音字母不包含字母 "y" 。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/1 10:59
 */
public class ReverseVowels {
    public String reverseVowels(String s) {
        char[] chars = s.toCharArray();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && !isVowel(chars[i])) {
                i++;
            }
            while (i < j && !isVowel(chars[j])) {
                j--;
            }
            if (i < j) {
                char temp = chars[i];
                chars[i] = chars[j];
                chars[j] = temp;
                i++;
                j--;
            }
        }
        return new String(chars);
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'
                || c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U';
    }

    public static void main(String[] args) {
        ReverseVowels reverseVowels = new ReverseVowels();
        String s = "hello";
        System.out.println(reverseVowels.reverseVowels(s));
    }
}
