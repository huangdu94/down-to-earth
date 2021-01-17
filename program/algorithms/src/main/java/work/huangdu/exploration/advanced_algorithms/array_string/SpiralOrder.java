package work.huangdu.exploration.advanced_algorithms.array_string;

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
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/26 19:59
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

    public List<Integer> spiralOrder2(int[][] matrix) {
        if (matrix.length == 0) return new ArrayList<>();
        int n = matrix.length, m = matrix[0].length, capacity = n * m;
        List<Integer> res = new ArrayList<>(capacity);
        int u = 0, d = n - 1, l = 0, r = m - 1;
        while (l <= r && u <= d) {
            for (int i = l; i <= r; i++) {
                res.add(matrix[u][i]);
            }
            u++;
            for (int i = u; i <= d; i++) {
                res.add(matrix[i][r]);
            }
            r--;
            if (u - 1 != d) {
                for (int i = r; i >= l; i--) {
                    res.add(matrix[d][i]);
                }
            }
            d--;
            if (r + 1 != l) {
                for (int i = d; i >= u; i--) {
                    res.add(matrix[i][l]);
                }
            }
            l++;
        }
        return res;
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
