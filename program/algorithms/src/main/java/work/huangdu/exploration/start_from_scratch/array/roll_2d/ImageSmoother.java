package work.huangdu.exploration.start_from_scratch.array.roll_2d;

import java.util.Arrays;

/**
 * 661. 图片平滑器
 * 包含整数的二维矩阵 M 表示一个图片的灰度。你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入) ，平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
 * 示例 1:
 * 输入:
 * [[1,1,1],
 * [1,0,1],
 * [1,1,1]]
 * 输出:
 * [[0, 0, 0],
 * [0, 0, 0],
 * [0, 0, 0]]
 * 解释:
 * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
 * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
 * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
 * 注意:
 * 给定矩阵中的整数范围为 [0, 255]。
 * 矩阵的长和宽的范围均为 [1, 150]。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/16 0:59
 */
public class ImageSmoother {
    public int[][] imageSmoother(int[][] M) {
        int m = M.length, n = M[0].length;
        // 滚动数组
        int[] rollOne = null, rollTwo;
        for (int i = 0; i < m; i++) {
            rollTwo = new int[n];
            for (int j = 0; j < n; j++) {
                rollTwo[j] = computeSmoother(M, m, n, i, j);
            }
            if (i >= 1) M[i - 1] = rollOne;
            rollOne = rollTwo;
        }
        M[m - 1] = rollOne;
        return M;
    }

    private int computeSmoother(int[][] M, int m, int n, int i, int j) {
        int sum = 0, count = 0;
        for (int row = i - 1; row <= i + 1; row++) {
            for (int coj = j - 1; coj <= j + 1; coj++) {
                if (row >= 0 && row < m && coj >= 0 && coj < n) {
                    sum += M[row][coj];
                    count++;
                }
            }
        }
        return sum / count;
    }

    public static void main(String[] args) {
        ImageSmoother smoother = new ImageSmoother();
        int[][] M = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        M = smoother.imageSmoother(M);
        System.out.println(Arrays.deepToString(M));
    }
}
