package work.huangdu.exploration.intermediate_algorithms.linkedlist;

import work.huangdu.data_structure.ListNode;

/**
 * 相交链表
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/4 19:16
 */
public class GetIntersectionNode {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null)
            return null;
        int lenA = 1;
        int lenB = 1;
        ListNode tail = new ListNode(0);
        ListNode pA = headA;
        ListNode pB = headB;
        // 先把List B的末尾加一个tail节点(并计算List B的长度)
        while (pB.next != null) {
            lenB += 1;
            pB = pB.next;
        }
        pB.next = tail;
        // 遍历List A(并计算List A的长度，以headB为结束信号，如果遇到null表示不相交)
        while (pA.next != tail) {
            // 说明AB不相交
            if (pA.next == null) {
                pB.next = null;
                return null;
            }
            lenA += 1;
            pA = pA.next;
        }
        // 恢复List B(环的作用已经达到了)
        pB.next = null;
        // 重置pA pB到List头部(程序能运行到这里说明AB肯定相交)
        pA = headA;
        pB = headB;
        // 根据长度差值移动指针到同一起跑线
        int diff = lenA - lenB;
        if (diff > 0) {
            while (diff-- > 0)
                pA = pA.next;
        } else if (diff < 0) {
            diff = -diff;
            while (diff-- > 0)
                pB = pB.next;
        }
        // 输出相交点值
        while (true) {
            if (pA == pB)
                return pA;
            pA = pA.next;
            pB = pB.next;
        }
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        node1.next = node2;
        System.out.println(new GetIntersectionNode().getIntersectionNode(node1, node2).val);
    }
}
