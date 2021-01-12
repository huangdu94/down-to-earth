package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.ListNode;

/**
 * 143. 重排链表
 * 给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
 * 将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * 示例 1:
 * 给定链表 1->2->3->4, 重新排列为 1->4->2->3.
 * 示例 2:
 * 给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/10/20 12:26
 */
public class ReorderList {
    public void reorderList(ListNode head) {
        // 0. 空链表，单节点链表，双节点链表直接返回
        if (head == null || head.next == null || head.next.next == null) return;
        // 1. 快慢指针找到链表中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 2. 翻转一半的链表
        ListNode pre = null, cur = slow;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 3. 依次连接头和尾直到链表中点
        ListNode tail = pre;
        while (head != null && tail != null && head.next != tail) {
            ListNode nextHead = head.next, nextTail = tail.next;
            head.next = tail;
            tail.next = nextHead;
            head = nextHead;
            tail = nextTail;
        }
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ReorderList reorderList = new ReorderList();
        reorderList.reorderList(head);
        System.out.println(head);
    }

    public void reorderList2(ListNode head) {
        if (head == null || head.next == null) return;
        // 1. 快慢指针找中点
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode head2 = slow.next;
        slow.next = null;
        // 2. 翻转后一半链表
        ListNode pre = null, cur = head2;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        // 3. 合并两个链表
        ListNode l1 = head, l2 = pre;
        while (l1 != null && l2 != null) {
            ListNode next = l1.next, next2 = l2.next;
            l1.next = l2;
            l2.next = next;
            l1 = next;
            l2 = next2;
        }
    }
}
