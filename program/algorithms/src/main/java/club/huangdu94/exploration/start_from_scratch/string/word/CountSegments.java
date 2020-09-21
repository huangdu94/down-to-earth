package club.huangdu94.exploration.start_from_scratch.string.word;

/**
 * 434. 字符串中的单词数
 * 统计字符串中的单词个数，这里的单词指的是连续的不是空格的字符。
 * 请注意，你可以假定字符串里不包括任何不可打印的字符。
 * 示例:
 * 输入: "Hello, my name is John"
 * 输出: 5
 * 解释: 这里的单词是指连续的不是空格的字符，所以 "Hello," 算作 1 个单词。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/21 13:18
 */
public class CountSegments {
    public int countSegments(String s) {
        int len = s.length(), i = 0, count = 0;
        while (i < len) {
            while (i < len && s.charAt(i) == ' ') {
                i++;
            }
            if (i < len) {
                count++;
            }
            while (i < len && s.charAt(i) != ' ') {
                i++;
            }
        }
        return count;
    }
}
