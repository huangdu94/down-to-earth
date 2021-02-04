package work.huangdu.exploration.start_from_scratch.tree.preorder_serialization;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 652. 寻找重复的子树
 * 给定一棵二叉树，返回所有重复的子树。对于同一类的重复子树，你只需要返回其中任意一棵的根结点即可。
 * 两棵树重复是指它们具有相同的结构以及相同的结点值。
 * 652. Find Duplicate Subtrees
 * Given the root of a binary tree, return all duplicate subtrees.
 * For each kind of duplicate subtrees, you only need to return the root node of any one of them.
 * Two trees are duplicate if they have the same structure with the same node values.
 * 示例 1：
 * *         1
 * *        / \
 * *       2   3
 * *      /   / \
 * *     4   2   4
 * *        /
 * *       4
 * 下面是两个重复的子树：
 * *       2
 * *      /
 * *     4
 * 和
 * *   4
 * 因此，你需要以列表的形式返回上述重复子树的根结点。
 * Constraints:
 * The number of the nodes in the tree will be in the range [1, 10^4]
 * -200 <= Node.val <= 200
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/4
 */
public class FindDuplicateSubtrees {
    // 暴力法(超出容量未通过)
/*    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> visited = new ArrayList<>(), result = new ArrayList<>();
        preorder(root, visited, result);
        return result;
    }

    private void preorder(TreeNode curNode, List<TreeNode> visited, List<TreeNode> result) {
        if (curNode == null) return;
        for (TreeNode resultNode : result) {
            if (isSame(curNode, resultNode)) {
                return;
            }
        }
        boolean flag = true;
        for (TreeNode visitedNode : visited) {
            if (isSame(curNode, visitedNode)) {
                result.add(curNode);
                flag = false;
                break;
            }
        }
        if (flag) {
            visited.add(curNode);
        }
        preorder(curNode.left, visited, result);
        preorder(curNode.right, visited, result);
    }

    private boolean isSame(TreeNode root1, TreeNode root2) {
        return root1 == null && root2 == null ||
                root1 != null && root2 != null
                        && root1.val == root2.val && isSame(root1.left, root2.left) && isSame(root1.right, root2.right);
    }*/

    public List<TreeNode> findDuplicateSubtrees2(TreeNode root) {
        Map<String, Integer> counts = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();
        preorder(root, counts, result);
        return result;
    }

    public void preorder(TreeNode root, Map<String, Integer> counts, List<TreeNode> result) {
        if (root == null) return;
        String serialization = serialize(root);
        if (counts.merge(serialization, 1, Integer::sum) == 2) {
            result.add(root);
        }
        preorder(root.left, counts, result);
        preorder(root.right, counts, result);
    }

    private String serialize(TreeNode node) {
        if (node == null) return "#";
        return Integer.toString(node.val).concat(",").concat(serialize(node.left)).concat(",").concat(serialize(node.right));
    }

    private int id = 1;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> ids = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();
        List<TreeNode> result = new ArrayList<>();
        preorder(root, ids, counts, result);
        return result;
    }

    public int preorder(TreeNode root, Map<String, Integer> ids, Map<Integer, Integer> counts, List<TreeNode> result) {
        if (root == null) return 0;
        String serialization = Integer.toString(root.val).concat(",").concat(Integer.toString(preorder(root.left, ids, counts, result)))
                .concat(",").concat(Integer.toString(preorder(root.right, ids, counts, result)));
        int id = ids.computeIfAbsent(serialization, x -> this.id++);
        if (counts.merge(id, 1, Integer::sum) == 2) {
            result.add(root);
        }
        return id;
    }
}
