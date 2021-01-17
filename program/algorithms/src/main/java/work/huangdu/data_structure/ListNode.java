package work.huangdu.data_structure;

/**
 * Definition for singly-linked list.
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/13 13:45
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
