package club.huangdu94.exploration.advanced_algorithms.tree_graph;

import club.huangdu94.exploration.intermediate_algorithms.design.Codec;
import club.huangdu94.data_structure.TreeNode;

/**
 * 二叉树中的最大路径和
 * 给定一个非空二叉树，返回其最大路径和。
 * 本题中，路径被定义为一条从树中任意节点出发，达到任意节点的序列。该路径至少包含一个节点，且不一定经过根节点。
 * 示例 1:
 * 输入: [1,2,3]
 * *       1
 * *      / \
 * *     2   3
 * 输出: 6
 * 示例2:
 * 输入: [-10,9,20,null,null,15,7]
 * *  -10
 * * / \
 * * 9 20
 * *  / \
 * * 15  7
 * 输出: 42
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/9 10:29
 */
public class MaxPathSum {
    /* 递归到叶子节点后回溯
       1. 对于叶子节点，不管是不是负数，先保存一下结果(防止最大的路径和就是负数)
          返回该节点值；
       2. 对于其它节点，如果其子树为负数则直接抛弃（因为结果为负数不会为最大值做什么贡献所以抛弃该子树）
          计算其节点值与其非负子树值之和，保存结果，返回该值
          (注意返回的值只能是该节点值与左右子树种较大的一边之和)
          因为到该节点父节点时两条子树只能保存一条
       3. 直到回溯到根节点返回结果值
     */
    private int res = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        dfs(root);
        return res;
    }

    public int dfs(TreeNode root) {
        int curVal = root.val;
        int leftVal;
        int rightVal;
        if (root.left != null || root.right != null) {
            if (root.right == null) {
                leftVal = dfs(root.left);
                if (leftVal > 0) curVal += leftVal;
            } else if (root.left == null) {
                rightVal = dfs(root.right);
                if (rightVal > 0) curVal += rightVal;
            } else {
                leftVal = dfs(root.left);
                rightVal = dfs(root.right);
                if (leftVal > 0 && rightVal > 0) {
                    int sum = curVal + leftVal + rightVal;
                    if (res < sum) res = sum;
                    if (leftVal > rightVal) return leftVal + curVal;
                    else return rightVal + curVal;
                }
                if (leftVal > 0) curVal += leftVal;
                if (rightVal > 0) curVal += rightVal;
            }
        }
        if (res < curVal) res = curVal;
        return curVal;
    }

    public static void main(String[] args) {
        Codec codec = new Codec();
        String str = "[5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1]";
        TreeNode root = codec.deserialize(str);
        MaxPathSum maxPathSum = new MaxPathSum();
        int res = maxPathSum.maxPathSum(root);
        System.out.println(res);
    }
}
