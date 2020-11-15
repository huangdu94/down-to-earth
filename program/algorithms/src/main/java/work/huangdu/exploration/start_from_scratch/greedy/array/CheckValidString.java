package work.huangdu.exploration.start_from_scratch.greedy.array;

/**
 * 678. 有效的括号字符串
 * 给定一个只包含三种字符的字符串：（ ，） 和 *，写一个函数来检验这个字符串是否为有效字符串。有效字符串具有如下规则：
 * 任何左括号 ( 必须有相应的右括号 )。
 * 任何右括号 ) 必须有相应的左括号 ( 。
 * 左括号 ( 必须在对应的右括号之前 )。
 * * 可以被视为单个右括号 ) ，或单个左括号 ( ，或一个空字符串。
 * 一个空字符串也被视为有效字符串。
 * 示例 1:
 * 输入: "()"
 * 输出: True
 * 示例 2:
 * 输入: "(*)"
 * 输出: True
 * 示例 3:
 * 输入: "(*))"
 * 输出: True
 * 注意:
 * 字符串大小将在 [1，100] 范围内。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/15 16:58
 */
public class CheckValidString {
    // 算法思路，从左到右来一遍，再从右到左来一遍
    public boolean checkValidString(String s) {
        char[] strings = s.toCharArray();
        int n = s.length(), star = 0, remain = 0;
        for (char c : strings) {
            if (c == '(') {
                remain++;
            } else if (c == ')') {
                if (remain > 0) {
                    remain--;
                } else if (star > 0) {
                    star--;
                } else {
                    return false;
                }
            } else {
                star++;
            }
        }
        star = 0;
        remain = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (strings[i] == ')') {
                remain++;
            } else if (strings[i] == '(') {
                if (remain > 0) {
                    remain--;
                } else if (star > 0) {
                    star--;
                } else {
                    return false;
                }
            } else {
                star++;
            }
        }
        return true;
    }
}
