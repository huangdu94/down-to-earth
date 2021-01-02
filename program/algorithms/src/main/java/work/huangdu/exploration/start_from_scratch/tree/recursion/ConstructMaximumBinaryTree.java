package work.huangdu.exploration.start_from_scratch.tree.recursion;

import work.huangdu.data_structure.TreeNode;

/**
 * 654. 最大二叉树
 * 给定一个不含重复元素的整数数组。一个以此数组构建的最大二叉树定义如下：
 * 二叉树的根是数组中的最大元素。
 * 左子树是通过数组中最大值左边部分构造出的最大二叉树。
 * 右子树是通过数组中最大值右边部分构造出的最大二叉树。
 * 通过给定的数组构建最大二叉树，并且输出这个树的根节点。
 * 示例 ：
 * 输入：[3,2,1,6,0,5]
 * 输出：返回下面这棵树的根节点：
 * *       6
 * *     /   \
 * *    3     5
 * *     \    /
 * *      2  0
 * *        \
 * *         1
 * 提示：
 * 给定的数组的大小在 [1, 1000] 之间。
 *
 * @author duhuang@iflytek.com
 * @version 2021/1/2 9:56
 */
public class ConstructMaximumBinaryTree {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length - 1);
    }

    private TreeNode constructMaximumBinaryTree(int[] nums, int left, int right) {
        if (left > right) return null;
        int index = left;
        for (int i = left + 1; i <= right; i++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        return new TreeNode(nums[index],
                constructMaximumBinaryTree(nums, left, index - 1),
                constructMaximumBinaryTree(nums, index + 1, right));
    }
}
