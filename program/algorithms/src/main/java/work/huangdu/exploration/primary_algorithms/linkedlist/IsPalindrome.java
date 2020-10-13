package work.huangdu.exploration.primary_algorithms.linkedlist;

import work.huangdu.data_structure.ListNode;

/**
 * 回文链表
 * 请判断一个链表是否为回文链表。
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 18:20
 */
public class IsPalindrome {
    public boolean isPalindrome(ListNode head) {
        // 如果链表为空或长度为1则直接返回true
        if (head == null || head.next == null)
            return true;
        ListNode target = findStart2(head);
        // 反转包括target节点及之后的链表
        ListNode tail = reverseList(target);
        // 比较回文链表
        boolean result = compareList(head, tail);
        // 恢复反转
        reverseList(tail);
        return result;
    }

    /**
     * 找到反转的起始节点(通过链表长度计算法)
     */
    private ListNode findStart(ListNode head) {
        // 求链表长度,和尾结点指针
        int len = 1;
        ListNode p = head;
        while (p.next != null) {
            len++;
            p = p.next;
        }
        // 求链表需要开始比较的目标位置(如果len为奇数则跳过中间一个节点进行比较)
        int target = len / 2 + 1 + len % 2;
        // 将p移至需要开始比较的地方
        p = head;
        while (--target > 0)
            p = p.next;
        return p;
    }

    /**
     * 找到反转的起始节点(快慢指针法)
     */
    private ListNode findStart2(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        // 慢指针一次走一步，快指针一次走两步
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow.next;
    }

    /**
     * 反转List,返回反转后头节点
     */
    private ListNode reverseList(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 比较两list值是否相等(保留传入节点的位置)
     */
    private boolean compareList(ListNode head, ListNode tail) {
        ListNode pre = head;
        ListNode suf = tail;
        boolean result = true;
        while (result && suf != null) {
            if (pre.val != suf.val)
                result = false;
            pre = pre.next;
            suf = suf.next;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 1, 2, 1, 1};
        ListNode l1 = null;
        ListNode pre = null;
        for (int i : arr1) {
            if (pre == null) {
                l1 = new ListNode(i);
                pre = l1;
                continue;
            }
            pre.next = new ListNode(i);
            pre = pre.next;
        }
        System.out.println(new IsPalindrome().isPalindrome(l1));
        System.out.print("[ ");
        while (l1 != null) {
            System.out.print(l1.val + " -> ");
            l1 = l1.next;
        }
        System.out.println("null ]");
    }
}