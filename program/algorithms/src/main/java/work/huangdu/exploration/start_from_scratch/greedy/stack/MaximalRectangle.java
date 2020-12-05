package work.huangdu.exploration.start_from_scratch.greedy.stack;

/**
 * 85. 最大矩形
 * 给定一个仅包含 0 和 1 、大小为 rows x cols 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：6
 * 解释：最大矩形如上图所示。
 * 示例 2：
 * 输入：matrix = []
 * 输出：0
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 * 示例 4：
 * 输入：matrix = [["1"]]
 * 输出：1
 * 示例 5：
 * 输入：matrix = [["0","0"]]
 * 输出：0
 * 提示：
 * rows == matrix.length
 * cols == matrix.length
 * 0 <= row, cols <= 200
 * matrix[i][j] 为 '0' 或 '1'
 *
 * @author duhuang@iflytek.com
 * @version 2020/12/4 13:31
 * @see work.huangdu.exploration.advanced_algorithms.other.LargestRectangleArea
 */
public class MaximalRectangle {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int m = matrix.length, n = matrix[0].length, max = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == '1') {
                    max = maxArea(matrix, i, j, m, n, max);
                }
            }
        }
        return max;
    }

    private int maxArea(char[][] matrix, int row, int coj, int m, int n, int max) {
        // 1. 如果(row,coj)作为左上角，最大的可能性已经小于等于maxArea，则没必要继续
        if ((m - row) * (n - coj) <= max) return max;
        // 题目限制row和coj最大200，那这里lastCoj设置为201
        int lastCoj = 201, area;
        for (int j = coj; j < n; j++) {
            // 2. 如果利用上目前已经得到的信息，判断之后可能出现的最大值已经小于等于maxArea，则没必要继续
            if (lastCoj * (n - coj) <= max) return max;
            area = 0;
            int i = row;
            while (i < m && (i - row < lastCoj) && matrix[i][j] == '1') {
                area += (j - coj + 1);
                i++;
            }
            lastCoj = i - row;
            if (max < area) {
                max = area;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1', '1', '0', '1'}, {'1', '1', '0', '1'}, {'1', '1', '1', '1'}};
        System.out.println(new MaximalRectangle().maximalRectangle(matrix));
    }
}
