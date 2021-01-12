package work.huangdu.question_bank.easy;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 637. 二叉树的层平均值
 * 给定一个非空二叉树, 返回一个由每层节点平均值组成的数组。
 * 示例 1：
 * 输入：
 * *     3
 * *    / \
 * *   9  20
 * *     /  \
 * *    15   7
 * 输出：[3, 14.5, 11]
 * 解释：
 * 第 0 层的平均值是 3 ,  第1层是 14.5 , 第2层是 11 。因此返回 [3, 14.5, 11] 。
 * 提示：
 * 节点值的范围在32位有符号整数范围内。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/12 0:06
 */
public class AverageOfLevels {
    public List<Double> averageOfLevels(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Double> res = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                sum += cur.val;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            res.add(sum / size);
        }
        return res;
    }
}
