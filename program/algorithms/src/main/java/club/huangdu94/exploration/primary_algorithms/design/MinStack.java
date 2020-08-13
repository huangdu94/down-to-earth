package club.huangdu94.exploration.primary_algorithms.design;

import java.util.NoSuchElementException;

/**
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * push(x) —— 将元素 x 推入栈中。
 * pop() —— 删除栈顶的元素。
 * top() —— 获取栈顶元素。
 * getMin() —— 检索栈中的最小元素。
 * 示例:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 * 提示：
 * pop、top 和 getMin 操作总是在 非空栈 上调用。
 *
 * @author duhuang@iflytek.com
 * @version 2020/6/29 15:05
 */
public class MinStack {
    /**
     * 自定义数据结构
     */
    private class Node {
        int val;
        Node next;
        int min;

        Node() {
            min = Integer.MAX_VALUE;
        }

        Node(int val, int min, Node next) {
            this.val = val;
            this.min = min;
            this.next = next;
        }
    }

    // 栈头
    private final Node head = new Node();
    // 当前元素
    private Node current;

    /**
     * initialize your data structure here.
     */
    public MinStack() {
        current = head;
    }

    public void push(int x) {
        current = new Node(x, Math.min(current.min, x), current);
    }

    public void pop() {
        if (current == head)
            throw new NoSuchElementException();
        current = current.next;
    }

    public int top() {
        if (current == head)
            throw new NoSuchElementException();
        return current.val;
    }

    public int getMin() {
        if (current == head)
            throw new NoSuchElementException();
        /*Node p = current;
        int min = p.val;
        while (p.next != null) {
            if (min > p.val)
                min = p.val;
            p = p.next;
        }
        return min;*/
        return current.min;
    }

    public static void main(String[] args) {
        MinStack stack = new MinStack();
        stack.push(2);
        stack.push(0);
        stack.push(3);
        stack.push(0);
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
        stack.pop();
        System.out.println(stack.getMin());
    }
}
/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */