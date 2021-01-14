package work.huangdu.exploration.primary_algorithms.linkedlist;

import work.huangdu.data_structure.ListNode;

/**
 * 206. 反转链表
 * 剑指 Offer 24. 反转链表
 * 反转一个单链表。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/26 18:06
 */
public class ReverseList {
    public ListNode reverseList(ListNode head) {
        ListNode pointer = head;
        ListNode current = null;
        ListNode next = null;
        while (pointer != null) {
            current = new ListNode(pointer.val);
            current.next = next;
            next = current;
            pointer = pointer.next;
        }
        return current;
    }

    private static ListNode dummy;
    private static ListNode current;

    public ListNode reverseList2(ListNode head) {
        dummy = new ListNode(0);
        current = null;
        reverse(head);
        if (current != null)
            current.next = null;
        return dummy.next;
    }

    private void reverse(ListNode current) {
        if (current == null)
            return;
        reverse(current.next);
        generateResult(current);
    }

    public ListNode reverseList3(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public ListNode reverseList4(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode res = reverseList4(head);
        head.next.next = head;
        head.next = null;
        return res;
    }

    private void generateResult(ListNode node) {
        if (current == null)
            dummy.next = node;
        else
            current.next = node;
        current = node;
    }

    private ListNode left;
    private boolean stop;

    public ListNode reverseList5(ListNode head) {
        if (head == null || head.next == null) return head;
        left = head;
        stop = false;
        recursion(head.next);
        return head;
    }

    private void recursion(ListNode right) {
        if (right == null) return;
        recursion(right.next);
        if (left == right || right.next == left) {
            stop = true;
        }
        if (!stop) {
            int temp = left.val;
            left.val = right.val;
            right.val = temp;
            left = left.next;
        }
    }
}
