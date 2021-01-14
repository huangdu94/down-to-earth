package work.huangdu.exploration.start_from_scratch.tree.recursion;

import work.huangdu.data_structure.TreeNode;

/**
 * 222. 完全二叉树的节点个数
 * 给出一个完全二叉树，求出该树的节点个数。
 * 说明：
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * 示例:
 * 输入:
 * *     1
 * *    / \
 * *   2   3
 * *  / \  /
 * * 4  5 6
 * 输出: 6
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2021/1/1 18:10
 */
public class CountNodes {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        // 1. 计算层数n层，最后一层序号范围2^(n-1)到2^n - 1
        int n = 1;
        TreeNode node = root.left;
        while (node != null) {
            node = node.left;
            n++;
        }
        // 最后一层的序号范围
        int bottomLeft = 1 << (n - 1), i = bottomLeft, j = (bottomLeft << 1), result = i;
        // 2. 二分法找最后一个存在的节点
        while (i < j) {
            int mid = i + (j - i) / 2;
            if (isExist(bottomLeft, root, mid)) {
                result = i;
                i = mid + 1;
            } else {
                j = mid;
            }
        }
        return result;
    }

    private boolean isExist(int bottomLeft, TreeNode root, int number) {
        int mask = bottomLeft >> 1;
        while (mask > 0 && root != null) {
            if ((number & mask) == 0) {
                root = root.left;
            } else {
                root = root.right;
            }
            mask >>= 1;
        }
        return root != null;
    }
}
