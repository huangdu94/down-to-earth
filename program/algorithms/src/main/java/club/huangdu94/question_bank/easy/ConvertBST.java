package club.huangdu94.question_bank.easy;

import club.huangdu94.data_structure.TreeNode;

/**
 * 538. 把二叉搜索树转换为累加树
 * 1038. 从二叉搜索树到更大和树
 * 给定一个二叉搜索树（Binary Search Tree），把它转换成为累加树（Greater Tree)，使得每个节点的值是原来的节点值加上所有大于它的节点值之和。
 * 例如：
 * 输入: 原始二叉搜索树:
 * *               5
 * *             /   \
 * *            2     13
 * 输出: 转换为累加树:
 * *              18
 * *             /   \
 * *           20     13
 * 给出二叉 搜索 树的根节点，该二叉树的节点值各不相同，修改二叉树，使每个节点 node的新值等于原树中大于或等于node.val的值之和。
 * 提醒一下，二叉搜索树满足下列约束条件：
 * 节点的左子树仅包含键 小于 节点键的节点。
 * 节点的右子树仅包含键 大于 节点键的节点。
 * 左右子树也必须是二叉搜索树。
 * 示例：
 * 输入：[4,1,6,0,2,5,7,null,null,null,3,null,null,null,8]
 * 输出：[30,36,21,36,35,26,15,null,null,null,33,null,null,null,8]
 * 提示：
 * 树中的节点数介于 1 和 100 之间。
 * 每个节点的值介于0 和100之间。
 * 给定的树为二叉搜索树。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/21 0:23
 */
public class ConvertBST {
    public TreeNode convertBST1(TreeNode root) {
        // dfs(root, 0);
        inorder(root);
        return root;
    }

    private int dfs(TreeNode root, int sum) {
        if (root == null) return 0;
        root.val += (sum + dfs(root.right, sum));
        return root.val + dfs(root.left, root.val) - sum;
    }

    private int sum = 0;

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.right);
        root.val = (sum += root.val);
        inorder(root.left);
    }

    // 反向莫里斯遍历(先写一个正序的莫里斯遍历，再改反过来)
    public TreeNode convertBST(TreeNode root) {
        int sum = 0;
        TreeNode cur = root;
        while (cur != null) {
            TreeNode prev = cur.right;
            if (prev == null) {
                cur.val = (sum += cur.val);
                cur = cur.left;
            } else {
                while (prev.left != null && prev.left != cur) {
                    prev = prev.left;
                }
                if (prev.left == null) {
                    prev.left = cur;
                    cur = cur.right;
                } else {
                    cur.val = (sum += cur.val);
                    prev.left = null;
                    cur = cur.left;
                }
            }
        }
        return root;
    }
}
