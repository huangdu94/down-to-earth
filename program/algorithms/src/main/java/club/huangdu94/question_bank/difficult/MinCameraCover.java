package club.huangdu94.question_bank.difficult;

import club.huangdu94.data_structure.TreeNode;
import club.huangdu94.exploration.intermediate_algorithms.design.Codec;

/**
 * 968. 监控二叉树
 * 给定一个二叉树，我们在树的节点上安装摄像头。
 * 节点上的每个摄影头都可以监视其父对象、自身及其直接子对象。
 * 计算监控树的所有节点所需的最小摄像头数量。
 * 示例 1：
 * 输入：[0,0,null,0,0]
 * 输出：1
 * 解释：如图所示，一台摄像头足以监控所有节点。
 * 示例 2：
 * 输入：[0,0,null,0,null,0,null,null,0]
 * 输出：2
 * 解释：需要至少两个摄像头来监视树的所有节点。 上图显示了摄像头放置的有效位置之一。
 * 提示：
 * 给定树的节点数的范围是 [1, 1000]。
 * 每个节点的值都是 0。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/22 9:56
 */
public class MinCameraCover {
    public int minCameraCover(TreeNode root) {
        return dfs(root)[1];
    }

    private int[] dfs(TreeNode node) {
        if (node == null) {
            return new int[]{1001, 0, 0};
        }
        int[] left = dfs(node.left);
        int[] right = dfs(node.right);
        int[] res = new int[3];
        res[0] = 1 + left[2] + right[2];
        res[1] = Math.min(res[0], Math.min(left[0] + right[1], left[1] + right[0]));
        res[2] = Math.min(res[0], left[1] + right[1]);
        return res;
    }
/*
    private int count = 0;

    // 1 表示设置了监控 -1 表示该位置已被监控
    public int minCameraCover(TreeNode root) {
        if (root == null) return 0;
        if (isLeafNode(root)) return 1;
        while (root.val == 0 && !isLeafNode(root)) {
            dfs(root);
        }
        if (root.val == 0 && isLeafNode(root)) {
            count++;
        }
        return count;
    }

    private void dfs(TreeNode root) {
        if (root.left != null && root.left.val != -1) {
            if (root.left.val == 1) {
                root.val = -1;
            } else if (isLeafNode(root.left)) {
                if (root.val != 1) {
                    count++;
                    root.val = 1;
                }
                if (root.left.val == 0) {
                    root.left.val = -1;
                }
            } else {
                while (root.left.val == 0 && !isLeafNode(root.left)) {
                    dfs(root.left);
                }
            }
        }
        if (root.right != null && root.right.val != -1) {
            if (root.right.val == 1) {
                root.val = -1;
            } else if (isLeafNode(root.right)) {
                if (root.val != 1) {
                    count++;
                    root.val = 1;
                }
                if (root.right.val == 0) {
                    root.right.val = -1;
                }
            } else {
                while (root.right.val == 0 && !isLeafNode(root.right)) {
                    dfs(root.right);
                }
            }
        }
    }

    private boolean isLeafNode(TreeNode node) {
        return (node.left == null || node.left.val == -1) &&
                (node.right == null || node.right.val == -1);
    }
*/

    public static void main(String[] args) {
        // TreeNode root = new TreeNode(0, new TreeNode(0, new TreeNode(0), new TreeNode(0)), null);
        // TreeNode root = new TreeNode(0, null, new TreeNode(0, null, new TreeNode(0, null, new TreeNode(0))));
        // TreeNode root = new TreeNode(0, new TreeNode(0, null, new TreeNode(0, new TreeNode(0, null, new TreeNode(0, new TreeNode(0), null)), null)), new TreeNode(0));
        TreeNode root = new TreeNode(0, new TreeNode(0, new TreeNode(0, new TreeNode(0, null, new TreeNode(0, null, new TreeNode(0, new TreeNode(0), null))), null), new TreeNode(0)), null);
        Codec codec = new Codec();
        String treeStr = "[0,0,null,0,0,0,null,null,0,0,0,null,null,0,null,0,null,null,null,0,0,null,null,null,0,0]";
        MinCameraCover cover = new MinCameraCover();
        System.out.println(cover.minCameraCover(codec.deserialize(treeStr)));
    }
}
