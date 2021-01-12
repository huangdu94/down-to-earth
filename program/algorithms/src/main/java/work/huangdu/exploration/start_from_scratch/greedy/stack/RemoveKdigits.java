package work.huangdu.exploration.start_from_scratch.greedy.stack;

/**
 * 402. 移掉K位数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 注意:
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/11/15 13:13
 */
public class RemoveKdigits {
    /**
     * 1. 从num头开始向后找，如果在前k个元素中有0，那么一定优先删除
     * 开头的元素，保持删除后num开头的元素0越多最后数字位数越小；
     * 2. 处理完了第一步后，我们得到处理第一步后剩下的num和k，此时再进行第1步发现num前k个没有0了
     * 3. 赋值剩下的num数组，进行压栈，如果遇到前一个数比后面数大，必删除前面一个数，否则压栈，直到删掉的数量够k个
     * 4. 若进行了3步骤后，k还是不等于0则删除末尾k个元素
     * 算法时间复杂度 o(n) 空间复杂度o(n)
     *
     * @param num 数组
     * @param k   可以删除的数量
     * @return 删除后最小的数字
     */
    public String removeKdigits2(String num, int k) {
        char[] chars = num.toCharArray();
        int n = num.length(), start = 0;
        for (int i = 0; i < n && i - start <= k; i++) {
            if (chars[i] == '0') {
                k -= (i - start);
                start = i + 1;
            }
        }
        // 说明删完了
        if (n - start <= k) return "0";
        // 说明剩下的不需要再删了
        if (k == 0) return new String(chars, start, n - start);
        int top = 0;
        char[] stack = new char[n - start];
        for (int i = start; i < n; i++) {
            while (k > 0 && top > 0 && stack[top - 1] > chars[i]) {
                top--;
                k--;
            }
            stack[top++] = chars[i];
        }
        return new String(stack, 0, top - k);
    }

    public String removeKdigits(String num, int k) {
        int n = num.length(), start = 0, top = 0;
        char[] chars = num.toCharArray(), stack = new char[n];
        for (char c : chars) {
            while (k > 0 && top != 0 && stack[top - 1] > c) {
                top--;
                k--;
            }
            stack[top++] = c;
        }
        while (start < top && stack[start] == '0') {
            start++;
        }
        if (start >= top - k) return "0";
        return new String(stack, start, top - k - start);
    }
}
