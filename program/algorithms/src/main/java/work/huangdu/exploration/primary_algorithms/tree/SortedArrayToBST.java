package work.huangdu.exploration.primary_algorithms.tree;

import work.huangdu.data_structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 将一个按照升序排列的有序数组，转换为一棵高度平衡二叉搜索树。
 * 本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。
 * 示例:
 * 给定有序数组: [-10,-3,0,5,9],
 * 一个可能的答案是：[0,-3,9,-10,null,5]，它可以表示下面这个高度平衡二叉搜索树：
 * 0
 * / \
 * -3   9
 * /   /
 * -10  5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/6/23 10:20
 */
public class SortedArrayToBST {
    private int[] nums;
    private int index = 0;

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0)
            return null;
        this.nums = nums;
        TreeNode root = createTree(nums.length);
        setValue(root);
        return root;
    }

    /**
     * 按照从第一层至最后一层的顺序生成数直到满足数量要求
     *
     * @param count 树的节点数量
     * @return 树的root节点
     */
    private TreeNode createTree(int count) {
        TreeNode root = new TreeNode(0);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        count--;
        while (count > 0) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode father = queue.remove();
                TreeNode left = new TreeNode(0);
                father.left = left;
                count--;
                if (count <= 0)
                    break;
                TreeNode right = new TreeNode(0);
                father.right = right;
                count--;
                if (count <= 0)
                    break;
                queue.offer(left);
                queue.offer(right);
            }
        }
        return root;
    }

    /**
     * 中序遍历赋值
     *
     * @param node 当前节点
     */
    private void setValue(TreeNode node) {
        if (node == null)
            return;
        setValue(node.left);
        node.val = nums[index++];
        setValue(node.right);
    }
}
