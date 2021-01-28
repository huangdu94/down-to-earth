package work.huangdu.exploration.start_from_scratch.tree.level_order;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 662. 二叉树最大宽度
 * 给定一个二叉树，编写一个函数来获取这个树的最大宽度。树的宽度是所有层中的最大宽度。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。
 * 每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。
 * 示例 1:
 * 输入:
 * *            1
 * *          /   \
 * *         3     2
 * *        / \     \
 * *       5   3     9
 * 输出: 4
 * 解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。
 * 示例 2:
 * 输入:
 * *           1
 * *          /
 * *         3
 * *        / \
 * *       5   3
 * 输出: 2
 * 解释: 最大值出现在树的第 3 层，宽度为 2 (5,3)。
 * 示例 3:
 * 输入:
 * *           1
 * *          / \
 * *         3   2
 * *        /
 * *       5
 * 输出: 2
 * 解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。
 * 示例 4:
 * 输入:
 * *           1
 * *          / \
 * *         3   2
 * *        /     \
 * *       5       9
 * *      /         \
 * *     6           7
 * 输出: 8
 * 解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
 * 注意: 答案在32位有符号整数的表示范围内。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/28
 */
public class WidthOfBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        int maxWidth = -1;
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<Integer> indexQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        indexQueue.offer(1);
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size(), width = 1;
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.remove();
                int index = indexQueue.remove();
                if (i == 0) {
                    width -= index;
                }
                if (i == size - 1) {
                    width += index;
                }
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    indexQueue.offer(index << 1);
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    indexQueue.offer((index << 1) + 1);
                }
            }
            if (maxWidth < width) {
                maxWidth = width;
            }
        }
        return maxWidth;
    }
}
