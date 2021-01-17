package work.huangdu.exploration.intermediate_algorithms.other;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 150. 逆波兰表达式求值
 * 根据 逆波兰表示法，求表达式的值。
 * 有效的运算符包括 +, -, *, / 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
 * 说明：
 * 整数除法只保留整数部分。
 * 给定逆波兰表达式总是有效的。换句话说，表达式总会得出有效数值且不存在除数为 0 的情况。
 * 示例 1：
 * 输入: ["2", "1", "+", "3", "*"]
 * 输出: 9
 * 解释: 该算式转化为常见的中缀算术表达式为：((2 + 1) * 3) = 9
 * 示例 2：
 * 输入: ["4", "13", "5", "/", "+"]
 * 输出: 6
 * 解释: 该算式转化为常见的中缀算术表达式为：(4 + (13 / 5)) = 6
 * 示例 3：
 * 输入: ["10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"]
 * 输出: 22
 * 解释:
 * 该算式转化为常见的中缀算术表达式为：
 * ((10 * (6 / ((9 + 3) * -11))) + 17) + 5
 * = ((10 * (6 / (12 * -11))) + 17) + 5
 * = ((10 * (6 / -132)) + 17) + 5
 * = ((10 * 0) + 17) + 5
 * = (0 + 17) + 5
 * = 17 + 5
 * = 22
 * 逆波兰表达式：
 * 逆波兰表达式是一种后缀表达式，所谓后缀就是指算符写在后面。
 * 平常使用的算式则是一种中缀表达式，如 ( 1 + 2 ) * ( 3 + 4 ) 。
 * 该算式的逆波兰表达式写法为 ( ( 1 2 + ) ( 3 4 + ) * ) 。
 * 逆波兰表达式主要有以下两个优点：
 * 去掉括号后表达式无歧义，上式即便写成 1 2 + 3 4 + * 也可以依据次序计算出正确结果。
 * 适合用栈操作运算：遇到数字则入栈；遇到算符则取出栈顶两个数字进行计算，并将结果压入栈中。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/22 0:23
 */
public class EvalRPN {
    //1.Stack实现
    //2.使用数组实现栈
    //3.直接把String[]从后往前递归，当String[]本身为已经全都压好的栈

    public int evalRPN(String[] tokens) {
        // 以下程序不对溢出做任何处理
        Stack<Integer> numberStack = new Stack<>();
        for (String token : tokens) {
            if (this.isNumber(token))
                numberStack.push(Integer.parseInt(token));
            else
                numberStack.push(this.operation(numberStack.pop(), numberStack.pop(), token));
        }
        return numberStack.pop();
    }

    /**
     * 如果不是+、-、*或/
     * 则就是数字
     *
     * @param token 字符串
     * @return 是否是数字
     */
    private boolean isNumber(String token) {
        return !token.equals("+") && !token.equals("-") && !token.equals("*") && !token.equals("/");
    }


    /**
     * a 操作 b的结果
     *
     * @param a       操作数a
     * @param b       操作数b
     * @param operate + - * /操作
     * @return 结果
     */
    private int operation(int b, int a, String operate) {
        switch (operate) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "*":
                return a * b;
            case "/":
                return a / b;
            default:
                throw new RuntimeException();
        }
    }

    public int evalRPN2(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                    stack.push(stack.pop() + stack.pop());
                    break;
                case "-":
                    stack.push(-stack.pop() + stack.pop());
                    break;
                case "*":
                    stack.push(stack.pop() * stack.pop());
                    break;
                case "/":
                    int temp = stack.pop();
                    stack.push(stack.pop() / temp);
                    break;
                default:
                    stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
