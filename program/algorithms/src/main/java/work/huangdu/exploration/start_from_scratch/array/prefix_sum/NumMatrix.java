package work.huangdu.exploration.start_from_scratch.array.prefix_sum;

import java.util.Arrays;

/**
 * 304. 二维区域和检索 - 矩阵不可变
 * 给定一个二维矩阵，计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)。
 * Range Sum Query 2D
 * 上图子矩阵左上角 (row1, col1) = (2, 1) ，右下角(row2, col2) = (4, 3)，该子矩形内元素的总和为 8。
 * 示例:
 * 给定 matrix = [
 * [3, 0, 1, 4, 2],
 * [5, 6, 3, 2, 1],
 * [1, 2, 0, 1, 5],
 * [4, 1, 0, 1, 7],
 * [1, 0, 3, 0, 5]
 * ]
 * sumRegion(2, 1, 4, 3) -> 8
 * sumRegion(1, 1, 2, 2) -> 11
 * sumRegion(1, 2, 2, 4) -> 12
 * 说明:
 * 你可以假设矩阵不可变。
 * 会多次调用 sumRegion 方法。
 * 你可以假设 row1 ≤ row2 且 col1 ≤ col2。
 * Your NumMatrix object will be instantiated and called as such:
 * NumMatrix obj = new NumMatrix(matrix);
 * int param_1 = obj.sumRegion(row1,col1,row2,col2);
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/19 15:18
 */
public class NumMatrix {
    private int[][] matrixSum;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0) return;
        int m = matrix.length;
        int n = matrix[0].length;
        matrixSum = new int[m + 1][n + 1];
        for (int i = 0; i < m; i++) {
            int rowSum = 0;
            for (int j = 0; j < n; j++) {
                matrixSum[i + 1][j + 1] = matrixSum[i][j + 1] + (rowSum += matrix[i][j]);
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        return matrixSum[row2 + 1][col2 + 1] - matrixSum[row1][col2 + 1] - matrixSum[row2 + 1][col1] + matrixSum[row1][col1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}
        };
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(Arrays.deepToString(numMatrix.matrixSum));
        System.out.println(numMatrix.matrixSum[5][4]);
    }
}
