package work.huangdu.exploration.start_from_scratch.tree.inorder_binary_search_tree;

import work.huangdu.data_structure.TreeNode;

/**
 * 700. 二叉搜索树中的搜索
 * 给定二叉搜索树（BST）的根节点和一个值。 你需要在BST中找到节点值等于给定值的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 NULL。
 * 例如，
 * 给定二叉搜索树:
 * *        4
 * *       / \
 * *      2   7
 * *     / \
 * *    1   3
 * 和值: 2
 * 你应该返回如下子树:
 * *      2
 * *     / \
 * *    1   3
 * 在上述示例中，如果要找的值是 5，但因为没有节点值为 5，我们应该返回 NULL。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2021/2/6
 */
public class SearchBST {
    public TreeNode searchBST2(TreeNode root, int val) {
        while (root != null && val != root.val) {
            if (val < root.val) {
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return root;
    }

    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || val == root.val) return root;
        return val < root.val ? searchBST(root.left, val) : searchBST(root.right, val);
    }
}
