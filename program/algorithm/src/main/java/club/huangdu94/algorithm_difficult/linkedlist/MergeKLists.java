package club.huangdu94.algorithm_difficult.linkedlist;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 合并K个元素的有序链表
 * 合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。
 * 示例:
 * 输入:
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 输出: 1->1->2->3->4->4->5->6
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/28 19:51
 */
public class MergeKLists {
    // 暴力解 每一次遍历列表的每一个节点找到最小的 时间复杂度o(k^2*n) 其中k为链表条数,n为链表平均长度
    // 总耗时 309 ms
    public ListNode mergeKLists1(ListNode[] lists) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (true) {
            ListNode minNode = null;
            int minIndex = -1;
            for (int i = 0; i < lists.length; i++)
                if (lists[i] != null)
                    if (minNode == null) {
                        minNode = lists[i];
                        minIndex = i;
                    } else if (lists[i].val < minNode.val) {
                        minNode = lists[i];
                        minIndex = i;
                    }
            if (minNode == null)
                return dummy.next;
            cur.next = minNode;
            cur = cur.next;
            lists[minIndex] = lists[minIndex].next;
        }
    }

    // 排序解法 每一次合并一个节点后 调整lists排序 时间复杂度(耗时71 ms)
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        if (lists.length == 1)
            return lists[0];
        int curIndex = this.mySort(lists);
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        // 如果第一个非null元素下标越界则循环结束
        while (curIndex < lists.length) {
            // 一直使用第一个非null元素下标对应的节点
            cur.next = lists[curIndex];
            lists[curIndex] = lists[curIndex].next;
            // 重新上浮
            curIndex = this.mySort(lists, curIndex);
            cur = cur.next;
        }
        return dummy.next;
    }

    // 自定义排序 升序排序 (null元素放在前面,返回第一个非null元素下标)
    private int mySort(ListNode[] lists) {
        int index = 0;
        // 跳过前面的null
        while (index < lists.length && lists[index] == null)
            index++;
        // 将null都移到前面
        for (int i = index; i < lists.length; i++)
            if (lists[i] == null) {
                lists[i] = lists[index];
                lists[index++] = null;
            }
        // 从第一个非null元素开始插入排序
        for (int i = index + 1, j = i; i < lists.length; j = ++i) {
            ListNode ai = lists[i];
            while (ai.val < lists[j - 1].val) {
                lists[j] = lists[j - 1];
                if (--j == index)
                    break;
            }
            lists[j] = ai;
        }
        // 返回第一个非null元素的下标
        return index;
    }

    // 上浮 (返回第一个非null元素下标)
    private int mySort(ListNode[] lists, int curIndex) {
        ListNode change = lists[curIndex];
        // 如果该位置链表已经用完则往后移一位
        if (change == null)
            return curIndex + 1;
        // 否则上浮
        int i = curIndex + 1;
        while (i < lists.length && change.val > lists[i].val)
            lists[i - 1] = lists[i++];
        lists[i - 1] = change;
        // 返回第一个非null元素下标
        return curIndex;
    }

    // 两两合并 每次合并两条(耗时118ms)时间复杂度o(k^2*n)同暴力解,但是系数大约为二分之一
    public ListNode mergeKLists3(ListNode[] lists) {
        if (lists == null || lists.length <= 0)
            return null;
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++)
            head = this.mergeTwoLists(head, lists[i]);
        return head;
    }

    // 优先队列解法 执行耗时只有8ms 时间复杂度o(n*k logk)
    public ListNode mergeKLists4(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        PriorityQueue<ListNode> listsQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
        for (ListNode list : lists)
            if (list != null)
                listsQueue.add(list);
        while (!listsQueue.isEmpty()) {
            ListNode small = listsQueue.poll();
            cur.next = small;
            cur = cur.next;
            small = small.next;
            if (small != null)
                listsQueue.offer(small);
        }
        return dummy.next;
    }

    // 分治法 时间复杂度n*k logk 2ms
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0)
            return null;
        return mergeKLists(lists, 0, lists.length - 1);
    }

    private ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (left == right)
            return lists[left];
        int mid = left + (right - left) / 2;
        ListNode leftList = this.mergeKLists(lists, left, mid);
        ListNode rightList = this.mergeKLists(lists, mid + 1, right);
        return mergeTwoLists(leftList, rightList);
    }

    // 合并两个链表
    private ListNode mergeTwoLists(ListNode pre, ListNode next) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (pre != null && next != null) {
            if (pre.val < next.val) {
                cur.next = pre;
                pre = pre.next;
            } else {
                cur.next = next;
                next = next.next;
            }
            cur = cur.next;
        }
        cur.next = pre == null ? next : pre;
        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode head12 = new ListNode(4);
        ListNode head13 = new ListNode(5);
        head1.next = head12;
        head12.next = head13;

        ListNode head2 = new ListNode(1);
        ListNode head22 = new ListNode(3);
        ListNode head23 = new ListNode(4);
        head2.next = head22;
        head22.next = head23;

        ListNode head3 = new ListNode(2);
        head3.next = new ListNode(6);

        /*ListNode head1 = new ListNode(-8);
        ListNode head12 = new ListNode(-7);
        ListNode head13 = new ListNode(-7);
        ListNode head14 = new ListNode(-5);
        ListNode head15 = new ListNode(1);
        ListNode head16 = new ListNode(1);
        ListNode head17 = new ListNode(3);
        ListNode head18 = new ListNode(4);
        head1.next = head12;
        head12.next = head13;
        head13.next = head14;
        head14.next = head15;
        head15.next = head16;
        head16.next = head17;
        head17.next = head18;

        ListNode head2 = new ListNode(-2);

        ListNode head3 = new ListNode(-10);
        ListNode head31 = new ListNode(-10);
        ListNode head32 = new ListNode(-7);
        ListNode head33 = new ListNode(0);
        ListNode head34 = new ListNode(1);
        ListNode head35 = new ListNode(3);
        head3.next = head31;
        head31.next = head32;
        head32.next = head33;
        head33.next = head34;
        head34.next = head35;

        ListNode head4 = new ListNode(2);*/

        ListNode[] lists = {head1, head2, head3};
        ListNode[] lists2 = {};
        MergeKLists mergeKLists = new MergeKLists();
        ListNode head = mergeKLists.mergeKLists(lists2);
        System.out.println(Arrays.toString(lists));
    }
}

//Definition for singly-linked list.
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}
