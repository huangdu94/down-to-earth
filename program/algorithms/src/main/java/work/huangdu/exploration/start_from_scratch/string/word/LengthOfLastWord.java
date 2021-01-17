package work.huangdu.exploration.start_from_scratch.string.word;

/**
 * 58. 最后一个单词的长度
 * 给定一个仅包含大小写字母和空格 ' ' 的字符串 s，返回其最后一个单词的长度。如果字符串从左向右滚动显示，那么最后一个单词就是最后出现的单词。
 * 如果不存在最后一个单词，请返回 0 。
 * 说明：一个单词是指仅由字母组成、不包含任何空格字符的 最大子字符串。
 * 示例:
 * 输入: "Hello World"
 * 输出: 5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/21 11:28
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        int len = s.length(), i = len - 1, res = 0;
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }
        while (i >= 0 && s.charAt(i) != ' ') {
            res++;
            i--;
        }
        return res;
    }

    public int lengthOfLastWord2(String s) {
        int index = s.length() - 1;
        // 跳过末尾空格
        while (index >= 0 && s.charAt(index) == ' ') index--;
        int len = 0;
        for (int i = index; i >= 0 && s.charAt(i) != ' '; i--) len++;
        return len;
    }
}
