package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 501. 二叉搜索树中的众数
 * 给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。
 * 假定 BST 有如下定义：
 * 结点左子树中所含结点的值小于等于当前结点的值
 * 结点右子树中所含结点的值大于等于当前结点的值
 * 左子树和右子树都是二叉搜索树
 * 例如：
 * 给定 BST [1,null,2,2],
 * *    1
 * *     \
 * *      2
 * *     /
 * *    2
 * 返回[2].
 * 提示：如果众数超过1个，不需考虑输出顺序
 * 进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/24 13:04
 */
public class FindMode {
    private List<Integer> numList;
    private int pre;
    private int count;
    private int maxCount;

    public int[] findMode2(TreeNode root) {
        if (root == null) return new int[0];
        this.numList = new ArrayList<>();
        this.pre = -1;
        this.count = 0;
        this.maxCount = 0;
        inorder(root);
        if (count > maxCount) {
            return new int[]{pre};
        } else if (count == maxCount) {
            numList.add(pre);
        }
        int[] res = new int[numList.size()];
        for (int i = 0, len = res.length; i < len; i++) {
            res[i] = numList.get(i);
        }
        return res;
    }

    private void inorder(TreeNode node) {
        if (node == null) return;
        inorder(node.left);
        if (pre != node.val) {
            if (count > maxCount) {
                numList.clear();
                numList.add(pre);
                maxCount = count;
            } else if (count == maxCount) {
                numList.add(pre);
            }
            pre = node.val;
            count = 1;
        } else {
            count++;
        }
        inorder(node.right);
    }

    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        int pre = -1, maxCount = 0, count = 0;
        List<Integer> numList = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            TreeNode prev = cur.left;
            if (prev == null) {
                if (pre != cur.val) {
                    if (count > maxCount) {
                        numList.clear();
                        numList.add(pre);
                        maxCount = count;
                    } else if (count == maxCount) {
                        numList.add(pre);
                    }
                    pre = cur.val;
                    count = 1;
                } else {
                    count++;
                }
                cur = cur.right;
            } else {
                while (prev.right != null && prev.right != cur) {
                    prev = prev.right;
                }
                if (prev.right == null) {
                    prev.right = cur;
                    cur = cur.left;
                } else {
                    if (pre != cur.val) {
                        if (count > maxCount) {
                            numList.clear();
                            numList.add(pre);
                            maxCount = count;
                        } else if (count == maxCount) {
                            numList.add(pre);
                        }
                        pre = cur.val;
                        count = 1;
                    } else {
                        count++;
                    }
                    prev.right = null;
                    cur = cur.right;
                }
            }
        }
        if (count > maxCount) {
            return new int[]{pre};
        } else if (count == maxCount) {
            numList.add(pre);
        }
        int[] res = new int[numList.size()];
        for (int i = 0, len = res.length; i < len; i++) {
            res[i] = numList.get(i);
        }
        return res;
    }

    private void update(int cur) {
        if (cur == pre) {
            count++;
        } else {
            pre = cur;
            count = 1;
        }
        if (count > maxCount) {
            maxCount = count;
            numList.clear();
            numList.add(pre);
        } else if (count == maxCount) {
            numList.add(pre);
        }
    }
}
