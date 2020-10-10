package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.TreeNode;
import work.huangdu.question_bank.easy.HasPathSum;

import java.util.ArrayList;
import java.util.List;

/**
 * 113. 路径总和 II
 * 给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 给定如下二叉树，以及目标和 sum = 22，
 * *               5
 * *              / \
 * *             4   8
 * *            /   / \
 * *           11  13  4
 * *          /  \    / \
 * *         7    2  5   1
 * 返回:
 * [
 * [5,4,11,2],
 * [5,8,4,5]
 * ]
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/26 10:19
 * @see HasPathSum
 */
public class PathSum2 {
    private final List<List<Integer>> res = new ArrayList<>();
    private final List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        helper(root, sum);
        return res;
    }

    private void helper(TreeNode node, int sum) {
        if (node == null) return;
        path.add(node.val);
        sum -= node.val;
        if (node.left == null && node.right == null) {
            if (sum == 0) {
                res.add(new ArrayList<>(path));
            }
            path.remove(path.size() - 1);
            return;
        }
        helper(node.left, sum);
        helper(node.right, sum);
        path.remove(path.size() - 1);
    }
}
