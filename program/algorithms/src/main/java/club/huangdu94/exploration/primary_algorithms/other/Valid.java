package club.huangdu94.exploration.primary_algorithms.other;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * 示例 1:
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * 输入: "{[]}"
 * 输出: true
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/1 22:19
 */
public class Valid {
    /**
     * 自制栈
     */
    private class Stack {
        Node head;
        Node current;

        Stack() {
            head = new Node();
            current = head;
        }

        private class Node {
            char symbol;
            Node next;

            public Node() {
            }

            public Node(char symbol, Node next) {
                this.symbol = symbol;
                this.next = next;
            }
        }

        void push(char symbol) {
            current = new Node(symbol, current);
        }

        char pop() {
            if (current == head)
                throw new RuntimeException();
            char c = current.symbol;
            current = current.next;
            return c;
        }

        char peek() {
            if (current == head)
                throw new RuntimeException();
            return current.symbol;
        }

        boolean isEmpty() {
            return current == head;
        }
    }

    public boolean isValid(String s) {
        int len = s.length();
        if (len == 0)
            return true;
        if (len == 1)
            return false;
        // 自制栈
        Stack stack = new Stack();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == ')') {
                if (stack.isEmpty())
                    return false;
                if (stack.peek() != '(')
                    return false;
                else
                    stack.pop();
            } else if (c == '}') {
                if (stack.isEmpty())
                    return false;
                if (stack.peek() != '{')
                    return false;
                else
                    stack.pop();
            } else if (c == ']') {
                if (stack.isEmpty())
                    return false;
                if (stack.peek() != '[')
                    return false;
                else
                    stack.pop();
            } else {
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new Valid().isValid("[])"));
    }
}
