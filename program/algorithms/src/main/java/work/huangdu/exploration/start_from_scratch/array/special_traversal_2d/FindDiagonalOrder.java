package work.huangdu.exploration.start_from_scratch.array.special_traversal_2d;

import java.util.Arrays;

/**
 * 498. 对角线遍历
 * 给定一个含有 M x N 个元素的矩阵（M 行，N 列），请以对角线遍历的顺序返回这个矩阵中的所有元素，对角线遍历如下图所示。
 * 示例:
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出:  [1,2,4,7,5,3,6,8,9]
 * 解释:
 * 说明:
 * 给定矩阵中的元素总数不会超过 100000 。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/17 15:24
 */
public class FindDiagonalOrder {
        public int[] findDiagonalOrder(int[][] matrix) {
            if (matrix.length == 0) return new int[0];
            int m = matrix.length, n = matrix[0].length;
            int[] res = new int[m * n];
            int i = 0, j = 0, k = 0, index = 0;
            while (k < m + n - 1) {
                while (i >= 0 && j < n) {
                    res[index++] = matrix[i--][j++];
                }
                if (++k == m + n - 1) break;
                if (k < n) {
                    i = 0;
                    j = k;
                } else {
                    j = n - 1;
                    i = k - j;
                }
                while (i < m && j >= 0) {
                    res[index++] = matrix[i++][j--];
                }
                if (++k < m) {
                    i = k;
                    j = 0;
                } else {
                    i = m - 1;
                    j = k - i;
                }
            }
            return res;
        }

    public static void main(String[] args) {
        FindDiagonalOrder order = new FindDiagonalOrder();
        int[][] matrix = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        System.out.println(Arrays.toString(order.findDiagonalOrder(matrix)));
    }
}
