package work.huangdu.exploration.start_from_scratch.tree.inorder_binary_search_tree;

import work.huangdu.data_structure.TreeNode;

import java.util.*;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * 示例：
 * 输入：3
 * 输出：
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * *    1         3     3      2      1
 * *     \       /     /      / \      \
 * *      3     2     1      1   3      2
 * *     /     /       \                 \
 * *    2     1         2                 3
 * 提示：
 * 0 <= n <= 8
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/22
 */
public class GenerateTrees {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }

    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> roots = new ArrayList<>();
        if (start > end) {
            roots.add(null);
            return roots;
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> lefts = generateTrees(start, i - 1);
            List<TreeNode> rights = generateTrees(i + 1, end);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    roots.add(new TreeNode(i, left, right));
                }
            }
        }
        return roots;
    }
}
