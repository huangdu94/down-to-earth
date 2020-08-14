package club.huangdu94.exploration.intermediate_algorithms.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * 示例：
 * 输入：n = 3
 * 输出：[
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/9 0:20
 */
public class GenerateParenthesis {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        if (n < 1)
            return result;
        //this.backtrack(n, 0, 0, result, "");
        this.backtrack2(n, 0, 0, result, new char[n * 2], 0);
        return result;
    }

    /**
     * 回溯算法实现
     *
     * @param n          目标括号对数
     * @param count      当前已经完成的对数
     * @param i          已经出现几个没有对应右括号的左括号
     * @param resultList 结果list
     * @param pre        上一个深度生成的字符串
     */
    private void backtrack(int n, int count, int i, List<String> resultList, String pre) {
        if (count == n) {
            resultList.add(pre);
            return;
        }
        if (i == 0) {
            this.backtrack(n, count, i + 1, resultList, pre + '(');
        } else if (i == n - count) {
            this.backtrack(n, count + 1, i - 1, resultList, pre + ')');
        } else {
            this.backtrack(n, count, i + 1, resultList, pre + '(');
            this.backtrack(n, count + 1, i - 1, resultList, pre + ')');
        }
    }

    /**
     * 回溯算法实现
     *
     * @param n          目标括号对数
     * @param count      当前已经完成的对数
     * @param i          已经出现几个没有对应右括号的左括号
     * @param resultList 结果list
     * @param pre        字符数组
     * @param index      记录index
     */
    private void backtrack2(int n, int count, int i, List<String> resultList, char[] pre, int index) {
        if (count == n) {
            resultList.add(new String(pre));
            return;
        }
        if (i == 0) {
            pre[index] = '(';
            this.backtrack2(n, count, i + 1, resultList, pre, index + 1);
        } else if (i == n - count) {
            pre[index] = ')';
            this.backtrack2(n, count + 1, i - 1, resultList, pre, index + 1);
        } else {
            pre[index] = '(';
            this.backtrack2(n, count, i + 1, resultList, pre, index + 1);
            pre[index] = ')';
            this.backtrack2(n, count + 1, i - 1, resultList, pre, index + 1);
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<String> result = null;
        result = new GenerateParenthesis().generateParenthesis(3);
        long end = System.currentTimeMillis();
        System.out.println("结果：" + result);
        System.out.println("时间(ms)：" + (end - start));
    }
}
