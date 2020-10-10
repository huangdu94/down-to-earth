package work.huangdu.exploration.intermediate_algorithms.tree_graph;

import java.util.*;

/**
 * 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 * 示例 1:
 * 输入:
 * [
 * ['1','1','1','1','0'],
 * ['1','1','0','1','0'],
 * ['1','1','0','0','0'],
 * ['0','0','0','0','0']
 * ]
 * 输出: 1
 * 示例 2:
 * 输入:
 * [
 * ['1','1','0','0','0'],
 * ['1','1','0','0','0'],
 * ['0','0','1','0','0'],
 * ['0','0','0','1','1']
 * ]
 * 输出: 3
 * 解释: 每座岛屿只能由水平和/或竖直方向上相邻的陆地连接而成。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/7 18:58
 */
public class NumIslands {
    private static class Point {
        int i;
        int j;

        Point(int i, int j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return i == point.i &&
                    j == point.j;
        }

        @Override
        public int hashCode() {
            return Objects.hash(i, j);
        }
    }

    public int numIslands(char[][] grid) {
        // 边界处理
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        List<Set<Point>> islandList = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    boolean flag = true;
                    Point cur = new Point(i, j);
                    for (Set<Point> island : islandList) {
                        if (island.contains(cur)) {
                            flag = false;
                            break;
                        }
                    }
                    if (flag) {
                        Set<Point> newIsland = new HashSet<>();
                        this.iterateIsland(grid, newIsland, i, j);
                        islandList.add(newIsland);
                    }
                }
            }
        }
        return islandList.size();
    }

    public int numIslands2(char[][] grid) {
        // 边界处理
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        // 存储已经遍历过的点
        int numIslands = 0;
        Set<Point> pointSet = new HashSet<>(grid.length * grid[0].length);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    Point cur = new Point(i, j);
                    if (!pointSet.contains(cur)) {
                        numIslands++;
                        Set<Point> newIsland = new HashSet<>();
                        this.iterateIsland(grid, newIsland, i, j);
                        pointSet.addAll(newIsland);
                    }
                }
            }
        }
        return numIslands;
    }

    public int numIslands3(char[][] grid) {
        // 边界处理
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;
        // 存储已经遍历过的点
        int numIslands = 0;
        int row = grid.length;
        int coj = grid[0].length;
        char[][] grid_copy = new char[row][];
        for (int i = 0; i < row; i++) {
            grid_copy[i] = Arrays.copyOf(grid[i], coj);
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < coj; j++) {
                if (grid_copy[i][j] == '1') {
                    numIslands++;
                    this.iterateIsland2(grid_copy, row, coj, i, j);
                }
            }
        }
        return numIslands;
    }


    /**
     * 给岛中的一个点
     *
     * @param grid   地图二维数组
     * @param island 整个岛点集合
     * @param i      当前点的i
     * @param j      当前点的j
     */
    private void iterateIsland(char[][] grid, Set<Point> island, int i, int j) {
        if (i < 0 || i > grid.length - 1 ||
                j < 0 || j > grid[0].length - 1 ||
                grid[i][j] == '0')
            return;
        Point cur = new Point(i, j);
        if (island.contains(cur))
            return;
        island.add(cur);
        this.iterateIsland(grid, island, i - 1, j);
        this.iterateIsland(grid, island, i + 1, j);
        this.iterateIsland(grid, island, i, j - 1);
        this.iterateIsland(grid, island, i, j + 1);
    }

    /**
     * 给岛中的一个点
     *
     * @param grid 地图二维数组
     * @param row  grid的行数
     * @param coj  grid的列数
     * @param i    当前点的i
     * @param j    当前点的j
     */
    private void iterateIsland2(char[][] grid, int row, int coj, int i, int j) {
        if (i < 0 || i > row - 1 || j < 0 || j > coj - 1 || grid[i][j] == '0')
            return;
        grid[i][j] = '0';
        this.iterateIsland2(grid, row, coj, i - 1, j);
        this.iterateIsland2(grid, row, coj, i + 1, j);
        this.iterateIsland2(grid, row, coj, i, j - 1);
        this.iterateIsland2(grid, row, coj, i, j + 1);
    }

    public static void main(String[] args) {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        long start = System.currentTimeMillis();
        int result = 0;
        for (int i = 0; i < 10000; i++) {
            result = new NumIslands().numIslands3(grid);
        }
        long end = System.currentTimeMillis();
        System.out.println("程序执行结果: " + result);
        System.out.println("程序执行时间(ms): " + (end - start));
    }
}
