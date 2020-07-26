package club.huangdu94.algorithm_middle.recall;

import java.util.ArrayList;
import java.util.List;

/**
 * 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * abc def ghi jkl mno pqrs tuv wxyz
 * 2   3   4   5   6   7    8   9
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 说明:
 * 尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/9 0:19
 */
public class LetterCombinations {
    private static final char[][] dictionary = {
            {'a', 'b', 'c'},
            {'d', 'e', 'f'},
            {'g', 'h', 'i'},
            {'j', 'k', 'l'},
            {'m', 'n', 'o'},
            {'p', 'q', 'r', 's'},
            {'t', 'u', 'v'},
            {'w', 'x', 'y', 'z'}
    };

    public List<String> letterCombinations(String digits) {
        List<String> resultList = new ArrayList<>();
        if (digits == null || digits.length() == 0)
            return resultList;
        //this.recall(digits.toCharArray(), 0, resultList, "");
        this.recall2(digits.toCharArray(), 0, resultList, new char[digits.length()]);
        return resultList;
    }

    /**
     * 回溯算法实现
     *
     * @param numArr     数字字符数组
     * @param depth      当前深度,深度大于numArr-1停止
     * @param resultList 结果list
     * @param pre        上一个深度生成的字符串
     */
    private void recall(char[] numArr, int depth, List<String> resultList, String pre) {
        if (depth > numArr.length - 1) {
            resultList.add(pre);
            return;
        }
        for (char c : dictionary[numArr[depth++] - '2'])
            this.recall(numArr, depth, resultList, pre + c);
    }

    /**
     * 回溯算法实现
     *
     * @param numArr     数字字符数组
     * @param depth      当前深度,深度大于numArr-1停止
     * @param resultList 结果list
     * @param pre        字符数组
     */
    private void recall2(char[] numArr, int depth, List<String> resultList, char[] pre) {
        if (depth > numArr.length - 1) {
            resultList.add(new String(pre));
            return;
        }
        for (char c : dictionary[numArr[depth] - '2']) {
            pre[depth] = c;
            this.recall2(numArr, depth + 1, resultList, pre);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> result = null;
        result = new LetterCombinations().letterCombinations("23");
        long end = System.currentTimeMillis();
        System.out.println("结果：" + result);
        System.out.println("时间(ms)：" + (end - start));
    }
}
