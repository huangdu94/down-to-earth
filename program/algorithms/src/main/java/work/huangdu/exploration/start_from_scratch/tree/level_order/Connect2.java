package work.huangdu.exploration.start_from_scratch.tree.level_order;

import work.huangdu.exploration.intermediate_algorithms.tree_graph.Connect;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 117. 填充每个节点的下一个右侧节点指针 II
 * 给定一个二叉树
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * 初始状态下，所有 next 指针都被设置为 NULL。
 * 进阶：
 * 你只能使用常量级额外空间。
 * 使用递归解题也符合要求，本题中递归程序占用的栈空间不算做额外的空间复杂度。
 * 示例：
 * 输入：root = [1,2,3,4,5,null,7]
 * 输出：[1,#,2,3,#,4,5,7,#]
 * 解释：给定二叉树如图 A 所示，你的函数应该填充它的每个 next 指针，以指向其下一个右侧节点，如图 B 所示。
 * 提示：
 * 树中的节点数小于 6000
 * -100 <= node.val <= 100
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/28 9:27
 * @see Connect
 */
public class Connect2 {
    // Definition for a Node.
    static class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root == null) return null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node pre = null;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (pre != null) pre.next = cur;
                if (cur.left != null)
                    queue.offer(cur.left);
                if (cur.right != null)
                    queue.offer(cur.right);
                pre = cur;
            }
        }
        return root;
    }

    public Node connect2(Node root) {
        if (root == null) return null;
        Node head = root;
        while (head != null) {
            Node pre = null, cur = head, nextHead = null;
            while (cur != null) {
                if (cur.left != null) {
                    if (pre != null) pre.next = cur.left;
                    if (nextHead == null) nextHead = cur.left;
                    pre = cur.left;
                }
                if (cur.right != null) {
                    if (pre != null) pre.next = cur.right;
                    if (nextHead == null) nextHead = cur.right;
                    pre = cur.right;
                }
                cur = cur.next;
            }
            head = nextHead;
        }
        return root;
    }
}
