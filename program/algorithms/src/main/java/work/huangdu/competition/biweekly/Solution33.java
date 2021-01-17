package work.huangdu.competition.biweekly;

import java.util.ArrayList;
import java.util.List;

/**
 * 第 33 场双周赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/8/13 13:45
 */
public class Solution33 {
    /**
     * 5479. 千位分隔数 题目难度Easy
     * 给你一个整数 n，请你每隔三位添加点（即 "." 符号）作为千位分隔符，并将结果以字符串格式返回。
     * 示例 1：
     * 输入：n = 987
     * 输出："987"
     * 示例 2：
     * 输入：n = 1234
     * 输出："1.234"
     * 示例 3：
     * 输入：n = 123456789
     * 输出："123.456.789"
     * 示例 4：
     * 输入：n = 0
     * 输出："0"
     * 提示：
     * 0 <= n < 2^31
     */
    public String thousandSeparator(int n) {
        StringBuilder sb = new StringBuilder();
        sb.append(n);
        for (int i = sb.length() - 1, j = 1; i > 0; i--, j++) {
            if (j % 3 == 0)
                sb.insert(i, '.');
        }
        return sb.toString();
    }

    /**
     * 5480. 可以到达所有点的最少点数目 题目难度Medium
     * 给你一个 有向无环图 ， n 个节点编号为 0 到 n-1 ，以及一个边数组 edges ，其中 edges[i] = [fromi, toi] 表示一条从点  fromi 到点 toi 的有向边。
     * 找到最小的点集使得从这些点出发能到达图中所有点。题目保证解存在且唯一。
     * 你可以以任意顺序返回这些节点编号。
     * 示例 1：
     * 输入：n = 6, edges = [[0,1],[0,2],[2,5],[3,4],[4,2]]
     * 输出：[0,3]
     * 解释：从单个节点出发无法到达所有节点。从 0 出发我们可以到达 [0,1,2,5] 。从 3 出发我们可以到达 [3,4,2,5] 。所以我们输出 [0,3] 。
     * 示例 2：
     * 输入：n = 5, edges = [[0,1],[2,1],[3,1],[1,4],[2,4]]
     * 输出：[0,2,3]
     * 解释：注意到节点 0，3 和 2 无法从其他节点到达，所以我们必须将它们包含在结果点集中，这些点都能到达节点 1 和 4 。
     * 提示：
     * 2 <= n <= 10^5
     * 1 <= edges.length <= min(10^5, n * (n - 1) / 2)
     * edges[i].length == 2
     * 0 <= from^i, to^i < n
     * 所有点对 (from^i, to^i) 互不相同。
     */
    public List<Integer> findSmallestSetOfVertices(int n, List<List<Integer>> edges) {
        int[] inDegrees = new int[n];
        for (List<Integer> edge : edges) {
            inDegrees[edge.get(1)]++;
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (inDegrees[i] == 0) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 5481. 得到目标数组的最少函数调用次数 题目难度Medium
     * 给你一个与 nums 大小相同且初始值全为 0 的数组 arr ，请你调用以上函数得到整数数组 nums 。
     * 请你返回将 arr 变成 nums 的最少函数调用次数。
     * 答案保证在 32 位有符号整数以内。
     * 示例 1：
     * 输入：nums = [1,5]
     * 输出：5
     * 解释：给第二个数加 1 ：[0, 0] 变成 [0, 1] （1 次操作）。
     * 将所有数字乘以 2 ：[0, 1] -> [0, 2] -> [0, 4] （2 次操作）。
     * 给两个数字都加 1 ：[0, 4] -> [1, 4] -> [1, 5] （2 次操作）。
     * 总操作次数为：1 + 2 + 2 = 5 。
     * 示例 2：
     * 输入：nums = [2,2]
     * 输出：3
     * 解释：给两个数字都加 1 ：[0, 0] -> [0, 1] -> [1, 1] （2 次操作）。
     * 将所有数字乘以 2 ： [1, 1] -> [2, 2] （1 次操作）。
     * 总操作次数为： 2 + 1 = 3 。
     * 示例 3：
     * 输入：nums = [4,2,5]
     * 输出：6
     * 解释：（初始）[0,0,0] -> [1,0,0] -> [1,0,1] -> [2,0,2] -> [2,1,2] -> [4,2,4] -> [4,2,5] （nums 数组）。
     * 示例 4：
     * 输入：nums = [3,2,2,4]
     * 输出：7
     * 示例 5：
     * 输入：nums = [2,4,8,16]
     * 输出：8
     * 提示：
     * 1 <= nums.length <= 10^5
     * 0 <= nums[i] <= 10^9
     */
    public int minOperations(int[] nums) {
        int addCount = 0;
        int maxMulCount = 0;
        for (int n : nums) {
            int mulCount = 0;
            while (n > 0) {
                if ((n & 1) == 1) {
                    n -= 1;
                    addCount++;
                } else {
                    n >>= 1;
                    mulCount++;
                }
            }
            if (mulCount > maxMulCount) {
                maxMulCount = mulCount;
            }
        }
        return maxMulCount + addCount;
    }

    /**
     * 5482. 二维网格图中探测环 题目难度Hard
     * 给你一个二维字符网格数组 grid ，大小为 m x n ，你需要检查 grid 中是否存在 相同值 形成的环。
     * 一个环是一条开始和结束于同一个格子的长度 大于等于 4 的路径。对于一个给定的格子，你可以移动到它上、下、左、右四个方向相邻的格子之一，可以移动的前提是这两个格子有 相同的值 。
     * 同时，你也不能回到上一次移动时所在的格子。比方说，环  (1, 1) -> (1, 2) -> (1, 1) 是不合法的，因为从 (1, 2) 移动到 (1, 1) 回到了上一次移动时的格子。
     * 如果 grid 中有相同值形成的环，请你返回 true ，否则返回 false 。
     * 示例 1：
     * 输入：grid = [["a","a","a","a"],["a","b","b","a"],["a","b","b","a"],["a","a","a","a"]]
     * 输出：true
     * 解释：如下图所示，有 2 个用不同颜色标出来的环：
     * 示例 2：
     * 输入：grid = [["c","c","c","a"],["c","d","c","c"],["c","c","e","c"],["f","c","c","c"]]
     * 输出：true
     * 解释：如下图所示，只有高亮所示的一个合法环：
     * 示例 3：
     * 输入：grid = [["a","b","b"],["b","z","b"],["b","b","a"]]
     * 输出：false
     * 提示：
     * m == grid.length
     * n == grid[i].length
     * 1 <= m <= 500
     * 1 <= n <= 500
     * grid 只包含小写英文字母。
     */
    public boolean containsCycle(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char target = grid[i][j];
                if (target != '*' && dfs(grid, m, n, i, j, target, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] grid, int m, int n, int i, int j, int target, int stop) {
        if (grid[i][j] == '?') {
            return true;
        }
        grid[i][j] = '?';
        if (stop != 1 && i - 1 >= 0 && (grid[i - 1][j] == target || grid[i - 1][j] == '?')) {
            if (dfs(grid, m, n, i - 1, j, target, 2)) {
                return true;
            }
        }
        if (stop != 2 && i + 1 < m && (grid[i + 1][j] == target || grid[i + 1][j] == '?')) {
            if (dfs(grid, m, n, i + 1, j, target, 1)) {
                return true;
            }
        }
        if (stop != 3 && j - 1 >= 0 && (grid[i][j - 1] == target || grid[i][j - 1] == '?')) {
            if (dfs(grid, m, n, i, j - 1, target, 4)) {
                return true;
            }
        }
        if (stop != 4 && j + 1 < n && (grid[i][j + 1] == target || grid[i][j + 1] == '?')) {
            if (dfs(grid, m, n, i, j + 1, target, 3)) {
                return true;
            }
        }
        grid[i][j] = '*';
        return false;
    }

    public static void main(String[] args) {
        Solution33 solution33 = new Solution33();
        System.out.println(new Solution33().thousandSeparator(999999999));
        char[][] grid = {
                {'a', 'b', 'b'},
                {'b', 'z', 'b'},
                {'b', 'b', 'a'}};
        System.out.println(solution33.containsCycle(grid));
    }
}
