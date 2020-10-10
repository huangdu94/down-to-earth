package work.huangdu.data_structure;

/**
 * Definition for a binary tree node.
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/12 17:33
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int x) {
        val = x;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
