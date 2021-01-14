package work.huangdu.exploration.start_from_scratch.double_pointer.homonymous_sliding_window;

import work.huangdu.data_structure.ListNode;

/**
 * 82. 删除排序链表中的重复元素 II
 * 给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。
 * 示例 1:
 * 输入: 1->2->3->3->4->4->5
 * 输出: 1->2->5
 * 示例 2:
 * 输入: 1->1->1->2->3
 * 输出: 2->3
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/12/14 11:30
 */
public class DeleteDuplicates2 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0), cur = dummy;
        dummy.next = head;
        while (cur.next != null) {
            ListNode next = cur.next;
            if (next.next != null && next.val == next.next.val) {
                int val = next.val;
                while (next != null && next.val == val) {
                    next = next.next;
                }
                cur.next = next;
            } else {
                cur = cur.next;
            }
        }
        return dummy.next;
    }
}
