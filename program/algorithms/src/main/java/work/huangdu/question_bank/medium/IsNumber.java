package work.huangdu.question_bank.medium;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * 例如，字符串"+100"、"5e2"、"-123"、"3.1416"、"-1E-16"、"0123"都表示数值，
 * 但"12e"、"1a3.14"、"1.2.3"、"+-5"及"12e+5.4"都不是。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/2 12:46
 */
public class IsNumber {
    // 有限状态自动机
    /*
      分析：
        1. 数值里可能包含的字符，0-9 + - e E .，出现其它字符肯定不是数值
        2. 第一个位置可以是+或者-，也可以直接是数字(+ -也可以跟着e或E出现，但不可以出现在其它地方了)
        3. e 或 E只可以出现一次
        4. . 只可以出现一次，并且一定出现在至少一个数字后，出现在e或E之前
      尽可能一遍遍历判断上面所有的。
     */
    public boolean isNumber(String s) {
        s = s.trim();
        int len = s.length();
        if (len == 0) return false;
        char first = s.charAt(0);
        if (len == 1) return isNumber(first);
        boolean canAppearDot = true, canAppearE = false, appearE = false, appearNumber = false;
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (isNumber(c)) {
                if (!appearE && !canAppearE) {
                    canAppearE = true;
                }
                if (!appearNumber) {
                    appearNumber = true;
                }
            } else if (c == '+' || c == '-') {
                if (i != 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
                appearNumber = false;
            } else if (c == 'e' || c == 'E') {
                if (canAppearE) {
                    appearE = true;
                    canAppearE = false;
                    canAppearDot = false;
                    appearNumber = false;
                } else {
                    return false;
                }
            } else if (c == '.') {
                if (canAppearDot) {
                    canAppearDot = false;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return appearNumber;
    }

    private boolean isNumber(char c) {
        return c >= '0' && c <= '9';
    }
}
