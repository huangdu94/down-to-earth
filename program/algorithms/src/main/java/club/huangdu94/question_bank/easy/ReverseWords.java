package club.huangdu94.question_bank.easy;

/**
 * 557. 反转字符串中的单词 III
 * 给定一个字符串，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 * 示例：
 * 输入："Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 * 提示：
 * 在字符串中，每个单词由单个空格分隔，并且字符串中不会有任何额外的空格。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/30 13:34
 */
public class ReverseWords {
    public String reverseWords2(String s) {
        String[] words = s.split(" ");
        StringBuilder reverseWords = new StringBuilder(s.length() + 1);
        for (String word : words) {
            reverseWords.append(" ").append(new StringBuffer(word).reverse());
        }
        return reverseWords.substring(1);
    }

    public String reverseWords(String s) {
        int len = s.length(), i = 0, index = 0, word_start, word_end;
        char[] chars = new char[len], original = s.toCharArray();
        while (i < len) {
            word_start = i;
            while (i < len && original[i] != ' ') {
                i++;
            }
            word_end = i;
            while (i > word_start) {
                chars[index++] = original[--i];
            }
            if (word_end != len) {
                chars[index++] = ' ';
            }
            i = word_end + 1;
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        ReverseWords reverseWords = new ReverseWords();
        String s = "Let's take LeetCode contest";
        System.out.println(reverseWords.reverseWords(s));
    }
}
