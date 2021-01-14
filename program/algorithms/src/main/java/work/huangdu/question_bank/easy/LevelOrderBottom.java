package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.TreeNode;

import java.util.*;

/**
 * 107. 二叉树的层次遍历 II
 * 给定一个二叉树，返回其节点值自底向上的层次遍历。 （即按从叶子节点所在层到根节点所在的层，逐层从左向右遍历）
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * *   3
 * *  / \
 * * 9  20
 * *   /  \
 * *  15   7
 * 返回其自底向上的层次遍历为：
 * [
 * [15,7],
 * [9,20],
 * [3]
 * ]
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/8 22:25
 */
public class LevelOrderBottom {
    private final List<List<Integer>> list = new ArrayList<>();

    // bfs
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                level.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            res.addFirst(level);
        }
        return res;
    }

    // dfs
    public List<List<Integer>> levelOrderBottom2(TreeNode root) {
        this.levelOrder(root, 0);
        Collections.reverse(list);
        return list;
    }

    private void levelOrder(TreeNode node, int level) {
        if (node == null)
            return;
        if (list.size() <= level)
            list.add(new ArrayList<>());
        list.get(level).add(node.val);
        this.levelOrder(node.left, level + 1);
        this.levelOrder(node.right, level + 1);
    }
}
