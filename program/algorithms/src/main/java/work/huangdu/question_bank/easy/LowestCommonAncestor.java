package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.TreeNode;

/**
 * 235. 二叉搜索树的最近公共祖先
 * 给定一个二叉搜索树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉搜索树:  root = [6,2,8,0,4,7,9,null,null,3,5]
 * 示例 1:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 8
 * 输出: 6
 * 解释: 节点 2 和节点 8 的最近公共祖先是 6。
 * 示例 2:
 * 输入: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
 * 输出: 2
 * 解释: 节点 2 和节点 4 的最近公共祖先是 2, 因为根据定义最近公共祖先节点可以为节点本身。
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉搜索树中。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/15 14:41
 * @see work.huangdu.exploration.advanced_algorithms.tree_graph.LowestCommonAncestor
 */
public class LowestCommonAncestor {
    private int pVal;
    private int qVal;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        pVal = p.val;
        qVal = q.val;
        return lowestCommonAncestor(root);
    }

    private TreeNode lowestCommonAncestor(TreeNode root) {
        int rootVal = root.val;
        if (pVal == rootVal || qVal == rootVal) return root;
        if (pVal < rootVal && qVal < rootVal)
            return lowestCommonAncestor(root.left);
        if (pVal > rootVal && qVal > rootVal)
            return lowestCommonAncestor(root.right);
        return root;
    }

    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode commonAncestor = root;
        while (true) {
            if (commonAncestor.val > p.val && commonAncestor.val > q.val) {
                commonAncestor = commonAncestor.left;
            } else if (commonAncestor.val < p.val && commonAncestor.val < q.val) {
                commonAncestor = commonAncestor.right;
            } else {
                break;
            }
        }
        return commonAncestor;
    }
}
