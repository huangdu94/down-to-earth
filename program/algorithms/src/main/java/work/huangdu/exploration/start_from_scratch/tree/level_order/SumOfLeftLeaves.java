package work.huangdu.exploration.start_from_scratch.tree.level_order;

import work.huangdu.data_structure.TreeNode;

/**
 * 404. 左叶子之和
 * 计算给定二叉树的所有左叶子之和。
 * 示例：
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 * 在这个二叉树中，有两个左叶子，分别是 9 和 15，所以返回 24
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/19 10:23
 */
public class SumOfLeftLeaves {
    private int sum = 0;

    public int sumOfLeftLeaves(TreeNode root) {
        helper(root);
        return sum;
    }

    private void helper(TreeNode root) {
        if (root == null) return;
        if (root.left != null && root.left.left == null && root.left.right == null) {
            sum += root.left.val;
        } else {
            helper(root.left);
        }
        if (root.right != null) {
            helper(root.right);
        }
    }
}
