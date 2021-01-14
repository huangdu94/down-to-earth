package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.TreeNode;

import java.util.HashMap;
import java.util.Map;

/**
 * 437. 路径总和 III
 * 给定一个二叉树，它的每个结点都存放着一个整数值。
 * 找出路径和等于给定数值的路径总数。
 * 路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * 二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。
 * 示例：
 * root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8
 * *       10
 * *      /  \
 * *     5   -3
 * *    / \    \
 * *   3   2   11
 * *  / \   \
 * * 3  -2   1
 * 返回 3。和等于 8 的路径有:
 * 1.  5 -> 3
 * 2.  5 -> 2 -> 1
 * 3.  -3 -> 11
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/26 10:59
 * @see work.huangdu.exploration.start_from_scratch.tree.level_order.HasPathSum
 * @see work.huangdu.exploration.start_from_scratch.tree.level_order.PathSum2
 */
public class PathSum3 {
//    private int count = 0;
//    private int sum;
//
//    public int pathSum(TreeNode root, int sum) {
//        this.sum = sum;
//        preorder(root);
//        return count;
//    }
//
//    private void preorder(TreeNode root) {
//        if (root == null) return;
//        getPath(root);
//        preorder(root.left);
//        preorder(root.right);
//    }
//
//    private void getPath(TreeNode node) {
//        Queue<TreeNode> treeNodeQueue = new ArrayDeque<>();
//        Queue<Integer> sumQueue = new ArrayDeque<>();
//        treeNodeQueue.offer(node);
//        sumQueue.offer(0);
//        while (!treeNodeQueue.isEmpty()) {
//            TreeNode cur = treeNodeQueue.remove();
//            int s = sumQueue.remove() + cur.val;
//            if (s == sum) {
//                count++;
//            }
//            if (cur.left != null) {
//                treeNodeQueue.offer(cur.left);
//                sumQueue.offer(s);
//            }
//            if (cur.right != null) {
//                treeNodeQueue.offer(cur.right);
//                sumQueue.offer(s);
//            }
//        }
//    }

    private Map<Integer, Integer> sumMap;
    private int count;

    public int pathSum(TreeNode root, int sum) {
        // key:从根节点开始到当前节点的前缀和 value:前缀和对应的路径数量
        sumMap = new HashMap<>();
        // 前缀和为0，表示从根开始
        sumMap.put(0, 1);
        count = 0;
        dfs(root, 0, sum);
        return count;
    }

    private void dfs(TreeNode node, int sum, int target) {
        if (node == null) return;
        sum += node.val;
        count += sumMap.getOrDefault(sum - target, 0);
        sumMap.merge(sum, 1, Integer::sum);
        dfs(node.left, sum, target);
        dfs(node.right, sum, target);
        // 回溯
        sumMap.merge(sum, -1, Integer::sum);
    }
}
