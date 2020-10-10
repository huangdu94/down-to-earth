package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 145. 二叉树的后序遍历
 * 给定一个二叉树，返回它的 后序 遍历。
 * 示例:
 * 输入: [1,null,2,3]
 * *    1
 * *     \
 * *      2
 * *     /
 * *    3
 * 输出: [3,2,1]
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/29 9:54
 */
public class PostorderTraversal {
    private final List<Integer> res = new ArrayList<>();

    public List<Integer> postorderTraversal3(TreeNode root) {
        postorder(root);
        return res;
    }

    private void postorder(TreeNode node) {
        if (node == null) return;
        postorder(node.left);
        postorder(node.right);
        res.add(node.val);
    }

    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode cur = root, prev = null;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            cur = stack.peek();
            if (cur.right == null || cur.right == prev) {
                prev = stack.pop();
                res.add(prev.val);
                cur = null;
            } else {
                cur = cur.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1, new TreeNode(2), new TreeNode(3));
        System.out.println(new PostorderTraversal().postorderTraversal2(root));
    }
}