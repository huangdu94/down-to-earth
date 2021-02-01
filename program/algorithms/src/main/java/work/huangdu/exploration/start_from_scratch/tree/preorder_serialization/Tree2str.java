package work.huangdu.exploration.start_from_scratch.tree.preorder_serialization;

import work.huangdu.data_structure.TreeNode;

/**
 * 606. 根据二叉树创建字符串
 * 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 * 示例 1:
 * 输入: 二叉树: [1,2,3,4]
 * *        1
 * *      /   \
 * *     2     3
 * *    /
 * *   4
 * 输出: "1(2(4))(3)"
 * 解释: 原本将是“1(2(4)())(3())”，
 * 在你省略所有不必要的空括号对之后，
 * 它将是“1(2(4))(3)”。
 * 示例 2:
 * 输入: 二叉树: [1,2,3,null,4]
 * *        1
 * *      /   \
 * *     2     3
 * *      \
 * *       4
 * 输出: "1(2()(4))(3)"
 * 解释: 和第一个示例相似，
 * 除了我们不能省略第一个对括号来中断输入和输出之间的一对一映射关系。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/1
 */
public class Tree2str {
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        tree2str(t, sb);
        return sb.toString();
    }

    private void tree2str(TreeNode t, StringBuilder sb) {
        if (t == null) return;
        sb.append(t.val);
        if (t.left != null) {
            sb.append('(');
            tree2str(t.left, sb);
            sb.append(')');
        } else if (t.right != null) {
            sb.append("()");
        }
        if (t.right != null) {
            sb.append('(');
            tree2str(t.right, sb);
            sb.append(')');
        }
    }

    public String tree2str2(TreeNode t) {
        if (t == null) return "";
        StringBuilder sb = new StringBuilder(Integer.toString(t.val));
        String left = tree2str2(t.left);
        String right = tree2str2(t.right);
        if (left.length() != 0) {
            sb.append('(').append(left).append(')');
        } else if (right.length() != 0) {
            sb.append("()");
        }
        if (right.length() != 0) {
            sb.append('(').append(right).append(')');
        }
        return sb.toString();
    }

    public String tree2str3(TreeNode t) {
        if (t == null) return "";
        if (t.left == null && t.right == null) {
            return Integer.toString(t.val);
        }
        if (t.right == null) {
            return Integer.toString(t.val).concat("(").concat(tree2str3(t.left)).concat(")");
        }
        return Integer.toString(t.val).concat("(").concat(tree2str3(t.left)).concat(")(").concat(tree2str3(t.right).concat(")"));
    }
}
