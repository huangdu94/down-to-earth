package work.huangdu.exploration.primary_algorithms.tree;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 102. 二叉树的层序遍历
 * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 * 示例：
 * 二叉树：[3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/6/22 17:42
 */
public class LevelOrder {
    public List<List<Integer>> levelOrder3(TreeNode root) {
        List<List<Integer>> resultList = new LinkedList<>();
        if (root == null)
            return resultList;
        List<TreeNode> currentLevelTreeNodeList = new LinkedList<>();
        List<TreeNode> nextLevelTreeNodeList;
        currentLevelTreeNodeList.add(root);
        do {
            nextLevelTreeNodeList = new LinkedList<>();
            List<Integer> levelValueList = new LinkedList<>();
            for (TreeNode node : currentLevelTreeNodeList) {
                levelValueList.add(node.val);
                if (node.left != null)
                    nextLevelTreeNodeList.add(node.left);
                if (node.right != null)
                    nextLevelTreeNodeList.add(node.right);
            }
            resultList.add(levelValueList);
            currentLevelTreeNodeList = nextLevelTreeNodeList;
        } while (!currentLevelTreeNodeList.isEmpty());
        return resultList;
    }

    public List<List<Integer>> levelOrder2(TreeNode root) {
        if (root == null)
            return new LinkedList<>();
        List<List<Integer>> resultList = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        do {
            int size = queue.size();
            List<Integer> valueList = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                valueList.add(node.val);
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
            }
            resultList.add(valueList);
        } while (!queue.isEmpty());
        return resultList;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(result, root, 0);
        return result;
    }

    private void dfs(List<List<Integer>> result, TreeNode node, int level) {
        if (node == null) return;
        if (level == result.size()) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node.val);
        dfs(result, node.left, level + 1);
        dfs(result, node.right, level + 1);
    }
}
