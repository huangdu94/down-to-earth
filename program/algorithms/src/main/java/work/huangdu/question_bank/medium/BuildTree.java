package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 中序遍历 inorder = [9,3,15,20,7]
 * 后序遍历 postorder = [9,15,7,20,3]
 * 返回如下的二叉树：
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/25 16:02
 * @see work.huangdu.exploration.intermediate_algorithms.tree_graph.BuildTree
 */
public class BuildTree {
    private int postIndex;

    public TreeNode buildTree2(int[] inorder, int[] postorder) {
        int len = inorder.length;
        postIndex = len - 1;
        Map<Integer, Integer> inorderMap = new HashMap<>(len);
        for (int i = 0; i < len; i++) {
            inorderMap.put(inorder[i], i);
        }
        //return buildTree(inorderMap, 0, len - 1, postorder, 0, len - 1);
        return buildTree(inorderMap, postorder, 0, len - 1);
    }

    private TreeNode buildTree(Map<Integer, Integer> inorderMap, int il, int ir, int[] postorder, int pl, int pr) {
        if (il > ir) return null;
        int rootVal = postorder[pr];
        int index = inorderMap.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.left = buildTree(inorderMap, il, index - 1, postorder, pl, pl + index - il - 1);
        root.right = buildTree(inorderMap, index + 1, ir, postorder, pl + index - il, pr - 1);
        return root;
    }

    private TreeNode buildTree(Map<Integer, Integer> inorderMap, int[] postorder, int il, int ir) {
        if (il > ir) return null;
        int rootVal = postorder[postIndex--];
        int index = inorderMap.get(rootVal);
        TreeNode root = new TreeNode(rootVal);
        root.right = buildTree(inorderMap, postorder, index + 1, ir);
        root.left = buildTree(inorderMap, postorder, il, index - 1);
        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0) return null;
        int n = inorder.length, inIndex = n - 1;
        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode root = new TreeNode(postorder[n - 1]);
        stack.push(root);
        for (int i = n - 2; i >= 0; i--) {
            int postVal = postorder[i];
            TreeNode cur = stack.peek();
            if (cur.val != inorder[inIndex]) {
                cur.right = new TreeNode(postVal);
                stack.push(cur.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inIndex]) {
                    cur = stack.pop();
                    inIndex--;
                }
                cur.left = new TreeNode(postVal);
                stack.push(cur.left);
            }
        }
        return root;
    }
}
