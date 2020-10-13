package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/13 12:26
 */
public class SwapPairs {
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode res = head.next, p1 = head, p2, n1, n2;
        while (p1 != null && p1.next != null) {
            p2 = p1.next;
            n1 = p2.next;
            if (n1 == null || n1.next == null) {
                p2.next = p1;
                p1.next = n1;
            } else {
                n2 = n1.next;
                p2.next = p1;
                p1.next = n2;
            }
            p1 = n1;
        }
        return res;
    }
}
