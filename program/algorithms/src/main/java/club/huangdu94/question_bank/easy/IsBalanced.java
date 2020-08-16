package club.huangdu94.question_bank.easy;

import club.huangdu94.data_structure.TreeNode;

/**
 * 110. 平衡二叉树
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过1。
 * 示例 1:
 * 给定二叉树 [3,9,20,null,null,15,7]
 * *    3
 * *   / \
 * *  9  20
 * *    /  \
 * *   15   7
 * 返回 true 。
 * 示例 2:
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 * *      1
 * *     / \
 * *    2   2
 * *   / \
 * *  3   3
 * * / \
 * *4   4
 * 返回 false 。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/8 22:40
 */
public class IsBalanced {
    public boolean isBalanced2(TreeNode root) {
        return balanced(root) != -1;
    }

    /*private int isBalanced(TreeNode node, int height) {
        if (node == null) return height - 1;
        int leftHeight = this.isBalanced(node.left, height + 1);
        if (leftHeight == -1) return -1;
        int rightHeight = this.isBalanced(node.right, height + 1);
        if (rightHeight == -1) return -1;
        if (Math.abs(leftHeight - rightHeight) <= 1) return Math.max(leftHeight, rightHeight);
        return -1;
    }*/

    private int balanced(TreeNode node) {
        if (node == null) return 0;
        int leftHeight, rightHeight;
        if ((leftHeight = balanced(node.left)) == -1
                || (rightHeight = balanced(node.right)) == -1
                || Math.abs(leftHeight - rightHeight) > 1)
            return -1;
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
