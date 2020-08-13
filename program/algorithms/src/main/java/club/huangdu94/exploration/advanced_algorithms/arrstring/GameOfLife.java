package club.huangdu94.exploration.advanced_algorithms.arrstring;

/**
 * 生命游戏
 * 根据 百度百科 ，生命游戏，简称为生命，是英国数学家约翰·何顿·康威在 1970 年发明的细胞自动机。
 * 给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：
 * 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
 * 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
 * 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
 * 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；
 * 根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。
 * 示例：
 * 输入：
 * [
 * [0,1,0],
 * [0,0,1],
 * [1,1,1],
 * [0,0,0]
 * ]
 * 输出：
 * [
 * [0,0,0],
 * [1,0,1],
 * [0,1,1],
 * [0,1,0]
 * ]
 * 进阶：
 * 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
 * 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/27 14:27
 */
public class GameOfLife {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        int n = board[0].length;
        this.mark(board, m, n);
        this.clear(board, m, n);
    }

    /**
     * 标记每个位置周围的活细胞数量
     * 活细胞位置标记为正数(绝对值为周围活细胞数+2)
     * 死细胞位置标记为负数(绝对值为周围活细胞数+1)
     * 标记完避免使用0,1 否则clear时无法区分标记了还是没标记
     */
    private void mark(int[][] board, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int count = this.compute(board, m, n, i, j);
                board[i][j] = board[i][j] == 0 ? -count - 1 : count + 2;
            }
        }
    }

    /**
     * -4 4和5标记为1
     * 其它标记为0
     */
    private void clear(int[][] board, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = board[i][j];
                if (val == -4 || val == 4 || val == 5)
                    board[i][j] = 1;
                else
                    board[i][j] = 0;
            }
        }
    }

    /**
     * 计算i,j位置细胞周围八格活细胞的数量
     * 如果在边界处则视边界外都为死细胞
     * 八种边界情况
     */
    private int compute(int[][] board, int m, int n, int i, int j) {
        int count = 0;
        if (j > 0)
            count += board[i][j - 1] > 0 ? 1 : 0;
        if (j < n - 1)
            count += board[i][j + 1] > 0 ? 1 : 0;
        if (i > 0) {
            count += board[i - 1][j] > 0 ? 1 : 0;
            if (j > 0)
                count += board[i - 1][j - 1] > 0 ? 1 : 0;
            if (j < n - 1)
                count += board[i - 1][j + 1] > 0 ? 1 : 0;
        }
        if (i < m - 1) {
            count += board[i + 1][j] > 0 ? 1 : 0;
            if (j > 0)
                count += board[i + 1][j - 1] > 0 ? 1 : 0;
            if (j < n - 1)
                count += board[i + 1][j + 1] > 0 ? 1 : 0;
        }
        return count;
    }

    public static void main(String[] args) {
        int[][] board = new int[11][11];
        board[5][4] = 1;
        board[5][5] = 1;
        board[5][6] = 1;
        GameOfLife gameOfLife = new GameOfLife();
        gameOfLife.gameOfLife(board);
    }

    private static void printBoard(int[][] board) {
        System.out.println("[");
        for (int[] row : board) {
            System.out.print("[");
            for (int element : row) {
                System.out.print(element + ",");
            }
            System.out.println("]");
        }
        System.out.println("]");
    }

    /**
     * -4 标记为 1 flag置为true
     * >5 或 >=2 <4 标记为 0 flag置为true
     * 4和5标记为1 flag不变
     * 其它均标记为0 flag不变
     */
    private boolean clearAndFlag(int[][] board, int m, int n) {
        boolean flag = false;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int val = board[i][j];
                if (val == -4 || val == 4 || val == 5) {
                    board[i][j] = 1;
                    if (!flag && val == -4)
                        flag = true;
                } else {
                    board[i][j] = 0;
                    if (!flag && val >= 2)
                        flag = true;
                }
            }
        }
        return flag;
    }
}
