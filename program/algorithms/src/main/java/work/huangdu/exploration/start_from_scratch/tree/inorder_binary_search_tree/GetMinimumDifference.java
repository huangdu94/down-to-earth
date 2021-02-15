package work.huangdu.exploration.start_from_scratch.tree.inorder_binary_search_tree;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 530. 二叉搜索树的最小绝对差
 * 783. 二叉搜索树节点最小距离
 * 给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。
 * 示例：
 * 输入：
 * *    1
 * *     \
 * *      3
 * *     /
 * *    2
 * 输出：
 * 1
 * 解释：
 * 最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
 * 提示：
 * 树中至少有 2 个节点。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/12 15:49
 */
public class GetMinimumDifference {
    public int getMinimumDifference(TreeNode root) {
        int preVal = -1, min = Integer.MAX_VALUE;
        while (root != null) {
            if (root.left == null) {
                if (preVal != -1) {
                    min = Math.min(min, root.val - preVal);
                }
                preVal = root.val;
                root = root.right;
            } else {
                TreeNode prev = root.left;
                while (prev.right != null && prev.right != root) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = root;
                    root = root.left;
                } else {
                    if (preVal != -1) {
                        min = Math.min(min, root.val - preVal);
                    }
                    preVal = root.val;
                    prev.right = null;
                    root = root.right;
                }
            }
        }
        return min;
    }

    private int pre = -1;
    private int min = Integer.MAX_VALUE;

    public int getMinimumDifference3(TreeNode root) {
        inOrder(root);
        return min;
    }

    private void inOrder(TreeNode node) {
        if (node == null) return;
        inOrder(node.left);
        if (pre != -1) {
            int temp = Math.abs(node.val - pre);
            if (temp < min) {
                min = temp;
            }
        }
        pre = node.val;
        inOrder(node.right);
    }

    public int getMinimumDifference2(TreeNode root) {
        int pre = -1, min = Integer.MAX_VALUE;
        TreeNode cur = root;
        Deque<TreeNode> set = new ArrayDeque<>();
        while (!set.isEmpty() || cur != null) {
            while (cur != null) {
                set.push(cur);
                cur = cur.left;
            }
            cur = set.pop();
            if (pre != -1) {
                int temp = Math.abs(cur.val - pre);
                if (temp < min) {
                    min = temp;
                }
            }
            pre = cur.val;
            cur = cur.right;
        }
        return min;
    }

    public int getMinimumDifference4(TreeNode root) {
        int pre = -1, min = Integer.MAX_VALUE;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                if (pre != -1) {
                    int temp = Math.abs(cur.val - pre);
                    if (temp < min) {
                        min = temp;
                    }
                }
                pre = cur.val;
                cur = cur.right;
            } else {
                TreeNode prev = cur.left;
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    if (pre != -1) {
                        int temp = Math.abs(cur.val - pre);
                        if (temp < min) {
                            min = temp;
                        }
                    }
                    pre = cur.val;
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        return min;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(543, new TreeNode(384, null, new TreeNode(445)), new TreeNode(652, null, new TreeNode(699)));
        GetMinimumDifference difference = new GetMinimumDifference();
        System.out.println(difference.getMinimumDifference(root));
    }

}
