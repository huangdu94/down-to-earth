package work.huangdu.exploration.start_from_scratch.tree.inorder_binary_search_tree;


import work.huangdu.data_structure.TreeNode;

import java.util.*;

/**
 * 95. 不同的二叉搜索树 II
 * 给定一个整数 n，生成所有由 1 ... n 为节点所组成的 二叉搜索树 。
 * 示例：
 * 输入：3
 * 输出：
 * [
 * [1,null,3,2],
 * [3,2,null,1],
 * [3,1,null,null,2],
 * [2,1,3],
 * [1,null,2,null,3]
 * ]
 * 解释：
 * 以上的输出对应以下 5 种不同结构的二叉搜索树：
 * *    1         3     3      2      1
 * *     \       /     /      / \      \
 * *      3     2     1      1   3      2
 * *     /     /       \                 \
 * *    2     1         2                 3
 * 提示：
 * 0 <= n <= 8
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/22
 */
public class GenerateTrees {
    public List<TreeNode> generateTrees(int n) {
        // TODO
        if (n == 1) return Collections.singletonList(new TreeNode(1));
        List<List<Integer>> permutes = permute(n);
        Set<TreeNode> result = new HashSet<>();
        for (List<Integer> permute : permutes) {
            TreeNode root = null, cur = null;
            for (Integer num : permute) {
                if (root == null) {
                    root = new TreeNode(num);
                    cur = root;
                } else {
                    while (true) {
                        if (num > cur.val) {
                            if (cur.right == null) {
                                cur.right = new TreeNode(num);
                                break;
                            }
                            cur = cur.right;
                        } else {
                            if (cur.left == null) {
                                cur.left = new TreeNode(num);
                                break;
                            }
                            cur = cur.left;
                        }
                    }
                }
            }
            result.add(root);
        }
        return new ArrayList<>(result);
    }

    private List<List<Integer>> permute(int n) {
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = i + 1;
        }
        List<List<Integer>> permutes = new ArrayList<>();
        this.backtrack(nums, 0, permutes, new ArrayList<>());
        return permutes;
    }

    private void backtrack(int[] nums, int index, List<List<Integer>> permutes, List<Integer> permute) {
        if (index == nums.length) {
            permutes.add(new ArrayList<>(permute));
            return;
        }
        for (int i = index; i < nums.length; i++) {
            permute.add(nums[i]);
            swap(nums, index, i);
            backtrack(nums, index + 1, permutes, permute);
            permute.remove(index);
            swap(nums, index, i);
        }
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}
