package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 100. 相同的树
 * 给定两个二叉树，编写一个函数来检验它们是否相同。
 * 如果两个树在结构上相同，并且节点具有相同的值，则认为它们是相同的。
 * 示例 1:
 * 输入:       1         1
 * *         / \       / \
 * *        2   3     2   3
 * *       [1,2,3],   [1,2,3]
 * 输出: true
 * 示例 2:
 * 输入:      1          1
 * *         /           \
 * *        2             2
 * *       [1,2],     [1,null,2]
 * 输出: false
 * 示例 3:
 * 输入:       1         1
 * *         / \       / \
 * *        2   1     1   2
 * *       [1,2,1],   [1,1,2]
 * 输出: false
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/7 11:22
 */
public class IsSameTree {
    // 1. 迭代
    public boolean isSameTree2(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()) {
            int size = queue.size() / 2;
            for (int i = 0; i < size; i++) {
                TreeNode p0 = queue.poll();
                TreeNode q0 = queue.poll();
                if (p0 == null && q0 == null)
                    continue;
                if (p0 == null || q0 == null || p0.val != q0.val)
                    return false;
                queue.offer(p0.left);
                queue.offer(q0.left);
                queue.offer(p0.right);
                queue.offer(q0.right);
            }
        }
        return true;
    }

    // 2. 递归
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
            return true;
        if (p == null || q == null || p.val != q.val)
            return false;
        boolean left = isSameTree(p.left, q.left);
        if (!left) return false;
        return isSameTree(p.right, q.right);
    }

    public static void main(String[] args) {
        IsSameTree isSameTree = new IsSameTree();
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);
        System.out.println(isSameTree.isSameTree(p, q));
    }

    public boolean isSameTree3(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        return p.val == q.val && isSameTree3(p.left, q.left) && isSameTree3(p.right, q.right);
    }
}
