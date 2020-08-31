package club.huangdu94.competition.weekly;

/**
 * 第 204 场周赛
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/30 10:19
 */
public class Solution204 {
    /**
     * 5499. 重复至少 K 次且长度为 M 的模式
     * 给你一个正整数数组 arr，请你找出一个长度为 m 且在数组中至少重复 k 次的模式。
     * 模式 是由一个或多个值组成的子数组（连续的子序列），连续 重复多次但 不重叠 。 模式由其长度和重复次数定义。
     * 如果数组中存在至少重复 k 次且长度为 m 的模式，则返回 true ，否则返回  false 。
     * 示例 1：
     * 输入：arr = [1,2,4,4,4,4], m = 1, k = 3
     * 输出：true
     * 解释：模式 (4) 的长度为 1 ，且连续重复 4 次。注意，模式可以重复 k 次或更多次，但不能少于 k 次。
     * 示例 2：
     * 输入：arr = [1,2,1,2,1,1,1,3], m = 2, k = 2
     * 输出：true
     * 解释：模式 (1,2) 长度为 2 ，且连续重复 2 次。另一个符合题意的模式是 (2,1) ，同样重复 2 次。
     * 示例 3：
     * 输入：arr = [1,2,1,2,1,3], m = 2, k = 3
     * 输出：false
     * 解释：模式 (1,2) 长度为 2 ，但是只连续重复 2 次。不存在长度为 2 且至少重复 3 次的模式。
     * 示例 4：
     * 输入：arr = [1,2,3,1,2], m = 2, k = 2
     * 输出：false
     * 解释：模式 (1,2) 出现 2 次但并不连续，所以不能算作连续重复 2 次。
     * 示例 5：
     * 输入：arr = [2,2,2,2], m = 2, k = 3
     * 输出：false
     * 解释：长度为 2 的模式只有 (2,2) ，但是只连续重复 2 次。注意，不能计算重叠的重复次数。
     * 提示：
     * 2 <= arr.length <= 100
     * 1 <= arr[i] <= 100
     * 1 <= m <= 100
     * 2 <= k <= 100
     */
    public boolean containsPattern1(int[] arr, int m, int k) {
        int len = arr.length;
        if (m * k > len) return false;
        for (int start = 0; k * m <= len - start; start++) {
            int end = start + m - 1, _k = k - 1, i = end + 1;
            while (_k * m <= len - i) {
                int j = start;
                int _i = i;
                while (_i < len && j <= end && arr[_i] == arr[j]) {
                    _i++;
                    j++;
                }
                if (j == end + 1) {
                    if (--_k == 0) {
                        return true;
                    }
                    i = _i;
                } else {
                    i++;
                    _k = k;
                }
            }
        }
        return false;
    }

    public boolean containsPattern(int[] arr, int m, int k) {
        for (int i = 0; i <= arr.length - k * m; i++) {
            int j = i + m, end = i + k * m;
            while (j < end && arr[j - m] == arr[j]) {
                j++;
            }
            if (j == end) {
                return true;
            }
        }
        return false;
    }

    /**
     * 5500. 乘积为正数的最长子数组长度
     * 给你一个整数数组 nums ，请你求出乘积为正数的最长子数组的长度。
     * 一个数组的子数组是由原数组中零个或者更多个连续数字组成的数组。
     * 请你返回乘积为正数的最长子数组长度。
     * 示例  1：
     * 输入：nums = [1,-2,-3,4]
     * 输出：4
     * 解释：数组本身乘积就是正数，值为 24 。
     * 示例 2：
     * 输入：nums = [0,1,-2,-3,-4]
     * 输出：3
     * 解释：最长乘积为正数的子数组为 [1,-2,-3] ，乘积为 6 。
     * 注意，我们不能把 0 也包括到子数组中，因为这样乘积为 0 ，不是正数。
     * 示例 3：
     * 输入：nums = [-1,-2,-3,0,1]
     * 输出：2
     * 解释：乘积为正数的最长子数组是 [-1,-2] 或者 [-2,-3] 。
     * 示例 4：
     * 输入：nums = [-1,2]
     * 输出：1
     * 示例 5：
     * 输入：nums = [1,2,3,5,-6,4,0,10]
     * 输出：4
     * 提示：
     * 1 <= nums.length <= 10^5
     * -10^9 <= nums[i] <= 10^9
     */
    public int getMaxLen(int[] nums) {
        int maxLen = 0, noZeroLen = 0, first = -1, last = -1;
        boolean flag = true;
        for (int n : nums) {
            if (n == 0) {
                if (flag) {
                    maxLen = Math.max(maxLen, noZeroLen);
                } else {
                    maxLen = Math.max(maxLen, Math.max(noZeroLen - first, last - 1));
                }
                noZeroLen = 0;
                first = -1;
                last = -1;
                flag = true;
            } else {
                noZeroLen++;
                if (n < 0) {
                    if (first == -1) {
                        first = noZeroLen;
                    }
                    flag = !flag;
                    last = noZeroLen;
                }
            }
        }
        if (flag) {
            maxLen = Math.max(maxLen, noZeroLen);
        } else {
            maxLen = Math.max(maxLen, Math.max(noZeroLen - first, last - 1));
        }
        return maxLen;
    }

    /**
     * 5501. 使陆地分离的最少天数
     * 给你一个由若干 0 和 1 组成的二维网格 grid ，其中 0 表示水，而 1 表示陆地。岛屿由水平方向或竖直方向上相邻的 1 （陆地）连接形成。
     * 如果 恰好只有一座岛屿 ，则认为陆地是 连通的 ；否则，陆地就是 分离的 。
     * 一天内，可以将任何单个陆地单元（1）更改为水单元（0）。
     * 返回使陆地分离的最少天数。
     * 示例 1：
     * 输入：grid = [[0,1,1,0],[0,1,1,0],[0,0,0,0]]
     * 输出：2
     * 解释：至少需要 2 天才能得到分离的陆地。
     * 将陆地 grid[1][1] 和 grid[0][2] 更改为水，得到两个分离的岛屿。
     * 示例 2：
     * 输入：grid = [[1,1]]
     * 输出：2
     * 解释：如果网格中都是水，也认为是分离的 ([[1,1]] -> [[0,0]])，0 岛屿。
     * 示例 3：
     * 输入：grid = [[1,0,1,0]]
     * 输出：0
     * 示例 4：
     * 输入：grid = [[1,1,0,1,1],
     * *            [1,1,1,1,1],
     * *            [1,1,0,1,1],
     * *            [1,1,0,1,1]]
     * 输出：1
     * 示例 5：
     * 输入：grid = [[1,1,0,1,1],
     * *            [1,1,1,1,1],
     * *            [1,1,0,1,1],
     * *            [1,1,1,1,1]]
     * 输出：2
     * 提示：
     * 1 <= grid.length, grid[i].length <= 30
     * grid[i][j] 为 0 或 1
     */
    public int minDays(int[][] grid) {
        int m = grid.length, n = grid[0].length, flag = 1;
        if (separate(grid, m, n, flag)) {
            return 0;
        }
        flag *= -1;
        /*
         1. 如果一个是陆地的格子仅从周围八个格子就判断它不可能分离陆地，那么就无须dfs判断
         2. 如果不能从周围格子判断，则需要dfs
         */
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == flag) {
                    if (maybeSeparate(grid, m, n, i, j, flag)) {
                        grid[i][j] = 0;
                        if (separate(grid, m, n, flag)) {
                            return 1;
                        }
                        grid[i][j] = (flag *= -1);
                    }
                }
            }
        }
        return 2;
    }

    /**
     * 是否有分离的可能性
     */
    private boolean maybeSeparate(int[][] grid, int m, int n, int i, int j, int flag) {
        int count = 0;
        boolean u, d, l, r, ul, ur, dl, dr;
        u = d = l = r = ul = ur = dl = dr = false;
        if ((i - 1 < 0 || j - 1 < 0) || grid[i - 1][j - 1] == 0) {
            ul = true;
        }
        if ((i - 1 < 0 || j + 1 >= n) || grid[i - 1][j + 1] == 0) {
            ur = true;
        }
        if ((i + 1 >= m || j - 1 < 0) || grid[i + 1][j - 1] == 0) {
            dl = true;
        }
        if ((i + 1 >= m || j + 1 >= n) || grid[i + 1][j + 1] == 0) {
            dr = true;
        }
        if (i - 1 >= 0 && grid[i - 1][j] == flag) {
            u = true;
            count++;
        }
        if (i + 1 < m && grid[i + 1][j] == flag) {
            d = true;
            count++;
        }
        if (j - 1 >= 0 && grid[i][j - 1] == flag) {
            l = true;
            count++;
        }
        if (j + 1 < n && grid[i][j + 1] == flag) {
            r = true;
            count++;
        }
        return (count >= 2) && (u && ul && ur || d && dl && dr || l && dl && ul || r && dr && ur);
    }

    /**
     * 判断陆地是否分离，如果全是水则分离，或者两个岛以上是分离
     */
    private boolean separate(int[][] grid, int m, int n, int flag) {
        int count = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == flag) {
                    if (++count == 2) {
                        return true;
                    }
                    dfs(grid, m, n, i, j, flag);
                }
            }
        }
        return count == 0;
    }

    /**
     * dfs flag代表陆地，dfs完成后flag变为-flag
     */
    private void dfs(int[][] grid, int m, int n, int i, int j, int flag) {
        grid[i][j] = -flag;
        if (i - 1 >= 0 && grid[i - 1][j] == flag) {
            dfs(grid, m, n, i - 1, j, flag);
        }
        if (i + 1 < m && grid[i + 1][j] == flag) {
            dfs(grid, m, n, i + 1, j, flag);
        }
        if (j - 1 >= 0 && grid[i][j - 1] == flag) {
            dfs(grid, m, n, i, j - 1, flag);
        }
        if (j + 1 < n && grid[i][j + 1] == flag) {
            dfs(grid, m, n, i, j + 1, flag);
        }
    }

    /**
     * 5502. 将子数组重新排序得到同一个二叉查找树的方案数
     * 给你一个数组 nums 表示 1 到 n 的一个排列。我们按照元素在 nums 中的顺序依次插入一个初始为空的二叉查找树（BST）。请你统计将 nums 重新排序后，统计满足如下条件的方案数：重排后得到的二叉查找树与 nums 原本数字顺序得到的二叉查找树相同。
     * 比方说，给你 nums = [2,1,3]，我们得到一棵 2 为根，1 为左孩子，3 为右孩子的树。数组 [2,3,1] 也能得到相同的 BST，但 [3,2,1] 会得到一棵不同的 BST 。
     * 请你返回重排 nums 后，与原数组 nums 得到相同二叉查找树的方案数。
     * 由于答案可能会很大，请将结果对 10^9 + 7 取余数。
     * 示例 1：
     * 输入：nums = [2,1,3]
     * 输出：1
     * 解释：我们将 nums 重排， [2,3,1] 能得到相同的 BST 。没有其他得到相同 BST 的方案了。
     * 示例 2：
     * 输入：nums = [3,4,5,1,2]
     * 输出：5
     * 解释：下面 5 个数组会得到相同的 BST：
     * [3,1,2,4,5]
     * [3,1,4,2,5]
     * [3,1,4,5,2]
     * [3,4,1,2,5]
     * [3,4,1,5,2]
     * 示例 3：
     * 输入：nums = [1,2,3]
     * 输出：0
     * 解释：没有别的排列顺序能得到相同的 BST 。
     * 示例 4：
     * 输入：nums = [3,1,2,5,4,6]
     * 输出：19
     * 示例  5：
     * 输入：nums = [9,4,2,1,3,6,5,7,8,14,11,10,12,13,16,15,17,18]
     * 输出：216212978
     * 解释：得到相同 BST 的方案数是 3216212999。将它对 10^9 + 7 取余后得到 216212978。
     * 提示：
     * 1 <= nums.length <= 1000
     * 1 <= nums[i] <= nums.length
     * nums 中所有数 互不相同 。
     */
    public int numOfWays(int[] nums) {
        return -1;
    }

    public static void main(String[] args) {
        Solution204 solution = new Solution204();
        int[] arr = {1, 2, 2, 2, 1, 1, 2, 2, 2};
        int m = 1, k = 3;
        System.out.println(solution.containsPattern(arr, m, k));
        int[] nums = {5, -20, -20, -39, -5, 0, 0, 0, 36, -32, 0, -7, -10, -7, 21, 20, -12, -34, 26, 2};
        System.out.println(solution.getMaxLen(nums));
        int[][] grid = {{0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        System.out.println(solution.minDays(grid));
    }

}
