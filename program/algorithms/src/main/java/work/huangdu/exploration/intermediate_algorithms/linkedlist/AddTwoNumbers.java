package work.huangdu.exploration.intermediate_algorithms.linkedlist;

import work.huangdu.data_structure.ListNode;

/**
 * 2. 两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/4 19:12
 */
public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode p0 = dummy;
        // 是否进位
        boolean flag = false;
        while (l1 != null || l2 != null) {
            if ((l1 == null || l2 == null) && !flag)
                break;
            int n1 = l1 == null ? 0 : l1.val;
            int n2 = l2 == null ? 0 : l2.val;
            int val = n1 + n2;
            if (flag)
                val += 1;
            if (val > 9) {
                flag = true;
                p0.next = new ListNode(val - 10);
            } else {
                flag = false;
                p0.next = new ListNode(val);
            }
            p0 = p0.next;
            if (l1 != null)
                l1 = l1.next;
            if (l2 != null)
                l2 = l2.next;
        }
        if (l1 != null)
            p0.next = l1;
        else if (l2 != null)
            p0.next = l2;
        else if (flag)
            p0.next = new ListNode(1);
        return dummy.next;
    }

    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode res = new ListNode(), cur = res;
        int add = 0;
        while (l1 != null || l2 != null || add != 0) {
            int sum = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + add;
            cur.next = new ListNode(sum % 10);
            add = sum / 10;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
            cur = cur.next;
        }
        return res.next;
    }
}