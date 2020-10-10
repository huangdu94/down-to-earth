package work.huangdu.exploration.primary_algorithms.array;

/**
 * 有效的数独
 * 判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
 * 上图是一个部分填充的有效的数独。
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 * 示例 1:
 * 输入:
 * [
 * ["5","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: true
 * 示例 2:
 * 输入:
 * [
 * ["8","3",".",".","7",".",".",".","."],
 * ["6",".",".","1","9","5",".",".","."],
 * [".","9","8",".",".",".",".","6","."],
 * ["8",".",".",".","6",".",".",".","3"],
 * ["4",".",".","8",".","3",".",".","1"],
 * ["7",".",".",".","2",".",".",".","6"],
 * [".","6",".",".",".",".","2","8","."],
 * [".",".",".","4","1","9",".",".","5"],
 * [".",".",".",".","8",".",".","7","9"]
 * ]
 * 输出: false
 * 解释: 除了第一行的第一个数字从 5 改为 8 以外，空格内其他数字均与 示例1 相同。
 * 但由于位于左上角的 3x3 宫内有两个 8 存在, 因此这个数独是无效的。
 * 说明:
 * 一个有效的数独（部分已被填充）不一定是可解的。
 * 只需要根据以上规则，验证已经填入的数字是否有效即可。
 * 给定数独序列只包含数字 1-9 和字符 '.' 。
 * 给定数独永远是 9x9 形式的。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 16:14
 */
public class IsValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[] flagArr = new int[9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                int cur = board[i][j] - '0';
                // '.' - '0' == -3
                if (cur < 0)
                    continue;
                cur = 1 << cur;
                int block = i / 3 * 3 + j / 3;
                if ((flagArr[block] & cur) != 0 || ((flagArr[j] >>> 9) & cur) != 0 || ((flagArr[i] >>> 18) & cur) != 0)
                    return false;
                flagArr[block] |= cur;
                flagArr[j] |= (cur << 9);
                flagArr[i] |= (cur << 18);
            }
        }
        return true;
    }

    public boolean isValidSudoku2(char[][] board) {
        boolean[] flagArr;
        for (int i = 0; i < 9; i++) {
            flagArr = new boolean[9];
            for (int j = 0; j < 9; j++) {
                int index = board[i][j] - 49;
                if (index == -3) {
                    continue;
                }
                if (flagArr[index]) {
                    return false;
                }
                flagArr[index] = true;
            }
        }
        for (int j = 0; j < 9; j++) {
            flagArr = new boolean[9];
            for (int i = 0; i < 9; i++) {
                int index = board[i][j] - 49;
                if (index == -3) {
                    continue;
                }
                if (flagArr[index]) {
                    return false;
                }
                flagArr[index] = true;
            }
        }
        for (int row = 0; row < 3; row++) {
            for (int coj = 0; coj < 3; coj++) {
                flagArr = new boolean[9];
                for (int i = 3 * row; i < 3 * row + 3; i++) {
                    for (int j = 3 * coj; j < 3 * coj + 3; j++) {
                        int index = board[i][j] - 49;
                        if (index == -3) {
                            continue;
                        }
                        if (flagArr[index]) {
                            return false;
                        }
                        flagArr[index] = true;
                    }
                }
            }
        }
        return true;
    }
}
