package work.huangdu.data_structure;

import java.util.EmptyStackException;

/**
 * The Stack class represents a last-in-first-out (LIFO) stack of objects.
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/14 0:10
 */
public class Stack<T> {
    private final Node head = new Node();
    private Node current = head;

    /**
     * Creates an empty Stack.
     */
    public Stack() {
    }

    /**
     * Pushes an element onto the top of this stack.
     */
    public void push(T element) {
        current = new Node(element, current);
    }

    /**
     * Removes the object at the top of this stack and returns that
     * object as the value of this function.
     */
    public T pop() {
        if (current == head) throw new EmptyStackException();
        T element = current.element;
        current = current.next;
        return element;
    }

    /**
     * Looks at the object at the top of this stack without removing it
     * from the stack.
     */
    public T peek() {
        if (current == head) throw new EmptyStackException();
        return current.element;
    }

    /**
     * Tests if this stack is empty.
     */
    public boolean empty() {
        return current == head;
    }

    /**
     * 链表栈节点
     */
    private class Node {
        T element;
        Node next;

        Node() {
        }

        Node(T element, Node next) {
            this.element = element;
            this.next = next;
        }
    }
}
