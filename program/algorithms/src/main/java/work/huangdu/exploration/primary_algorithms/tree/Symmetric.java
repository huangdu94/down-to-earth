package work.huangdu.exploration.primary_algorithms.tree;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 101. 对称二叉树
 * 给定一个二叉树，检查它是否是镜像对称的。
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/6/23 9:37
 */
public class Symmetric {
    public boolean isSymmetric2(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        List<TreeNode> levelList;
        while (!queue.isEmpty()) {
            levelList = new ArrayList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                levelList.add(node);
                if (node != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
            for (int i = 0, j = levelList.size() - 1; i < j; i++, j--) {
                if (levelList.get(i) == null && levelList.get(j) == null)
                    continue;
                if (levelList.get(i) == null || levelList.get(j) == null)
                    return false;
                if (levelList.get(i).val != levelList.get(j).val)
                    return false;
            }
        }
        return true;
    }

    public boolean isSymmetric(TreeNode root) {
        return isSymmetric(root, root);
    }

    private boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null) return false;
        return node1.val == node2.val && isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
    }
}
