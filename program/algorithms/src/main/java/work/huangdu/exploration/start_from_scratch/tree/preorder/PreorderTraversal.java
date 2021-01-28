package work.huangdu.exploration.start_from_scratch.tree.preorder;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 144. 二叉树的前序遍历
 * 给定一个二叉树，返回它的 前序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 * *    1
 * *     \
 * *      2
 * *     /
 * *    3
 * 输出: [1,2,3]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/27 11:26
 */
public class PreorderTraversal {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        // 1. 递归
        // preorder(result, root);

        // 2. 栈
        // Deque<TreeNode> stack = new ArrayDeque<>();
        // while (root != null || !stack.isEmpty()) {
        //     while (root != null) {
        //         result.add(root.val);
        //         stack.push(root);
        //         root = root.left;
        //     }
        //     root = stack.remove().right;
        // }

        // 3. 莫里斯遍历
        while (root != null) {
            if (root.left == null) {
                result.add(root.val);
                root = root.right;
            } else {
                TreeNode prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    result.add(root.val);
                    prev.right = root;
                    root = root.left;
                } else {
                    prev.right = null;
                    root = root.right;
                }
            }
        }
        return result;
    }

    private void preorder(List<Integer> result, TreeNode root) {
        if (root == null) return;
        result.add(root.val);
        preorder(result, root.left);
        preorder(result, root.right);
    }
}
