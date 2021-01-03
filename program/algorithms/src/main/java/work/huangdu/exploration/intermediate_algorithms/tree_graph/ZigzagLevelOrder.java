package work.huangdu.exploration.intermediate_algorithms.tree_graph;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 103.二叉树的锯齿形层次遍历
 * 给定一个二叉树，返回其节点值的锯齿形层次遍历。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 * 例如：
 * 给定二叉树 [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回锯齿形层次遍历如下：
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/5 19:30
 */
public class ZigzagLevelOrder {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> orderList = new ArrayList<>();
        if (root == null)
            return orderList;
        Stack<TreeNode> preStack = new Stack<>();
        preStack.push(root);
        Stack<TreeNode> curStack;
        TreeNode cur;
        List<Integer> levelList;
        boolean flag = true;
        while (!preStack.empty()) {
            curStack = new Stack<>();
            levelList = new ArrayList<>();
            while (!preStack.empty()) {
                cur = preStack.pop();
                if (cur != null) {
                    levelList.add(cur.val);
                    if (flag) {
                        curStack.push(cur.left);
                        curStack.push(cur.right);
                    } else {
                        curStack.push(cur.right);
                        curStack.push(cur.left);
                    }
                }
            }
            if (!levelList.isEmpty())
                orderList.add(levelList);
            preStack = curStack;
            flag = !flag;
        }
        return orderList;
    }
}
