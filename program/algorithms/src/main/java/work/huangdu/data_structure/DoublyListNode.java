package work.huangdu.data_structure;

/**
 * Definition for doubly linked list.
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/5 21:56
 */
public class DoublyListNode {
    public int val;
    public DoublyListNode prev;
    public DoublyListNode next;

    public DoublyListNode() {
    }

    public DoublyListNode(int x) {
        val = x;
    }

    public DoublyListNode(int val, DoublyListNode prev, DoublyListNode next) {
        this.val = val;
        this.prev = prev;
        this.next = next;
    }
}
