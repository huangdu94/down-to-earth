package work.huangdu.exploration.primary_algorithms.linkedlist;

import work.huangdu.data_structure.ListNode;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * 示例：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/7/26 18:11
 */
public class MergeTwoLists {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(), cur = dummy;
        while(l1 != null || l2 != null){
            if (l2 == null || l1 != null && l1.val <= l2.val){
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }

    public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l2 == null ? l1 : l2;
        return dummy.next;
    }

    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        // 如果l1为null则直接返回l2，如果l2为null则直接返回l1
        if (l1 == null)
            return l2;
        if (l2 == null)
            return l1;
        // 比较链表头的值，以小的为基准合并链表
        if (l1.val > l2.val)
            return mergeTwoLists(l2, l1);
        // 记录基准链表头，作为返回值
        ListNode head = l1;
        // 当l1的next不为空时，保持循环
        while (l1.next != null) {
            // 当l1的next的值大于l2的值时，将l2插入l1与l1的next之间，否则l1指针后移
            if (l1.next.val > l2.val) {
                ListNode l1next = l1.next;
                ListNode l2next = l2.next;
                l1.next = l2;
                l2.next = l1next;
                // 如果l2的next为null，则合并结束返回head
                if (l2next == null)
                    return head;
                // 指针后移
                l1 = l2;
                l2 = l2next;
            } else {
                l1 = l1.next;
            }
        }
        // 当l1的next为空时，则直接将l2合并到l1末尾，返回head
        l1.next = l2;
        return head;
    }
}
