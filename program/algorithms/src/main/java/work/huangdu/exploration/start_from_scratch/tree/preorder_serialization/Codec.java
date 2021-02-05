package work.huangdu.exploration.start_from_scratch.tree.preorder_serialization;

import work.huangdu.data_structure.TreeNode;
import work.huangdu.question_bank.easy.IsSameTree;

/**
 * 449. 序列化和反序列化二叉搜索树
 * 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：[2,1,3]
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 * 提示：
 * 树中节点数范围是 [0, 10^4]
 * 0 <= Node.val <= 10^4
 * 题目数据 保证 输入的树是一棵二叉搜索树。
 * 注意：不要使用类成员/全局/静态变量来存储状态。 你的序列化和反序列化算法应该是无状态的。
 * Your Codec object will be instantiated and called as such:
 * Codec ser = new Codec();
 * Codec deser = new Codec();
 * String tree = ser.serialize(root);
 * TreeNode ans = deser.deserialize(tree);
 * return ans;
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/5
 */
public class Codec {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        while (root != null) {
            if (root.left == null) {
                sb.append("#").append(root.val);
                root = root.right;
            } else {
                TreeNode prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    sb.append("#").append(root.val);
                    prev.right = root;
                    root = root.left;
                } else {
                    prev.right = null;
                    root = root.right;
                }
            }
        }
        return sb.substring(1);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.length() == 0) return null;
        String[] strArr = data.split("#");
        TreeNode root = new TreeNode(Integer.parseInt(strArr[0]));
        for (int i = 1, n = strArr.length; i < n; i++) {
            insert(root, Integer.parseInt(strArr[i]));
        }
        return root;
    }

    private void insert(TreeNode root, int val) {
        while (true) {
            if (val <= root.val) {
                if (root.left == null) {
                    root.left = new TreeNode(val);
                    return;
                }
                root = root.left;
            } else {
                if (root.right == null) {
                    root.right = new TreeNode(val);
                    return;
                }
                root = root.right;
            }
        }
    }

    public static void main(String[] args) {
        Codec ser = new Codec();
        Codec deser = new Codec();
        TreeNode root = new TreeNode(2, new TreeNode(1), new TreeNode(3));
        String tree = ser.serialize(root);
        TreeNode ans = deser.deserialize(tree);
        System.out.println(new IsSameTree().isSameTree(root, ans));
    }
}
