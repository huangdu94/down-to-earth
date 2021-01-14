package work.huangdu.exploration.start_from_scratch.linkedlist.rotate_reverse;

import work.huangdu.data_structure.ListNode;

/**
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例:
 * 给定 1->2->3->4, 你应该返回 2->1->4->3.
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/10/13 12:26
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

    public ListNode swapPairs2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;
        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;
            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;
            temp = node1;
        }
        return dummyHead.next;
    }

    public ListNode swapPairs3(ListNode head) {
        ListNode dummy = new ListNode(), cur = dummy;
        dummy.next = head;
        while (cur.next != null && cur.next.next != null) {
            ListNode p1 = cur.next, p2 = p1.next, next = p2.next;
            cur.next = p2;
            p2.next = p1;
            p1.next = next;
            cur = p1;
        }
        return dummy.next;
    }
}
