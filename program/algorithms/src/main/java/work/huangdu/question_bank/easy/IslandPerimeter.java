package work.huangdu.question_bank.easy;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 463. 岛屿的周长
 * 给定一个包含 0 和 1 的二维网格地图，其中 1 表示陆地 0 表示水域。
 * 网格中的格子水平和垂直方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿（或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 * 示例 :
 * 输入:
 * [[0,1,0,0],
 * [1,1,1,0],
 * [0,1,0,0],
 * [1,1,0,0]]
 * 输出: 16
 * 解释: 它的周长是下面图片中的 16 个黄色的边：
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/10/30 12:45
 */
public class IslandPerimeter {
    private int sum = 0;

    public int islandPerimeter2(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, new boolean[m][n], m, n, i, j);
                    return sum;
                }
            }
        }
        return 0;
    }

    private void dfs(int[][] grid, boolean[][] visited, int m, int n, int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || grid[i][j] == 0) {
            sum++;
            return;
        }
        if (visited[i][j]) return;
        visited[i][j] = true;
        dfs(grid, visited, m, n, i - 1, j);
        dfs(grid, visited, m, n, i + 1, j);
        dfs(grid, visited, m, n, i, j - 1);
        dfs(grid, visited, m, n, i, j + 1);
    }

    public static void main(String[] args) {
        IslandPerimeter ilp = new IslandPerimeter();
        int[][] grid = {{1, 1}, {1, 1}};
        System.out.println(ilp.islandPerimeter(grid));
    }

    public int islandPerimeter3(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int sum = 0;
                    Deque<int[]> queue = new ArrayDeque<>();
                    queue.offer(new int[]{i, j});
                    while (!queue.isEmpty()) {
                        i = queue.peek()[0];
                        j = queue.poll()[1];
                        if (visited[i][j]) continue;
                        visited[i][j] = true;
                        if (i - 1 < 0 || grid[i - 1][j] == 0) {
                            sum++;
                        } else {
                            queue.offer(new int[]{i - 1, j});
                        }
                        if (i + 1 >= m || grid[i + 1][j] == 0) {
                            sum++;
                        } else {
                            queue.offer(new int[]{i + 1, j});
                        }
                        if (j - 1 < 0 || grid[i][j - 1] == 0) {
                            sum++;
                        } else {
                            queue.offer(new int[]{i, j - 1});
                        }
                        if (j + 1 >= n || grid[i][j + 1] == 0) {
                            sum++;
                        } else {
                            queue.offer(new int[]{i, j + 1});
                        }
                    }
                    return sum;
                }
            }
        }
        return 0;
    }

    public int islandPerimeter(int[][] grid) {
        int m = grid.length, n = grid[0].length, sum = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    if (i - 1 < 0 || grid[i - 1][j] == 0) {
                        sum++;
                    }
                    if (i + 1 >= m || grid[i + 1][j] == 0) {
                        sum++;
                    }
                    if (j - 1 < 0 || grid[i][j - 1] == 0) {
                        sum++;
                    }
                    if (j + 1 >= n || grid[i][j + 1] == 0) {
                        sum++;
                    }
                }
            }
        }
        return sum;
    }
}
