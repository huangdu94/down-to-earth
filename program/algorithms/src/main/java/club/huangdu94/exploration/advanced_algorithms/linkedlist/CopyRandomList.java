package club.huangdu94.exploration.advanced_algorithms.linkedlist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 复制带随机指针的链表
 * 给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。
 * 要求返回这个链表的深拷贝。
 * 我们用一个由n个节点组成的链表来表示输入/输出中的链表。每个节点用一个[val, random_index]表示：
 * val：一个表示Node.val的整数。
 * random_index：随机指针指向的节点索引（范围从0到n-1）；如果不指向任何节点，则为null。
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 * 示例 4：
 * 输入：head = []
 * 输出：[]
 * 解释：给定的链表为空（空指针），因此返回 null。
 * 提示：
 * -10000 <= Node.val <= 10000
 * Node.random为空（null）或指向链表中的节点。
 * 节点数目不超过 1000 。
 * Java
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/4 9:58
 */
public class CopyRandomList {
    public Node copyRandomList(Node head) {
        if (head == null) return null;
        // 1. 编织list，将每一个拷贝节点放到原节点的next位置，拼成一个list
        Node originNode = head;
        while (originNode != null) {
            Node copyNode = new Node(originNode.val);
            copyNode.next = originNode.next;
            originNode.next = copyNode;
            originNode = originNode.next.next;
        }
        Node cur = head;
        // 2. 赋值random
        while (cur != null) {
            if (cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 解除编织
        cur = head;
        Node newHead = head.next;
        while (cur != null) {
            originNode = cur.next;
            cur.next = cur.next.next;
            if (cur.next != null)
                originNode.next = originNode.next.next;
            cur = cur.next;
        }
        return newHead;
    }

    public Node copyRandomList2(Node head) {
        if (head == null)
            return null;
        List<Node> copyNodeList = new ArrayList<>();
        Map<Node, Integer> nodeIndexMap = new HashMap<>();
        Node cur = head;
        Node copyCur;
        int n = 0;
        while (cur != null) {
            copyCur = new Node(cur.val);
            copyNodeList.add(copyCur);
            nodeIndexMap.put(cur, n++);
            cur = cur.next;
        }
        cur = head;
        for (int i = 0; i < n; i++) {
            Node curNode = copyNodeList.get(i);
            if (i != n - 1)
                curNode.next = copyNodeList.get(i + 1);
            if (cur.random != null)
                curNode.random = copyNodeList.get(nodeIndexMap.get(cur.random));
            cur = cur.next;
        }
        return copyNodeList.get(0);
    }
}

// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
