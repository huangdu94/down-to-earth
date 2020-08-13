package club.huangdu94.algorithm_easy.tree;

import club.huangdu94.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 * <p>
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 * <p>
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 *
 * @author duhuang@iflytek.com
 * @version 2020/6/23 9:37
 */
public class Symmetric {
    public boolean isSymmetric(TreeNode root) {
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
}
