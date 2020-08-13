package club.huangdu94.exploration.primary_algorithms.linkedlist;

import club.huangdu94.data_structure.ListNode;

/**
 * 反转链表
 * 反转一个单链表。
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 18:06
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

    private void generateResult(ListNode node) {
        if (current == null)
            dummy.next = node;
        else
            current.next = node;
        current = node;
    }
}
