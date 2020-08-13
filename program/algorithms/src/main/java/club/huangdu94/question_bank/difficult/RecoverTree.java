package club.huangdu94.question_bank.difficult;

import java.util.Stack;

/**
 * 99. 恢复二叉搜索树
 * 二叉搜索树中的两个节点被错误地交换。
 * 请在不改变其结构的情况下，恢复这棵树。
 * 示例 1:
 * 输入: [1,3,null,null,2]
 * *   1
 * *  /
 * * 3
 * *  \
 * *   2
 * 输出: [3,1,null,null,2]
 * *   3
 * *  /
 * * 1
 * *  \
 * *   2
 * 示例 2:
 * 输入: [3,1,4,null,null,2]
 * *  3
 * * / \
 * *1   4
 * *   /
 * *  2
 * 输出: [2,1,4,null,null,3]
 * *  2
 * * / \
 * *1   4
 * *   /
 * *  3
 * 使用 O(n) 空间复杂度的解法很容易实现。
 * 你能想出一个只使用常数空间的解决方案吗？
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/8 10:57
 */
public class RecoverTree {
    //Definition for a binary tree node.
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // 莫里斯中序遍历
    public void recoverTree(TreeNode root) {
        TreeNode a = null;
        TreeNode b = null;
        TreeNode pre = null;
        TreeNode prev;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (pre != null && pre.val > cur.val) {
                    b = cur;
                    if (a == null) a = pre;
                }
                pre = cur;
                cur = cur.right;
            } else {
                prev = cur.left;
                while (prev.right != null && prev.right != cur)
                    prev = prev.right;
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    if (pre != null && pre.val > cur.val) {
                        b = cur;
                        if (a == null) a = pre;
                    }
                    pre = cur;
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        this.swap(a, b);
    }

    public void recoverTree2(TreeNode root) {
        TreeNode a = null;
        TreeNode b = null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        TreeNode cur = root;
        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            if (pre != null && pre.val > cur.val) {
                b = cur;
                if (a == null)
                    a = pre;
                else
                    break;
            }
            pre = cur;
            cur = cur.right;
        }
        this.swap(a, b);
    }

    /**
     * 交换a和b的值
     *
     * @param a 错误节点a
     * @param b 错误节点b
     */
    private void swap(TreeNode a, TreeNode b) {
        if (a != null && b != null) {
            int temp = a.val;
            a.val = b.val;
            b.val = temp;
        }
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3, null, new TreeNode(2));
        RecoverTree recoverTree = new RecoverTree();
        recoverTree.recoverTree(root);
        System.out.println("...");
    }
}
