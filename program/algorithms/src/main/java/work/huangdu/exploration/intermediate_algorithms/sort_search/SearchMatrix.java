package work.huangdu.exploration.intermediate_algorithms.sort_search;

/**
 * 240. 搜索二维矩阵 II
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 * 示例:
 * 现有矩阵 matrix 如下：
 * [
 * [1,   4,  7, 11, 15],
 * [2,   5,  8, 12, 19],
 * [3,   6,  9, 16, 22],
 * [10, 13, 14, 17, 24],
 * [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/12 15:51
 */
public class SearchMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0)
            return false;
        int i = matrix.length - 1;
        int j = 0;
        while (i >= 0 && j <= matrix[0].length - 1)
            if (matrix[i][j] < target)
                j++;
            else if (matrix[i][j] > target)
                i--;
            else
                return true;
        return false;
    }

    public boolean searchMatrix4(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return false;
        int i = 0;
        int j = matrix[0].length - 1;
        while (i <= matrix.length - 1 && j >= 0) {
            if (matrix[i][j] < target)
                i++;
            else if (matrix[i][j] > target)
                j--;
            else
                return true;
        }
        return false;
    }

    public boolean searchMatrix3(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return false;
        int m = matrix.length;
        int n = matrix[0].length;
        boolean[][] haveStep = new boolean[m][n];
        return backtrack(matrix, haveStep, 0, 0, m - 1, n - 1, target);
    }

    private boolean backtrack(int[][] matrix, boolean[][] haveStep, int i, int j, int m, int n, int target) {
        int cur = matrix[i][j];
        haveStep[i][j] = true;
        if (cur == target)
            return true;
        boolean flag = false;
        if (cur < target) {
            if (i + 1 <= m && !haveStep[i + 1][j])
                flag = backtrack(matrix, haveStep, i + 1, j, m, n, target);
            if (!flag && j + 1 <= n && !haveStep[i][j + 1])
                flag = backtrack(matrix, haveStep, i, j + 1, m, n, target);
        }
        return flag;
    }

    public boolean searchMatrix2(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return false;
        outloop:
        for (int[] row : matrix) {
            if (row[0] > target)
                break;
            for (int number : row) {
                if (number > target)
                    continue outloop;
                if (number == target)
                    return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };
        int[][] matrix2 = {{-1, 3}};
        int target = 3;
        System.out.println(new SearchMatrix().searchMatrix(matrix2, target));
    }
}
