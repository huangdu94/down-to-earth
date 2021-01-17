package work.huangdu.competition.weekly;

import work.huangdu.data_structure.TreeNode;

import java.util.*;

/**
 * 第209场周赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/4 9:51
 */
public class Solution209 {

    /**
     * 5531. 特殊数组的特征值 题目难度Easy
     * 给你一个非负整数数组 nums 。如果存在一个数 x ，使得 nums 中恰好有 x 个元素 大于或者等于 x ，那么就称 nums 是一个 特殊数组 ，而 x 是该数组的 特征值 。
     * 注意： x 不必 是 nums 的中的元素。
     * 如果数组 nums 是一个 特殊数组 ，请返回它的特征值 x 。否则，返回 -1 。可以证明的是，如果 nums 是特殊数组，那么其特征值 x 是 唯一的 。
     * 示例 1：
     * 输入：nums = [3,5]
     * 输出：2
     * 解释：有 2 个元素（3 和 5）大于或等于 2 。
     * 示例 2：
     * 输入：nums = [0,0]
     * 输出：-1
     * 解释：没有满足题目要求的特殊数组，故而也不存在特征值 x 。
     * 如果 x = 0，应该有 0 个元素 >= x，但实际有 2 个。
     * 如果 x = 1，应该有 1 个元素 >= x，但实际有 0 个。
     * 如果 x = 2，应该有 2 个元素 >= x，但实际有 0 个。
     * x 不能取更大的值，因为 nums 中只有两个元素。
     * 示例 3：
     * 输入：nums = [0,4,3,0,4]
     * 输出：3
     * 解释：有 3 个元素大于或等于 3 。
     * 示例 4：
     * 输入：nums = [3,6,7,7,0]
     * 输出：-1
     * 提示：
     * 1 <= nums.length <= 100
     * 0 <= nums[i] <= 1000
     */
    public int specialArray(int[] nums) {
        int n = nums.length, i = 0;
        Arrays.sort(nums);
        while (i < n) {
            int num = nums[i];
            int count = nums.length - i;
            if (num >= count && (i == 0 || nums[i - 1] < count)) {
                return count;
            }
            i++;
            while (i < n && nums[i] == nums[i - 1]) {
                i++;
            }
        }
        return -1;
    }

    /**
     * 5532. 奇偶树
     * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
     * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
     * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
     * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
     * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
     * 示例 1：
     * 输入：root = [1,10,4,3,null,7,9,12,8,6,null,null,2]
     * 输出：true
     * 解释：每一层的节点值分别是：
     * 0 层：[1]
     * 1 层：[10,4]
     * 2 层：[3,7,9]
     * 3 层：[12,8,6,2]
     * 由于 0 层和 2 层上的节点值都是奇数且严格递增，而 1 层和 3 层上的节点值都是偶数且严格递减，因此这是一棵奇偶树。
     * 示例 2：
     * 输入：root = [5,4,2,3,3,7]
     * 输出：false
     * 解释：每一层的节点值分别是：
     * 0 层：[5]
     * 1 层：[4,2]
     * 2 层：[3,3,7]
     * 2 层上的节点值不满足严格递增的条件，所以这不是一棵奇偶树。
     * 示例 3：
     * 输入：root = [5,9,1,3,5,7]
     * 输出：false
     * 解释：1 层上的节点值应为偶数。
     * 示例 4：
     * 输入：root = [1]
     * 输出：true
     * 示例 5：
     * 输入：root = [11,8,6,1,3,9,11,30,20,18,16,12,10,4,2,17]
     * 输出：true
     * 提示：
     * 树中节点数在范围 [1, 105] 内
     * 1 <= Node.val <= 10^6
     */
    public boolean isEvenOddTree(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            TreeNode pre = null;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                if ((level & 1) == 0) {
                    if ((cur.val & 1) == 0 || (pre != null && pre.val >= cur.val)) {
                        return false;
                    }
                } else {
                    if ((cur.val & 1) == 1 || (pre != null && pre.val <= cur.val)) {
                        return false;
                    }
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                pre = cur;
            }
            level++;
        }
        return true;
    }

    public boolean isEvenOddTree2(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            levelOrder.add(new ArrayList<>());
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.remove();
                levelOrder.get(levelOrder.size() - 1).add(cur.val);
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }
        for (int i = 0; i < levelOrder.size(); i++) {
            List<Integer> level = levelOrder.get(i);
            if ((i & 1) == 0) {
                for (int j = 0; j < level.size(); j++) {
                    if ((level.get(j) & 1) == 0 || (j > 0 && level.get(j - 1) >= level.get(j))) {
                        return false;
                    }
                }
            } else {
                for (int j = 0; j < level.size(); j++) {
                    if ((level.get(j) & 1) == 1 || (j > 0 && level.get(j - 1) <= level.get(j))) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * 5534. 可见点的最大数目
     * 给你一个点数组 points 和一个表示角度的整数 angle ，你的位置是 location ，其中 location = [posx, posy] 且 points[i] = [xi, yi] 都表示 X-Y 平面上的整数坐标。
     * 最开始，你面向东方进行观测。你 不能 进行移动改变位置，但可以通过 自转 调整观测角度。换句话说，posx 和 posy 不能改变。你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。设 d 为逆时针旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
     * 对于每个点，如果由该点、你的位置以及从你的位置直接向东的方向形成的角度 位于你的视野中 ，那么你就可以看到它。
     * 同一个坐标上可以有多个点。你所在的位置也可能存在一些点，但不管你的怎么旋转，总是可以看到这些点。同时，点不会阻碍你看到其他点。
     * 返回你能看到的点的最大数目。
     * 示例 1：
     * 输入：points = [[2,1],[2,2],[3,3]], angle = 90, location = [1,1]
     * 输出：3
     * 解释：阴影区域代表你的视野。在你的视野中，所有的点都清晰可见，尽管 [2,2] 和 [3,3]在同一条直线上，你仍然可以看到 [3,3] 。
     * 示例 2：
     * 输入：points = [[2,1],[2,2],[3,4],[1,1]], angle = 90, location = [1,1]
     * 输出：4
     * 解释：在你的视野中，所有的点都清晰可见，包括你所在位置的那个点。
     * 示例 3：
     * 输入：points = [[0,1],[2,1]], angle = 13, location = [1,1]
     * 输出：1
     * 解释：如图所示，你只能看到两点之一。
     * 提示：
     * 1 <= points.length <= 10^5
     * points[i].length == 2
     * location.length == 2
     * 0 <= angle < 360
     * 0 <= pos_x, pos_y, x_i, y_i <= 10^9
     */
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int same = 0;
        List<Double> angleList = new ArrayList<>(points.size() * 2);
        for (List<Integer> point : points) {
            if (point.get(0).equals(location.get(0)) && point.get(1).equals(location.get(1))) {
                same++;
            } else {
                angleList.add(Math.atan2(point.get(1) - location.get(1), point.get(0) - location.get(0)) * 180 / Math.PI);
            }
        }
        Collections.sort(angleList);
        for (int i = 0, len = angleList.size(); i < len; i++) {
            angleList.add(angleList.get(i) + 360);
        }
        int maxCount = 0;
        for (int left = 0, len = angleList.size(), right = left; left < len; left++) {
            double leftAngle = angleList.get(left);
            while (right < len && angleList.get(right) - leftAngle <= angle + 1e-6) {
                right++;
            }
            if (right - left > maxCount) {
                maxCount = right - left;
            }
        }
        return maxCount + same;
    }

    /**
     * 5533. 使整数变为 0 的最少操作次数
     * 给你一个整数 n，你需要重复执行多次下述操作将其转换为 0 ：
     * 翻转 n 的二进制表示中最右侧位（第 0 位）。
     * 如果第 (i-1) 位为 1 且从第 (i-2) 位到第 0 位都为 0，则翻转 n 的二进制表示中的第 i 位。
     * 返回将 n 转换为 0 的最小操作次数。
     * 示例 1：
     * 输入：n = 0
     * 输出：0
     * 示例 2：
     * 输入：n = 3
     * 输出：2
     * 解释：3 的二进制表示为 "11"
     * "11" -> "01" ，执行的是第 2 种操作，因为第 0 位为 1 。
     * "01" -> "00" ，执行的是第 1 种操作。
     * 示例 3：
     * 输入：n = 6
     * 输出：4
     * 解释：6 的二进制表示为 "110".
     * "110" -> "010" ，执行的是第 2 种操作，因为第 1 位为 1 ，第 0 到 0 位为 0 。
     * "010" -> "011" ，执行的是第 1 种操作。
     * "011" -> "001" ，执行的是第 2 种操作，因为第 0 位为 1 。
     * "001" -> "000" ，执行的是第 1 种操作。
     * 示例 4：
     * 输入：n = 9
     * 输出：14
     * 示例 5：
     * 输入：n = 333
     * 输出：393
     * 提示：
     * 0 <= n <= 10^9
     */
    public int minimumOneBitOperations(int n) {
        // 格雷码
        int ans = 0;
        while (n > 0) {
            ans ^= n;
            n >>= 1;
        }
        return ans;
    }

    private int helper(int n, int preOpt) {
        int count = 0;
        while (n != 0) {
            if (count > 10000) return -1;
            if (preOpt == 1) {
                int temp = n;
                if ((n & (n - 1)) != 0) {
                    temp = n & (-n);
                }
                temp <<= 1;
                n ^= temp;
                preOpt = 2;
            } else {
                n ^= 1;
                preOpt = 1;
            }
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        Solution209 solution = new Solution209();
//        int[] nums = {3, 6, 7, 7, 0};
//        System.out.println(solution.specialArray(nums));
//        int n = 333;
//        System.out.println(solution.minimumOneBitOperations(n));
        List<List<Integer>> points = Arrays.asList(Arrays.asList(1000000000, 999999999), Arrays.asList(999999999, 1000000000));
        int angle = 90;
        List<Integer> location = Arrays.asList(0, 0);
        System.out.println(solution.visiblePoints(points, angle, location));
    }
}
