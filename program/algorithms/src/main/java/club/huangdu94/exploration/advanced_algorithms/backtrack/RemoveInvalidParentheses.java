package club.huangdu94.exploration.advanced_algorithms.backtrack;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 删除无效的括号
 * 删除最小数量的无效括号，使得输入的字符串有效，返回所有可能的结果。
 * 说明: 输入可能包含了除(和)以外的字符。
 * 示例 1:
 * 输入: "()())()"
 * 输出: ["()()()", "(())()"]
 * 示例 2:
 * 输入: "(a)())()"
 * 输出: ["(a)()()", "(a())()"]
 * 示例 3:
 * 输入: ")("
 * 输出: [""]
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/17 11:08
 */
public class RemoveInvalidParentheses {
    /*
     1 HashSet存结果，返回时再转换为List，用于结果去重
     2 处理一遍字符串，记录删了几个左括号，删了几个右括号
       2.1 从左到右遍历一遍出现的不正确的括号删掉并计数，到末尾如果不满足要求则继续2.2
       2.2 从右到左遍历一遍出现的不正确的括号删掉并计数
     3 回溯部分，对于每一个括号有两种可能性（保留、删除）
       3.1 注意如果要删除的话不要超过预处理记录的删除个数
       3.2 如果过程中删除机会用完了，并且碰到了错误的括号则此次递归失败不保存结果
       3.3 字符串通过则保存结果
    */
    // 优化： 预处理只需要从左至右遍历一遍即可
    private Set<String> set;
    private StringBuilder sb;
    private int len;
    //private int left;
    //private int right;

    public List<String> removeInvalidParentheses(String s) {
        set = new HashSet<>();
        sb = new StringBuilder();
        len = s.length();
        //left = 0;
        //right = 0;
        //preprocess(s);
        int left = 0, right = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ')' && left == 0) {
                right++;
            } else {
                if (c == '(') left++;
                else if (c == ')' && left > 0) left--;
            }
        }
        backtrack(s, 0, 0, left, right);
        return new ArrayList<>(set);
    }

    /**
     * 预处理
     */
    /*private void preprocess(String s) {
        StringBuilder sbL = new StringBuilder(s.length());
        int l = 0;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ')' && l == 0) {
                right++;
            } else {
                if (c == '(') l++;
                else if (c == ')' && l > 0) l--;
                sbL.append(c);
            }
        }
        if (l == 0) return;
        int r = 0;
        for (int i = sbL.length() - 1; i >= 0; i--) {
            char c = sbL.charAt(i);
            if (c == '(' && r == 0) {
                left++;
            } else {
                if (c == ')') r++;
                else if (c == '(' && r > 0) r--;
            }
        }
    }*/

    /**
     * 回溯
     */
    private void backtrack(String s, int index, int l, int left, int right) {
        if (index == len || len - index < left + right) {
            if (l == 0) set.add(sb.toString());
            return;
        }
        char c = s.charAt(index);
        if (c == '(') {
            if (left > 0) backtrack(s, index + 1, l, left - 1, right);
            sb.append(c);
            backtrack(s, index + 1, l + 1, left, right);
            sb.deleteCharAt(sb.length() - 1);
        } else if (c == ')') {
            if (right > 0) {
                backtrack(s, index + 1, l, left, right - 1);
            }
            if (l > 0) {
                sb.append(c);
                backtrack(s, index + 1, l - 1, left, right);
                sb.deleteCharAt(sb.length() - 1);
            }
        } else {
            sb.append(c);
            backtrack(s, index + 1, l, left, right);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public static void main(String[] args) {
        RemoveInvalidParentheses removeInvalidParentheses = new RemoveInvalidParentheses();
        String s = "()())()";
        List<String> res = removeInvalidParentheses.removeInvalidParentheses(s);
        System.out.println(res);
    }
}
