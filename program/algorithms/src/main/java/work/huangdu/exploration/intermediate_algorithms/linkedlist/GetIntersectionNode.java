package work.huangdu.exploration.intermediate_algorithms.linkedlist;

import work.huangdu.data_structure.ListNode;

/**
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 * 如下面的两个链表：
 * 在节点 c1 开始相交。
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 * 注意：
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/4 19:16
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


    public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        ListNode pa = headA, pb = headB;
        while (pa != null && pb != null) {
            if (pa == pb) {
                return pa;
            }
            if (pa.next == pb.next && pa.next == null) {
                return null;
            }
            pa = pa.next == null ? headB : pa.next;
            pb = pb.next == null ? headA : pb.next;
        }
        return null;
    }

    public ListNode getIntersectionNode3(ListNode headA, ListNode headB) {
        ListNode pa = headA, pb = headB;
        while (pa != pb) {
            pa = pa == null ? headB : pa.next;
            pb = pb == null ? headA : pb.next;
        }
        return pa;
    }
}
