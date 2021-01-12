package work.huangdu.exploration.start_from_scratch.tree.recursion;

import work.huangdu.data_structure.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 508. 出现次数最多的子树元素和
 * 给你一个二叉树的根结点，请你找出出现次数最多的子树元素和。一个结点的「子树元素和」定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * 你需要返回出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 * 示例 1：
 * 输入:
 * *   5
 * *  /  \
 * * 2   -3
 * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
 * 示例 2：
 * 输入：
 * *   5
 * *  /  \
 * * 2   -5
 * 返回 [2]，只有 2 出现两次，-5 只出现 1 次。
 * 提示： 假设任意子树元素和均可以用 32 位有符号整数表示。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/12/27 12:56
 */
public class FindFrequentTreeSum {
    /**
     * 子树元素和及其对应数量map
     */
    private final Map<Integer, Integer> sumCountMap = new HashMap<>();
    /**
     * 当前数量最多的子树元素和的数量
     */
    private int max = -1;

    public int[] findFrequentTreeSum(TreeNode root) {
        dfs(root);
        if (sumCountMap.isEmpty()) return new int[0];
        List<Integer> resultList = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : sumCountMap.entrySet()) {
            if (entry.getValue() == max) {
                resultList.add(entry.getKey());
            }
        }
        int n = resultList.size();
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }

    private int dfs(TreeNode node) {
        if (node == null) return 0;
        int left = dfs(node.left);
        int right = dfs(node.right);
        int sum = node.val + left + right;
        int count = sumCountMap.merge(sum, 1, Integer::sum);
        if (max < count) max = count;
        return sum;
    }
}