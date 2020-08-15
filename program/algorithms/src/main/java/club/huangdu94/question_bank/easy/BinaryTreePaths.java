package club.huangdu94.question_bank.easy;

import club.huangdu94.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 257. 二叉树的所有路径
 * 给定一个二叉树，返回所有从根节点到叶子节点的路径。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 输入:
 * *    1
 * *  /   \
 * * 2     3
 * *  \
 * *   5
 * 输出: ["1->2->5", "1->3"]
 * 解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/16 0:38
 */
public class BinaryTreePaths {
    private final List<String> res = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, "");
        return res;
    }

    private void dfs(TreeNode root, String s) {
        if (root == null) return;
        s = s.concat(Integer.toString(root.val));
        if (root.left == null && root.right == null) {
            res.add(s);
            return;
        }
        s = s.concat("->");
        dfs(root.left, s);
        dfs(root.right, s);
    }
}
