package work.huangdu.exploration.start_from_scratch.tree.level_order;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 112. 路径总和
 * 给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * *              5
 * *             / \
 * *            4   8
 * *           /   / \
 * *          11  13  4
 * *         /  \      \
 * *        7    2      1
 * 返回 true, 因为存在目标和为 22 的根节点到叶子节点的路径 5->4->11->2。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/9 17:54
 * @see PathSum2
 * @see work.huangdu.question_bank.medium.PathSum3
 */
public class HasPathSum {

/*    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null && root.val == sum) return true;
        return this.hasPathSum(root.left, sum - root.val) ||
                this.hasPathSum(root.right, sum - root.val);
    }*/

    // 递归
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        sum -= root.val;
        if (root.left == null && root.right == null) return sum == 0;
        return hasPathSum(root.left, sum) || hasPathSum(root.right, sum);
    }

    public boolean hasPathSum2(TreeNode root, int target) {
        if (root == null) return false;
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> sumQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        sumQueue.offer(0);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.poll();
                Integer sum = sumQueue.poll() + node.val;
                if (node.left == null && node.right == null) {
                    if (sum == target) {
                        return true;
                    }
                    continue;
                }
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    sumQueue.offer(sum);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    sumQueue.offer(sum);
                }
            }
        }
        return false;
    }
}