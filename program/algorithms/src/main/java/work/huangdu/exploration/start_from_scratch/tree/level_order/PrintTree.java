package work.huangdu.exploration.start_from_scratch.tree.level_order;

import work.huangdu.data_structure.TreeNode;

import java.util.*;

/**
 * 655. 输出二叉树
 * 在一个 m*n 的二维字符串数组中输出二叉树，并遵守以下规则：
 * 行数 m 应当等于给定二叉树的高度。
 * 列数 n 应当总是奇数。
 * 根节点的值（以字符串格式给出）应当放在可放置的第一行正中间。根节点所在的行与列会将剩余空间划分为两部分（左下部分和右下部分）。你应该将左子树输出在左下部分，右子树输出在右下部分。左下和右下部分应当有相同的大小。即使一个子树为空而另一个非空，你不需要为空的子树输出任何东西，但仍需要为另一个子树留出足够的空间。然而，如果两个子树都为空则不需要为它们留出任何空间。
 * 每个未使用的空间应包含一个空的字符串""。
 * 使用相同的规则输出子树。
 * 示例 1:
 * 输入:
 * *      1
 * *     /
 * *    2
 * 输出:
 * [["", "1", ""],
 * ["2", "", ""]]
 * 示例 2:
 * 输入:
 * *      1
 * *     / \
 * *    2   3
 * *     \
 * *      4
 * 输出:
 * [["", "", "", "1", "", "", ""],
 * ["", "2", "", "", "", "3", ""],
 * ["", "", "4", "", "", "", ""]]
 * 示例 3:
 * 输入:
 * *       1
 * *      / \
 * *    2   5
 * *    /
 * *   3
 * *  /
 * * 4
 * 输出:
 * [["",  "",  "", "",  "", "", "", "1", "",  "",  "",  "",  "", "", ""]
 * ["",  "",  "", "2", "", "", "", "",  "",  "",  "",  "5", "", "", ""]
 * ["",  "3", "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]
 * ["4", "",  "", "",  "", "", "", "",  "",  "",  "",  "",  "", "", ""]]
 * 注意: 二叉树的高度在范围 [1, 10] 中。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/28
 */
public class PrintTree {
    public List<List<String>> printTree(TreeNode root) {
        int depth = depth(root), len = (1 << depth) - 1, level = 0;
        List<List<String>> result = generate(depth, len);
        Queue<TreeNode> nodeQueue = new ArrayDeque<>();
        Queue<int[]> rangeQueue = new ArrayDeque<>();
        nodeQueue.offer(root);
        // 左闭右开
        rangeQueue.offer(new int[]{0, len});
        while (!nodeQueue.isEmpty()) {
            int size = nodeQueue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = nodeQueue.remove();
                int[] range = rangeQueue.remove();
                int mid = (range[0] + range[1]) / 2;
                result.get(level).set(mid, Integer.toString(node.val));
                if (node.left != null) {
                    nodeQueue.offer(node.left);
                    rangeQueue.offer(new int[]{range[0], mid});
                }
                if (node.right != null) {
                    nodeQueue.offer(node.right);
                    rangeQueue.offer(new int[]{mid + 1, range[1]});
                }
            }
            level++;
        }
        return result;
    }

    private int depth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(depth(root.left), depth(root.right)) + 1;
    }

    private List<List<String>> generate(int depth, int len) {
        List<List<String>> result = new ArrayList<>(depth);
        for (int i = 0; i < depth; i++) {
            List<String> level = new ArrayList<>(len);
            for (int j = 0; j < len; j++) {
                level.add("");
            }
            result.add(level);
        }
        return result;
    }
}
