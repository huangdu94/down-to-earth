package work.huangdu.exploration.advanced_algorithms.linkedlist;

import work.huangdu.data_structure.ListNode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 排序链表
 * 在O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 * 示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/3 14:59
 */
public class SortList {
    // 首先想到归并排序，快慢指针找链表终点
    public ListNode sortList2(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode end = head;
        while (end.next != null) {
            end = end.next;
        }
        return this.sortList(head, end);
    }

    // 首先想到归并排序，快慢指针找链表终点
    private ListNode sortList(ListNode start, ListNode end) {
        if (start == end) {
            // 截断链表，避免死循环
            start.next = null;
            return start;
        }
        ListNode mid = start;
        ListNode quick = start.next;
        while (quick != end && quick.next != end) {
            mid = mid.next;
            quick = quick.next.next;
        }
        ListNode midPlus1 = mid.next;
        ListNode left = this.sortList(start, mid);
        ListNode right = this.sortList(midPlus1, end);
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        cur.next = left == null ? right : left;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(4);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(1);
        ListNode node4 = new ListNode(3);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        SortList sortList = new SortList();
        ListNode result = sortList.sortList(head);
        System.out.println(result);
    }

    // 迭代版
    public ListNode sortList(ListNode head) {
        ListNode h, h1, h2, pre, res;
        h = head;
        int length = 0, intv = 1;
        // 求链表总长度
        while (h != null) {
            h = h.next;
            length++;
        }
        // 哑结点，返回res.next
        res = new ListNode(0);
        res.next = head;
        // 当intv大于等于length时表示合并完成，循环结束
        while (intv < length) {
            pre = res;
            h = res.next;
            // 如果h==null 则说明本轮合并已经结束
            while (h != null) {
                int i = intv;
                h1 = h;
                while (i > 0 && h != null) {
                    h = h.next;
                    i--;
                }
                // 如果i>0 h==null 则说明链表中剩余的个数少于intv不用合了
                if (i > 0) break;
                i = intv;
                h2 = h;
                while (i > 0 && h != null) {
                    h = h.next;
                    i--;
                }
                int c1 = intv, c2 = intv - i;
                // c1为h1的节点个数 c2为h2的节点个数
                while (c1 > 0 && c2 > 0) {
                    if (h1.val < h2.val) {
                        pre.next = h1;
                        h1 = h1.next;
                        c1--;
                    } else {
                        pre.next = h2;
                        h2 = h2.next;
                        c2--;
                    }
                    pre = pre.next;
                }
                pre.next = c1 == 0 ? h2 : h1;
                // 需要把pre推到合并完链表的末尾，保证pre是合并完链表的末尾
                while (c1 > 0 || c2 > 0) {
                    pre = pre.next;
                    c1--;
                    c2--;
                }
                pre.next = h;
            }
            intv *= 2;
        }
        return res.next;
    }

    // 时间复杂度o(nlogn) 空间复杂度 o(n)
    public ListNode sortList3(ListNode head) {
        List<ListNode> nodeList = new ArrayList<>();
        while (head != null) {
            nodeList.add(head);
            head = head.next;
        }
        nodeList.sort(Comparator.comparingInt(o -> o.val));
        ListNode dummy = new ListNode(), cur = dummy;
        for (ListNode node : nodeList) {
            cur.next = node;
            cur = cur.next;
        }
        cur.next = null;
        return dummy.next;
    }

    // 归并排序
    public ListNode sortList4(ListNode head) {
        if (head == null || head.next == null) return head;
        // 快慢指针找链表中点的前一个点
        ListNode mid = head, fast = head.next.next, head2, dummy = new ListNode(), cur = dummy;
        while (fast != null && fast.next != null) {
            mid = mid.next;
            fast = fast.next.next;
        }
        head2 = mid.next;
        mid.next = null;
        head = sortList4(head);
        head2 = sortList4(head2);
        while (head != null && head2 != null) {
            if (head.val <= head2.val) {
                cur.next = head;
                head = head.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        if (head == null) {
            cur.next = head2;
        } else {
            cur.next = head;
        }
        return dummy.next;
    }
}
