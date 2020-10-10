package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 111. 二叉树的最小深度
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明: 叶子节点是指没有子节点的节点。
 * 示例:
 * 给定二叉树 [3,9,20,null,null,15,7],
 * *    3
 * *   / \
 * *  9  20
 * *    /  \
 * *   15   7
 * 返回它的最小深度  2.
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/8 22:55
 */
public class MinDepth {

    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        int res = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            res++;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                if (cur.left == null && cur.right == null) return res;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
        }
        return -1;
    }

    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        int left = this.minDepth(root.left);
        int right = this.minDepth(root.right);
        if (left == 0 && right == 0) return 1;
        if (right == 0) return left + 1;
        if (left == 0) return right + 1;
        return Math.min(left, right) + 1;
    }

    /*public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null)
            return 1;
        int min = -1;
        if (root.left != null) min = this.minDepth(root.left);
        if (root.right != null)
            if (min == -1) min = this.minDepth(root.right);
            else min = Math.min(min, this.minDepth(root.right));
        return 1 + min;
    }*/

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        System.out.println(new MinDepth().minDepth(root));
    }
}
