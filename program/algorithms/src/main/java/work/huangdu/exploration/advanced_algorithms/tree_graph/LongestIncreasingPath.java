package work.huangdu.exploration.advanced_algorithms.tree_graph;

/**
 * 矩阵中的最长递增路径
 * 给定一个整数矩阵，找出最长递增路径的长度。
 * 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。
 * 示例 1:
 * 输入: nums =
 * [
 * [9,9,4],
 * [6,6,8],
 * [2,1,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径为[1, 2, 6, 9]。
 * 示例 2:
 * 输入: nums =
 * [
 * [3,4,5],
 * [3,2,6],
 * [2,2,1]
 * ]
 * 输出: 4
 * 解释: 最长递增路径是[3, 4, 5, 6]。注意不允许在对角线方向上移动。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/11 11:27
 */
public class LongestIncreasingPath {
    public int longestIncreasingPath(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return 0;
        int len, max = 0, m = matrix.length, n = matrix[0].length;
        // 没有遍历过的点为0，遍历过的点其数字即为从这点开始最长的递增路径长度
        int[][] visited = new int[m][n];
        // 选择没有遍历过的点开始遍历
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0) {
                    len = dfs(matrix, visited, m, n, i, j);
                    if (len > max) max = len;
                }
            }
        }
        return max;
    }

    // 深度优先搜索
    private int dfs(int[][] matrix, int[][] visited, int m, int n, int i, int j) {
        if (visited[i][j] != 0) return visited[i][j];
        int len, max = 0, cur = matrix[i][j];
        if (i - 1 >= 0 && matrix[i - 1][j] > cur) {
            len = dfs(matrix, visited, m, n, i - 1, j);
            if (len > max) max = len;
        }
        if (i + 1 < m && matrix[i + 1][j] > cur) {
            len = dfs(matrix, visited, m, n, i + 1, j);
            if (len > max) max = len;
        }
        if (j - 1 >= 0 && matrix[i][j - 1] > cur) {
            len = dfs(matrix, visited, m, n, i, j - 1);
            if (len > max) max = len;
        }
        if (j + 1 < n && matrix[i][j + 1] > cur) {
            len = dfs(matrix, visited, m, n, i, j + 1);
            if (len > max) max = len;
        }
        visited[i][j] = max + 1;
        return max + 1;
    }
}
