package work.huangdu.exploration.intermediate_algorithms.tree_graph;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉搜索树中第K小的元素
 * 给定一个二叉搜索树，编写一个函数 kthSmallest 来查找其中第 k 个最小的元素。
 * 说明：
 * 你可以假设 k 总是有效的，1 ≤ k ≤ 二叉搜索树元素个数。
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 * 2
 * 输出: 1
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 3
 * 进阶：
 * 如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 kthSmallest 函数？
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/7 18:56
 */
public class KthSmallest {
    public int kthSmallest(TreeNode root, int k) {
        List<Integer> inorder = new ArrayList<>();
        TreeNode pre;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                inorder.add(cur.val);
                cur = cur.right;
            } else {
                pre = cur.left;
                while (pre.right != null && pre.right != cur) {
                    pre = pre.right;
                }
                if (pre.right == null) {
                    pre.right = cur;
                    cur = cur.left;
                } else {
                    inorder.add(cur.val);
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        return inorder.get(k - 1);
    }

    public int kthSmallest2(TreeNode root, int k) {
        List<Integer> inorder = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (!stack.empty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.pop();
            inorder.add(cur.val);
            cur = cur.right;
        }
        return inorder.get(k - 1);
    }

    public int kthSmallest3(TreeNode root, int k) {
        List<Integer> inorder = new ArrayList<>();
        this.inorder(root, inorder);
        return inorder.get(k - 1);
    }

    private void inorder(TreeNode node, List<Integer> inorder) {
        if (node == null)
            return;
        this.inorder(node.left, inorder);
        inorder.add(node.val);
        this.inorder(node.right, inorder);
    }

    int k;

    public int kthSmallest4(TreeNode root, int k) {
        this.k = k;
        return this.inorder(root);
    }

    private Integer inorder(TreeNode node) {
        if (node == null)
            return null;
        Integer left = this.inorder(node.left);
        if (left != null)
            return left;
        if (--k == 0)
            return node.val;
        return this.inorder(node.right);
    }
}
