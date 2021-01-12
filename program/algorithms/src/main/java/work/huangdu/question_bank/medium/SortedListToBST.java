package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.ListNode;
import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

/**
 * 109. 有序链表转换二叉搜索树
 * 给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 * 给定的有序链表： [-10, -3, 0, 5, 9],
 * 一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：
 * *      0
 * *     / \
 * *   -3   9
 * *   /   /
 * * -10  5
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/8/18 13:03
 */
public class SortedListToBST {
    // 分治+中序遍历 最优解

    // 1. 构建树
    // 2. 中序遍历树，把值放进去
    public TreeNode sortedListToBST2(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode listCur = head.next;
        TreeNode root = new TreeNode(), treeCur;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (true) {
            treeCur = queue.remove();
            treeCur.left = new TreeNode();
            if ((listCur = listCur.next) == null) break;
            treeCur.right = new TreeNode();
            if ((listCur = listCur.next) == null) break;
            queue.offer(treeCur.left);
            queue.offer(treeCur.right);
        }
        //curListNode = head;
        //inorder(root);
        Deque<TreeNode> stack = new ArrayDeque<>();
        treeCur = root;
        listCur = head;
        while (!stack.isEmpty() || treeCur != null) {
            while (treeCur != null) {
                stack.push(treeCur);
                treeCur = treeCur.left;
            }
            treeCur = stack.remove();
            treeCur.val = listCur.val;
            listCur = listCur.next;
            treeCur = treeCur.right;
        }
        return root;
    }

    /*  private ListNode curListNode;

      private void inorder(TreeNode root) {
          if (root == null) return;
          inorder(root.left);
          root.val = curListNode.val;
          curListNode = curListNode.next;
          inorder(root.right);
      }*/
    public TreeNode sortedListToBST3(ListNode head) {
        return sortedListToBST(head, null);
    }

    public TreeNode sortedListToBST(ListNode head, ListNode tail) {
        if (head == tail) return null;
        if (head.next == tail) return new TreeNode(head.val);
        ListNode mid = findMid(head, tail);
        TreeNode root = new TreeNode(mid.val);
        root.left = sortedListToBST(head, mid);
        root.right = sortedListToBST(mid.next, tail);
        return root;
    }

    private ListNode findMid(ListNode left, ListNode right) {
        ListNode slow = left, fast = left;
        while (fast != right && fast.next != right) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private ListNode current;

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        ListNode cur = head;
        int n = 0;
        while (cur != null) {
            n++;
            cur = cur.next;
        }
        current = head;
        return inorder(n);
    }

    private TreeNode inorder(int n) {
        if (n == 0) return null;
        TreeNode root = new TreeNode();
        root.left = inorder(n >> 1);
        root.val = current.val;
        current = current.next;
        root.right = inorder(n - 1 - (n >> 1));
        return root;
    }
}
