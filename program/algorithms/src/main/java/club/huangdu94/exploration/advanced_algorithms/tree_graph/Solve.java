package club.huangdu94.exploration.advanced_algorithms.tree_graph;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 被围绕的区域
 * 给定一个二维的矩阵，包含'X'和'O'（字母 O）。
 * 找到所有被 'X' 围绕的区域，并将这些区域里所有的'O' 用 'X' 填充。
 * 示例:
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 运行你的函数后，矩阵变为：
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 解释:
 * 被围绕的区间不会存在于边界上，换句话说，任何边界上的'O'都不会被填充为'X'。 任何不在边界上，或不与边界上的'O'相连的'O'最终都会被填充为'X'。如果两个元素在水平或垂直方向相邻，则称它们是“相连”的。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/4 12:44
 */
public class Solve {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return;
        int m = board.length;
        int n = board[0].length;
        // 方向 上下左右
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        // 1. 上边界 和 下边界
        for (int j = 0; j < n; j++) {
            if (board[0][j] == 'O')
                this.dfs(board, 0, j, directions, m, n);
            if (board[m - 1][j] == 'O')
                this.dfs(board, m - 1, j, directions, m, n);
        }
        // 2. 左边界 和 右边界
        for (int i = 1; i < m - 1; i++) {
            if (board[i][0] == 'O')
                this.dfs(board, i, 0, directions, m, n);
            if (board[i][n - 1] == 'O')
                this.dfs(board, i, n - 1, directions, m, n);
        }
        // 3. 转换标记为'O' 其它都转换为'X'
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                if (board[i][j] == 'O')
                    board[i][j] = 'X';
                else if (board[i][j] == '1')
                    board[i][j] = 'O';
    }

    // 深度优先搜索
    private void dfs(char[][] board, int i, int j, int[][] directions, int m, int n) {
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == 'X' || board[i][j] == '1') return;
        board[i][j] = '1';
        for (int[] direction : directions) this.dfs(board, i + direction[0], j + direction[1], directions, m, n);
    }

    // 广度优先搜索
    private void bfs(char[][] board, int x, int y, int[][] directions, int m, int n) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] coords = queue.poll();
            int i = coords[0], j = coords[1];
            if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] == 'X' || board[i][j] == '1') continue;
            board[i][j] = '1';
            for (int[] direction : directions) queue.offer(new int[]{i + direction[0], j + direction[1]});
        }
    }
}
