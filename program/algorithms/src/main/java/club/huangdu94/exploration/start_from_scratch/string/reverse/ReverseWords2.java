package club.huangdu94.exploration.start_from_scratch.string.reverse;

/**
 * 151. 翻转字符串里的单词
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * 示例 1：
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 说明：
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 进阶：
 * 请选用 C 语言的用户尝试使用 O(1) 额外空间复杂度的原地解法。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/21 14:02
 */
public class ReverseWords2 {
    public String reverseWords(String s) {
        int len = s.length(), index = len - 1;
        char[] chars = s.toCharArray();
        StringBuilder res = new StringBuilder(len);
        while (index >= 0) {
            while (index >= 0 && chars[index] == ' ') {
                index--;
            }
            if (index >= 0 && res.length() != 0) {
                res.append(' ');
            }
            int j = index;
            while (index >= 0 && chars[index] != ' ') {
                index--;
            }
            int i = index + 1;
            while (i <= j) {
                res.append(chars[i++]);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        ReverseWords2 reverseWords2 = new ReverseWords2();
        String s = "the sky is blue";
        System.out.println(reverseWords2.reverseWords(s));
    }
}
