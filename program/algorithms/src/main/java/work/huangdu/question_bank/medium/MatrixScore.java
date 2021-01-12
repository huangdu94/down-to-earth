package work.huangdu.question_bank.medium;

/**
 * 861. 翻转矩阵后的得分
 * 有一个二维矩阵 A 其中每个元素的值为 0 或 1 。
 * 移动是指选择任一行或列，并转换该行或列中的每一个值：将所有 0 都更改为 1，将所有 1 都更改为 0。
 * 在做出任意次数的移动后，将该矩阵的每一行都按照二进制数来解释，矩阵的得分就是这些数字的总和。
 * 返回尽可能高的分数。
 * 示例：
 * 输入：[[0,0,1,1],[1,0,1,0],[1,1,0,0]]
 * 输出：39
 * 解释：
 * 转换为 [[1,1,1,1],[1,0,0,1],[1,1,1,1]]
 * 0b1111 + 0b1001 + 0b1111 = 15 + 9 + 15 = 39
 * 提示：
 * 1 <= A.length <= 20
 * 1 <= A[0].length <= 20
 * A[i][j] 是 0 或 1
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/12/7 12:43
 */
public class MatrixScore {
    public int matrixScore(int[][] A) {
        int m = A.length, n = A[0].length, multiple = 1 << (n - 1), result = m * multiple;
        // 1. 只要某一行的最高位不是1，则翻转整行(不用真翻转了计数即可)
        // 2. 只要某一个列的0的个数多于1的个数，则翻转该列(不用真翻转了计数即可)
        for (int j = 1; j < n; j++) {
            multiple >>= 1;
            // 0的个数多于一半则可翻转
            int count = 0;
            for (int i = 0; i < m; i++) {
                if ((A[i][0] == 1 && A[i][j] == 0) || (A[i][0] == 0 && A[i][j] == 1)) {
                    count++;
                }
            }
            result += (2 * count > m ? multiple * count : multiple * (m - count));
        }
        return result;
    }

    public static void main(String[] args) {
        int[][] A = {{0, 0, 1, 1}, {1, 0, 1, 0}, {1, 1, 0, 0}};
        MatrixScore matrixScore = new MatrixScore();
        System.out.println(matrixScore.matrixScore(A));
    }
}
