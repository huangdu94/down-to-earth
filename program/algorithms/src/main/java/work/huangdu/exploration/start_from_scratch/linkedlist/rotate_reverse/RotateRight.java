package work.huangdu.exploration.start_from_scratch.linkedlist.rotate_reverse;

import work.huangdu.data_structure.ListNode;

/**
 * 61. 旋转链表
 * 给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: 1->2->3->4->5->NULL, k = 2
 * 输出: 4->5->1->2->3->NULL
 * 解释:
 * 向右旋转 1 步: 5->1->2->3->4->NULL
 * 向右旋转 2 步: 4->5->1->2->3->NULL
 * 示例 2:
 * 输入: 0->1->2->NULL, k = 4
 * 输出: 2->0->1->NULL
 * 解释:
 * 向右旋转 1 步: 2->0->1->NULL
 * 向右旋转 2 步: 1->2->0->NULL
 * 向右旋转 3 步: 0->1->2->NULL
 * 向右旋转 4 步: 2->0->1->NULL
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/28 13:27
 */
public class RotateRight {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null) return head;
        // 计算链表长度n，并得到链表尾
        int n = 1;
        ListNode tail = head;
        while (tail.next != null) {
            tail = tail.next;
            n++;
        }
        // k%=n，超过n的部分旋转是没有意义的
        if ((k %= n) == 0) return head;
        // 链表第n-k个，(如果k为n，则计算后k为0，直接返回head，所以k最大为n-1，k+1不会越界)
        int i = n - k;
        ListNode newTail = head;
        while (--i > 0) {
            newTail = newTail.next;
        }
        ListNode newHead = newTail.next;
        newTail.next = null;
        tail.next = head;
        return newHead;
    }
}
