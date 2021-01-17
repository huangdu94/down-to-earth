package work.huangdu.exploration.primary_algorithms.array;

import java.util.Arrays;

/**
 * 旋转图像
 * 给定一个 n × n 的二维矩阵表示一个图像。
 * 将图像顺时针旋转 90 度。
 * 说明：
 * 你必须在原地旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。
 * 示例 1:
 * 给定 matrix =
 * [
 * [1,2,3],
 * [4,5,6],
 * [7,8,9]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * [7,4,1],
 * [8,5,2],
 * [9,6,3]
 * ]
 * 示例 2:
 * 给定 matrix =
 * [
 * [ 5, 1, 9,11],
 * [ 2, 4, 8,10],
 * [13, 3, 6, 7],
 * [15,14,12,16]
 * ],
 * 原地旋转输入矩阵，使其变为:
 * [
 * [15,13, 2, 5],
 * [14, 3, 4, 1],
 * [12, 6, 8, 9],
 * [16, 7,10,11]
 * ]
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/26 16:42
 */
public class RotateMap {
    public void rotate(int[][] matrix) {
        for (int i = 0; i < matrix.length / 2; i++) {
            int bound = matrix.length - 1 - i;
            for (int j = i; j < bound; j++) {
                // i,j -> j,bound -> bound,bound-j -> bound-j,i
                int temp = matrix[i][j];
                matrix[i][j] = matrix[bound - (j - i)][i];
                matrix[bound - (j - i)][i] = matrix[bound][bound - (j - i)];
                matrix[bound][bound - (j - i)] = matrix[j][bound];
                matrix[j][bound] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[0];
        System.out.println(Arrays.toString(arr));
        arr = Arrays.copyOf(arr, arr.length + 1);
        System.out.println(Arrays.toString(arr));
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12},
                {13, 14, 15, 16}
        };
        showMatrix(matrix);
        new RotateMap().rotate(matrix);
        showMatrix(matrix);
    }

    private static void showMatrix(int[][] matrix) {
        System.out.println("[");
        for (int i = 0; i < matrix.length; i++) {
            System.out.print("  [");
            for (int j = 0; j < matrix[i].length; j++) {
                if (j == 0) {
                    System.out.print(matrix[i][j]);
                } else {
                    System.out.print("," + matrix[i][j]);
                }
            }
            if (i == matrix.length - 1) {
                System.out.println("]");
            } else {
                System.out.println("],");
            }
        }
        System.out.println("]");
    }

    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        if ((n & 1) == 0) {
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j < n / 2; j++) {
                    rotate(matrix, n, i, j);
                }
            }
        } else {
            for (int i = 0; i < n / 2; i++) {
                for (int j = 0; j <= n / 2; j++) {
                    rotate(matrix, n, i, j);
                }
            }
        }
    }

    // i,j -> j,n-1-i -> n-1-i,n-1-j -> n-1-j,i
    private void rotate(int[][] matrix, int n, int i, int j) {
        int temp = matrix[n - 1 - j][i];
        matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
        matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
        matrix[j][n - 1 - i] = matrix[i][j];
        matrix[i][j] = temp;
    }
}
