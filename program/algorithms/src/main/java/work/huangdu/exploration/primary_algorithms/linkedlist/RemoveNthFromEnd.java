package work.huangdu.exploration.primary_algorithms.linkedlist;

import work.huangdu.data_structure.ListNode;

/**
 * 删除链表的倒数第N个节点
 * 给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 n = 2.
 * 当删除了倒数第二个节点后，链表变为 1->2->3->5.
 * 说明：
 * 给定的 n 保证是有效的。
 * 进阶：
 * 你能尝试使用一趟扫描实现吗？
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 17:53
 */
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        ListNode tail = head;
        while (n-- > 0)
            tail = tail.next;
        while (tail != null) {
            cur = cur.next;
            tail = tail.next;
        }
        cur.next = cur.next.next;
        return dummy.next;
    }

    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode currentNode = head;
        // 如果head只有一个元素则直接返回null
        if (head.next == null)
            return null;
        // 直接删除最后一个
        if (n == 1) {
            while (currentNode.next.next != null)
                currentNode = currentNode.next;
            currentNode.next = null;
            return head;
        }
        // 扫描一遍，计算链表总长度len
        int len = 1;
        while (currentNode.next != null) {
            currentNode = currentNode.next;
            len++;
        }
        // 计算要删除的index
        int delIndex = len - n;
        // 删除第一个
        if (delIndex == 0)
            return head.next;
        // 删除非第一个
        int currentIndex = 0;
        currentNode = head;
        while (++currentIndex != delIndex) {
            currentNode = currentNode.next;
        }
        currentNode.next = currentNode.next.next;
        return head;
    }
}
