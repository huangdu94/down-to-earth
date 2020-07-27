package club.huangdu94.algorithm_difficult.arrstring;

import java.util.ArrayList;
import java.util.List;

/**
 * 螺旋矩阵
 * 给定一个包含 m x n 个元素的矩阵（m 行, n 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。
 * 示例 1:
 * 输入:
 * [
 * [ 1, 2, 3 ],
 * [ 4, 5, 6 ],
 * [ 7, 8, 9 ]
 * ]
 * 输出: [1,2,3,6,9,8,7,4,5]
 * 示例 2:
 * 输入:
 * [
 * [1, 2, 3, 4],
 * [5, 6, 7, 8],
 * [9,10,11,12]
 * ]
 * 输出: [1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 19:59
 */
public class SpiralOrder {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        int capacity = m * n;
        List<Integer> result = new ArrayList<>(capacity);
        int loop = 0;
        while (result.size() < capacity) {
            int right = n - loop - 1;
            int down = m - loop - 1;
            for (int i = loop; i <= right; i++)
                result.add(matrix[loop][i]);
            for (int i = 1 + loop; i <= down; i++)
                result.add(matrix[i][right]);
            if (down > loop)
                for (int i = right - 1; i >= loop; i--)
                    result.add(matrix[down][i]);
            if (right > loop)
                for (int i = down - 1; i >= loop + 1; i--)
                    result.add(matrix[i][loop]);
            loop++;
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3, 4},
                {5, 6, 7, 8},
                {9, 10, 11, 12}
        };
        SpiralOrder spiralOrder = new SpiralOrder();
        System.out.println(spiralOrder.spiralOrder(matrix));
    }
}
