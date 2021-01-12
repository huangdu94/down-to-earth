package work.huangdu.exploration.start_from_scratch.tree.recursion;

import work.huangdu.data_structure.TreeNode;

/**
 * 687. 最长同值路径
 * 给定一个二叉树，找到最长的路径，这个路径中的每个节点具有相同值。 这条路径可以经过也可以不经过根节点。
 * 注意：两个节点之间的路径长度由它们之间的边数表示。
 * 示例 1:
 * 输入:
 * *               5
 * *              / \
 * *             4   5
 * *            / \   \
 * *           1   1   5
 * 输出:
 * 2
 * 示例 2:
 * 输入:
 * *               1
 * *              / \
 * *             4   5
 * *            / \   \
 * *           4   4   5
 * 输出:
 * 2
 * 注意: 给定的二叉树不超过10000个结点。 树的高度不超过1000。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2021/1/2 10:09
 */
public class LongestUnivaluePath {
    private int maxPath = 0;

    public int longestUnivaluePath(TreeNode root) {
        dfs(root);
        return maxPath;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int sum = 0, max = 0;
        if (left != 0 && node.val == node.left.val) {
            sum += left;
            max = left;
        }
        if (right != 0 && node.val == node.right.val) {
            sum += right;
            max = Math.max(max, right);
        }
        maxPath = Math.max(maxPath, sum);
        return max + 1;
    }
}
