package work.huangdu.exploration.start_from_scratch.linkedlist.rotate_reverse;

import work.huangdu.data_structure.ListNode;

/**
 * 92. 反转链表 II
 * 反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。
 * 说明:
 * 1 ≤ m ≤ n ≤ 链表长度。
 * 示例:
 * 输入: 1->2->3->4->5->NULL, m = 2, n = 4
 * 输出: 1->4->3->2->5->NULL
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/27 14:52
 */
public class ReverseBetween {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode headTail = dummy;
        for (int i = 1; i < m; i++) {
            headTail = headTail.next;
        }
        ListNode begin = headTail.next, end = begin;
        for (int i = m; i < n; i++) {
            end = end.next;
        }
        ListNode tailHead = end.next;
        // 反转后原来的头变成尾，原来的尾变成头
        reverseList(begin, end.next);
        headTail.next = end;
        begin.next = tailHead;
        return dummy.next;
    }

    private void reverseList(ListNode begin, ListNode end) {
        ListNode pre = null, cur = begin;
        while (cur != end) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }
}
