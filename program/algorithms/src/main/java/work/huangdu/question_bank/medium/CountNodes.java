package work.huangdu.question_bank.medium;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 222. 完全二叉树的节点个数
 * 给出一个完全二叉树，求出该树的节点个数。
 * 说明：
 * 完全二叉树的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2h 个节点。
 * 示例:
 * 输入:
 * *    1
 * *   / \
 * *  2   3
 * * / \  /
 * *4  5 6
 * 输出: 6
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/11/24 12:10
 */
public class CountNodes {
    // 最优解 先看看最深的一层是多少层，那么根据最深的一层是多少层可以得到节点数量可能值的范围
    // 用二分法，判断对应序号的节点存不存在
    // 判断方法。 根节点为1。2的二进制就是10，就表示从根节点后往左走，就看看root.left存不存在就知道了，以此类推
    public int countNodes2(TreeNode root) {
        if (root == null) return 0;
        int count = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count++;
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return count;
    }

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }
}
