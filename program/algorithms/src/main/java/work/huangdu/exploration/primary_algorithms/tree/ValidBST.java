package work.huangdu.exploration.primary_algorithms.tree;

import work.huangdu.data_structure.TreeNode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个二叉树，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 假设一个二叉搜索树具有如下特征：
 * <p>
 * 节点的左子树只包含小于当前节点的数。
 * 节点的右子树只包含大于当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * 示例 1:
 * <p>
 * 输入:
 * 2
 * / \
 * 1   3
 * 输出: true
 * 示例 2:
 * <p>
 * 输入:
 * 5
 * / \
 * 1   4
 * / \
 * 3   6
 * 输出: false
 * 解释: 输入为: [5,1,4,null,null,3,6]。
 * 根节点的值为 5 ，但是其右子节点值为 4 。
 *
 * @author duhuang@iflytek.com
 * @version 2020/6/22 17:18
 */
public class ValidBST {
    private List<Integer> list = new LinkedList<>();

    public boolean isValidBST(TreeNode root) {
        if (root == null)
            return true;
        if (root.left == null && root.right == null)
            return true;
        inorder(root);
        Iterator<Integer> iterator = list.iterator();
        int pre = iterator.next();
        int current;
        while (iterator.hasNext()) {
            current = iterator.next();
            if (pre >= current)
                return false;
            pre = current;
        }
        return true;
    }

    /**
     * 先序遍历 根 左 右
     */
    public void preorder(TreeNode node) {
        list.add(node.val);
        if (node.left != null)
            preorder(node.left);
        if (node.right != null)
            preorder(node.right);
    }

    /**
     * 中序遍历 左 根 右
     */
    public void inorder(TreeNode node) {
        if (node.left != null)
            inorder(node.left);
        list.add(node.val);
        if (node.right != null)
            inorder(node.right);
    }

    /**
     * 后序遍历 左 右 根
     */
    public void postorder(TreeNode node) {
        if (node.left != null)
            postorder(node.left);
        if (node.right != null)
            postorder(node.right);
        list.add(node.val);
    }
}
