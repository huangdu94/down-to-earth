package work.huangdu.exploration.start_from_scratch.linkedlist.rotate_reverse;

import work.huangdu.data_structure.ListNode;

/**
 * 25. K 个一组翻转链表
 * 给你一个链表，每 k 个节点一组进行翻转，请你返回翻转后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 示例：
 * 给你这个链表：1->2->3->4->5
 * 当 k = 2 时，应当返回: 2->1->4->3->5
 * 当 k = 3 时，应当返回: 3->2->1->4->5
 * 说明：
 * 你的算法只能使用常数的额外空间。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/10/28 16:39
 */
public class ReverseKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(), cur = dummy;
        dummy.next = head;
        while ((cur = reverseNextK(cur, k)) != null) ;
        return dummy.next;
    }

    /**
     * 如果剩下的链表数量小于k个则直接返回null
     * 否则反转后返回新的尾结点
     *
     * @param cur 上一次翻转链表的tail
     * @return ListNode
     */
    private ListNode reverseNextK(ListNode cur, int k) {
        ListNode head = cur;
        int i = 0;
        while (i < k && head.next != null) {
            head = head.next;
            i++;
        }
        if (i < k) return null;
        ListNode pre = head.next, tail = cur.next;
        cur.next = head;
        cur = tail;
        while (pre != head) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return tail;
    }

    public static void main(String[] args) {
        ReverseKGroup kGroup = new ReverseKGroup();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        System.out.println(kGroup.reverseKGroup(node1, 2));
    }
}
