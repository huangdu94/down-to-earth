package club.huangdu94.exploration.intermediate_algorithms.tree_graph;

import club.huangdu94.data_structure.TreeNode;

import java.util.Stack;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * 根据一棵树的前序遍历与中序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 * 例如，给出
 * 前序遍历 preorder = [3,9,20,15,7]
 * 中序遍历 inorder = [9,3,15,20,7]
 * 返回如下的二叉树：
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/5 19:31
 */
public class BuildTree {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null)
            return null;
        return this.buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }

    /**
     * 递归构造树
     *
     * @param preorder 先序遍历序列数组
     * @param preStart 子树所用到的先序遍历序列数组开始节点
     * @param preEnd   子树所用到的先序遍历序列数组结束节点
     * @param inorder  中序遍历序列数组
     * @param inStart  子树所用到的中序遍历序列数组开始节点
     * @param inEnd    子树所用到的中序遍历序列数组结束节点
     * @return 返回子树根节点
     */
    private TreeNode buildTree(int[] preorder, int preStart, int preEnd, int[] inorder, int inStart, int inEnd) {
        // inStart == inEnd
        if (preStart == preEnd)
            return null;
        int rootVal = preorder[preStart];
        int inMiddle;
        for (inMiddle = inStart; inMiddle < inEnd; inMiddle++)
            if (inorder[inMiddle] == rootVal)
                break;
        int preMiddle = preStart + (inMiddle - inStart) + 1;
        TreeNode root = new TreeNode(rootVal);
        root.left = this.buildTree(preorder, preStart + 1, preMiddle, inorder, inStart, inMiddle);
        root.right = this.buildTree(preorder, preMiddle, preEnd, inorder, inMiddle + 1, inEnd);
        return root;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0)
            return null;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = new TreeNode(preorder[0]);
        stack.push(root);
        int inIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preVal = preorder[i];
            TreeNode cur = stack.peek();
            if (inorder[inIndex] != cur.val) {
                cur.left = new TreeNode(preVal);
                stack.push(cur.left);
            } else {
                while (!stack.empty() && stack.peek().val == inorder[inIndex]) {
                    cur = stack.pop();
                    inIndex++;
                }
                cur.right = new TreeNode(preVal);
                stack.push(cur.right);
            }
        }
        return root;
    }
}
