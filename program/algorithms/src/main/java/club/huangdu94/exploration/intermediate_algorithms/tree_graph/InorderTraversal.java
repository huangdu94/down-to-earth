package club.huangdu94.exploration.intermediate_algorithms.tree_graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 中序遍历二叉树
 * 给定一个二叉树，返回它的中序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 * 1
 * \
 * 2
 * /
 * 3
 * 输出: [1,3,2]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/5 19:28
 */
public class InorderTraversal {
    // Definition for a binary tree node.
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        if (root == null)
            return inorderList;
        Stack<TreeNode> nodeStack = new Stack<>();
        TreeNode cur = root;
        while (!nodeStack.empty() || cur != null) {
            while (cur != null) {
                nodeStack.push(cur);
                cur = cur.left;
            }
            cur = nodeStack.pop();
            inorderList.add(cur.val);
            cur = cur.right;
        }
        return inorderList;
    }

    /**
     * 莫里斯中序遍历
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> inorderList = new ArrayList<>();
        if (root == null)
            return inorderList;
        TreeNode cur = root;
        TreeNode prev;
        while (cur != null) {
            if (cur.left == null) {
                inorderList.add(cur.val);
                cur = cur.right;
            } else {
                prev = cur.left;
                while (prev.right != null && prev.right != cur)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    inorderList.add(cur.val);
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return inorderList;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode root = node1;
        node1.right = node2;
        node2.left = node3;
        System.out.println(new InorderTraversal().inorderTraversal2(root));
    }
}
