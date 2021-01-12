package work.huangdu.exploration.start_from_scratch.array.special_traversal_2d;

/**
 * 59. 螺旋矩阵 II
 * 给定一个正整数 n，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的正方形矩阵。
 * 示例:
 * 输入: 3
 * 输出:
 * [
 * [ 1, 2, 3 ],
 * [ 8, 9, 4 ],
 * [ 7, 6, 5 ]
 * ]
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/9/17 15:23
 */
public class GenerateMatrix {
    private int[][] res;
    private int n;
    private int num;

    public int[][] generateMatrix(int n) {
        if (n == 1) return new int[][]{{1}};
        this.res = new int[n][n];
        this.n = n;
        this.num = 1;
        goRight(0, 0);
        return res;
    }

    private void goRight(int i, int j) {
        if (res[i][j] != 0) return;
        while (j < n && res[i][j] == 0) {
            res[i][j++] = num++;
        }
        goDown(i + 1, j - 1);
    }

    private void goDown(int i, int j) {
        if (res[i][j] != 0) return;
        while (i < n && res[i][j] == 0) {
            res[i++][j] = num++;
        }
        goLeft(i - 1, j - 1);
    }

    private void goLeft(int i, int j) {
        if (res[i][j] != 0) return;
        while (j >= 0 && res[i][j] == 0) {
            res[i][j--] = num++;
        }
        goUp(i - 1, j + 1);
    }

    private void goUp(int i, int j) {
        if (res[i][j] != 0) return;
        while (i >= 0 && res[i][j] == 0) {
            res[i--][j] = num++;
        }
        goRight(i + 1, j + 1);
    }
}
