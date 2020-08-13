package club.huangdu94.question_easy;

import club.huangdu94.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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
 * @author duhuang@iflytek.com
 * @version 2020/8/8 22:25
 */
public class LevelOrderBottom {
    private final List<List<Integer>> list = new ArrayList<>();

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
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
