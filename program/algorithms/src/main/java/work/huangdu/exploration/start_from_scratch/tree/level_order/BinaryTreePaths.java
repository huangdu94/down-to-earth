package work.huangdu.exploration.start_from_scratch.tree.level_order;

import work.huangdu.data_structure.TreeNode;
import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

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

    public List<String> binaryTreePaths2(TreeNode root) {
        //dfs(root, "");
        bfs(root);
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

    private void bfs(TreeNode root) {
        if (root == null) return;
        Queue<Pair<TreeNode, String>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(root, ""));
        while (!queue.isEmpty()) {
            Pair<TreeNode, String> pair = queue.poll();
            TreeNode node = pair.getKey();
            String str = pair.getValue();
            str = str.concat(Integer.toString(node.val));
            if (node.left == null && node.right == null) {
                res.add(str);
            }
            str = str.concat("->");
            if (node.left != null) {
                queue.offer(new Pair<>(node.left, str));
            }
            if (node.right != null) {
                queue.offer(new Pair<>(node.right, str));
            }
        }
    }

    private List<String> paths;

    public List<String> binaryTreePaths(TreeNode root) {
        this.paths = new ArrayList<>();
        binaryTreePaths(root, "");
        return paths;
    }

    private void binaryTreePaths(TreeNode node, String path) {
        if (node == null) return;
        path = path.concat(Integer.toString(node.val));
        if (node.left == null && node.right == null) {
            paths.add(path);
            return;
        }
        path = path.concat("->");
        binaryTreePaths(node.left, path);
        binaryTreePaths(node.right, path);
    }
}
