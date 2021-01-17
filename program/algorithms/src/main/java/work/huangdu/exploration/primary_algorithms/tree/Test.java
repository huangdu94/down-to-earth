package work.huangdu.exploration.primary_algorithms.tree;

import work.huangdu.data_structure.TreeNode;

/**
 * 测试类
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/6/22 17:02
 */
public class Test {
    public static TreeNode generateTree(int[] input) {
        return null;
    }

    public static void main(String[] args) {
        //TreeNode root = new TreeNode(1);
        //root.left = new TreeNode(1);
        //new ValidBST().isValidBST(root);

//        TreeNode root = new TreeNode(1);
//        TreeNode level11 = new TreeNode(2);
//        TreeNode level12 = new TreeNode(2);
//        TreeNode level21 = new TreeNode(3);
//        TreeNode level22 = new TreeNode(4);
//        TreeNode level23 = new TreeNode(4);
//        TreeNode level24 = new TreeNode(3);
//        root.left = level11;
//        root.right = level12;
//        level11.left = level21;
//        level11.right = level22;
//        level12.left = level23;
//        level12.right = level24;
//        new Symmetric().isSymmetric(root);

        new SortedArrayToBST().sortedArrayToBST(new int[]{1, 2, 3});
    }
}