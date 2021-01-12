package work.huangdu.exploration.start_from_scratch.double_pointer.segment;

import work.huangdu.data_structure.ListNode;

/**
 * 86. 分隔链表
 * 给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。
 * 你应当保留两个分区中每个节点的初始相对位置。
 * 示例:
 * 输入: head = 1->4->3->2->5->2, x = 3
 * 输出: 1->2->2->4->3->5
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/12/20 12:30
 */
public class Partition {
    public ListNode partition(ListNode head, int x) {
        ListNode small = new ListNode(0), big = new ListNode(0), smallTail = small, bigTail = big;
        while (head != null) {
            if (head.val < x) {
                smallTail.next = head;
                smallTail = head;
            } else {
                bigTail.next = head;
                bigTail = head;
            }
            head = head.next;
        }
        smallTail.next = big.next;
        bigTail.next = null;
        return small.next;
    }
}
