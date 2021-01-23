package work.huangdu.exploration.start_from_scratch.tree.level_order;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 653. 两数之和 IV - 输入 BST
 * 给定一个二叉搜索树和一个目标结果，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 * 案例 1:
 * 输入:
 * *     5
 * *    / \
 * *   3   6
 * *  / \   \
 * * 2   4   7
 * Target = 9
 * 输出: True
 * 案例 2:
 * 输入:
 * *     5
 * *    / \
 * *   3   6
 * *  / \   \
 * * 2   4   7
 * Target = 28
 * 输出: False
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/23
 */
public class FindTarget {
    private List<Integer> inorderList;

    public boolean findTarget(TreeNode root, int k) {
        inorderList = new ArrayList<>();
        inorder(root);
        int n = inorderList.size(), i = 0, j = n - 1;
        while (i < j) {
            int sum = inorderList.get(i) + inorderList.get(j);
            if (sum == k) {
                return true;
            } else if (sum < k) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }

    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        inorderList.add(root.val);
        inorder(root.right);
    }
}
