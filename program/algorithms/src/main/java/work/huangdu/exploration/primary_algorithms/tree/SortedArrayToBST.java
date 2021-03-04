package work.huangdu.exploration.primary_algorithms.tree;

import work.huangdu.data_structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 108. 将有序数组转换为二叉搜索树
 * 给你一个整数数组 nums ，其中元素已经按 升序 排列，请你将其转换为一棵 高度平衡 二叉搜索树。
 * 高度平衡 二叉树是一棵满足「每个节点的左右两个子树的高度差的绝对值不超过 1 」的二叉树。
 * 示例 1：
 * 输入：nums = [-10,-3,0,5,9]
 * 输出：[0,-3,9,-10,null,5]
 * 解释：[0,-10,5,null,-3,null,9] 也将被视为正确答案：
 * 示例 2：
 * 输入：nums = [1,3]
 * 输出：[3,1]
 * 解释：[1,3] 和 [3,1] 都是高度平衡二叉搜索树。
 * 提示：
 * 1 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * nums 按 严格递增 顺序排列
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

    public TreeNode sortedArrayToBST2(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    private TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) return null;
        if (left == right) return new TreeNode(nums[left]);
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }
}
