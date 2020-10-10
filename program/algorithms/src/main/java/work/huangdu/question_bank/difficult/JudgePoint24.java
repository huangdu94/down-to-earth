package work.huangdu.question_bank.difficult;

/**
 * 679. 24 点游戏
 * 你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 *，/，+，-，(，) 的运算得到 24。
 * 示例 1:
 * 输入: [4, 1, 8, 7]
 * 输出: True
 * 解释: (8-4) * (7-1) = 24
 * 示例 2:
 * 输入: [1, 2, 1, 2]
 * 输出: False
 * 注意:
 * 除法运算符 / 表示实数除法，而不是整数除法。例如 4 / (1 - 2/3) = 12 。
 * 每个运算符对两个数进行运算。特别是我们不能用 - 作为一元运算符。例如，[1, 1, 1, 1] 作为输入时，表达式 -1 - 1 - 1 - 1 是不允许的。
 * 你不能将数字连接在一起。例如，输入为 [1, 2, 1, 2] 时，不能写成 12 + 12 。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/22 12:23
 */
public class JudgePoint24 {
    // 采用回溯算法会更优雅，用list装四个数，每次拿两个出来计算，计算结果放回去
    // 直到list中只有个数的时候比较是否为24
    // 有两点需要注意的：1.判断double是否相等不能用==，要用范围 2.除数等于0的情况可以直接规避
    private static final double EPSILON = 1e-6;
    private static final int NUM_COUNT = 4;
    private static final char[] OPERATORS = {'+', '-', '*', '/'};

    public boolean judgePoint24(int[] nums) {
        // 1. int 转 double
        double[] doubles = new double[NUM_COUNT];
        for (int i = 0; i < NUM_COUNT; i++) {
            doubles[i] = nums[i];
        }
        /*
        2. 分析所有的计算组合情况
          2.1 对于符号有3个位置，每个位置4种可能，共64种组合
          2.2 对于计算顺序：
            (1 2 3) (1 3 2) (2 1 3) (2 3 1) (3 1 2) (3 2 1) ，共有6种组合
          2.3 优化：
            a. 其中(1 3 2)和(3 1 2)等价，所以共有5种组合
            b. 对于符号全都为+、-或者符号全都为*、/不管什么顺序计算结果都一样，这些情况有16种可能
          2.4 还要考虑数字的位置，共有24种组合
        */
        for (int i1 = 0; i1 < NUM_COUNT; i1++) {
            for (int i2 = 0; i2 < NUM_COUNT; i2++) {
                if (i2 == i1) continue;
                for (int i3 = 0; i3 < NUM_COUNT; i3++) {
                    if (i3 == i1 || i3 == i2) continue;
                    for (int i4 = 0; i4 < NUM_COUNT; i4++) {
                        if (i4 == i1 || i4 == i2 || i4 == i3) continue;
                        if (forceCompute(doubles[i1], doubles[i2], doubles[i3], doubles[i4]))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean forceCompute(double d1, double d2, double d3, double d4) {
        // 暴力解，只考虑优化a，共有320种可能
        // 1 2 3
        double res1, res2, res3;
        for (char opt1 : OPERATORS) {
            res1 = compute(d1, d2, opt1);
            for (char opt2 : OPERATORS) {
                res2 = compute(res1, d3, opt2);
                for (char opt3 : OPERATORS) {
                    res3 = compute(res2, d4, opt3);
                    if (equal24(res3)) {
                        return true;
                    }
                }
            }
        }
        // 1 3 2
        for (char opt1 : OPERATORS) {
            res1 = compute(d1, d2, opt1);
            for (char opt3 : OPERATORS) {
                res3 = compute(d3, d4, opt3);
                for (char opt2 : OPERATORS) {
                    res2 = compute(res1, res3, opt2);
                    if (equal24(res2)) {
                        return true;
                    }
                }
            }
        }
        // 2 1 3
        for (char opt2 : OPERATORS) {
            res2 = compute(d2, d3, opt2);
            for (char opt1 : OPERATORS) {
                res1 = compute(d1, res2, opt1);
                for (char opt3 : OPERATORS) {
                    res3 = compute(res1, d4, opt3);
                    if (equal24(res3)) {
                        return true;
                    }
                }
            }
        }
        // 2 3 1
        for (char opt2 : OPERATORS) {
            res2 = compute(d2, d3, opt2);
            for (char opt3 : OPERATORS) {
                res3 = compute(res2, d4, opt3);
                for (char opt1 : OPERATORS) {
                    res1 = compute(d1, res3, opt1);
                    if (equal24(res1)) {
                        return true;
                    }
                }
            }
        }
        // 3 2 1
        for (char opt3 : OPERATORS) {
            res3 = compute(d3, d4, opt3);
            for (char opt2 : OPERATORS) {
                res2 = compute(d2, res3, opt2);
                for (char opt1 : OPERATORS) {
                    res1 = compute(d1, res2, opt1);
                    if (equal24(res1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private double compute(double n1, double n2, char opt) {
        switch (opt) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
            case '/':
                return n1 / n2;
        }
        return -1;
    }

    private boolean equal24(double n) {
        return Math.abs(n - 24) < EPSILON;
    }

    public static void main(String[] args) {
    }
}
