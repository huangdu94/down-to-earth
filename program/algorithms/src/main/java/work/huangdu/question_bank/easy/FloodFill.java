package work.huangdu.question_bank.easy;

import javafx.util.Pair;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 733. 图像渲染
 * 有一幅以二维整数数组表示的图画，每一个整数表示该图画的像素值大小，数值在 0 到 65535 之间。
 * 给你一个坐标 (sr, sc) 表示图像渲染开始的像素值（行 ，列）和一个新的颜色值 newColor，让你重新上色这幅图像。
 * 为了完成上色工作，从初始坐标开始，记录初始坐标的上下左右四个方向上像素值与初始坐标相同的相连像素点，接着再记录这四个方向上符合条件的像素点与他们对应四个方向上像素值与初始坐标相同的相连像素点，……，重复该过程。将所有有记录的像素点的颜色值改为新的颜色值。
 * 最后返回经过上色渲染后的图像。
 * 示例 1:
 * 输入:
 * image = [[1,1,1],[1,1,0],[1,0,1]]
 * sr = 1, sc = 1, newColor = 2
 * 输出: [[2,2,2],[2,2,0],[2,0,1]]
 * 解析:
 * 在图像的正中间，(坐标(sr,sc)=(1,1)),
 * 在路径上所有符合条件的像素点的颜色都被更改成2。
 * 注意，右下角的像素没有更改为2，
 * 因为它不是在上下左右四个方向上与初始点相连的像素点。
 * 注意:
 * image 和 image[0] 的长度在范围 [1, 50] 内。
 * 给出的初始点将满足 0 <= sr < image.length 和 0 <= sc < image[0].length。
 * image[i][j] 和 newColor 表示的颜色值在范围 [0, 65535]内。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/8/16 0:10
 */
public class FloodFill {
    // bfs
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        int oldColor = image[sr][sc];
        if (oldColor == newColor) return image;
        int m = image.length, n = image[0].length;
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        Queue<Pair<Integer, Integer>> queue = new ArrayDeque<>();
        queue.offer(new Pair<>(sr, sc));
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> coord = queue.poll();
            int i = coord.getKey();
            int j = coord.getValue();
            image[i][j] = newColor;
            for (int[] direction : directions) {
                int newI = i + direction[0];
                int newJ = j + direction[1];
                if (newI >= 0 && newI < m && newJ >= 0 && newJ < n && image[newI][newJ] == oldColor) {
                    queue.offer(new Pair<>(newI, newJ));
                }
            }
        }
        return image;
    }

    private int m;
    private int n;
    private int newColor;
    private int oldColor;
    private int[][] image;

    // dfs
    public int[][] floodFillDfs(int[][] image, int sr, int sc, int newColor) {
        this.oldColor = image[sr][sc];
        this.newColor = newColor;
        if (this.oldColor == this.newColor) return image;
        this.image = image;
        this.m = image.length;
        this.n = image[0].length;
        dfs(sr, sc);
        return image;
    }

    private void dfs(int i, int j) {
        if (i < 0 || i >= m || j < 0 || j >= n || image[i][j] != oldColor) return;
        image[i][j] = newColor;
        dfs(i - 1, j);
        dfs(i + 1, j);
        dfs(i, j - 1);
        dfs(i, j + 1);
    }
}
