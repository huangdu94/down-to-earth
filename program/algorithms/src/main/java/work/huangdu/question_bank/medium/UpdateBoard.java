package work.huangdu.question_bank.medium;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 529. 扫雷游戏
 * 让我们一起来玩扫雷游戏！
 * 给定一个代表游戏板的二维字符矩阵。 'M' 代表一个未挖出的地雷，'E' 代表一个未挖出的空方块，'B' 代表没有相邻（上，下，左，右，和所有4个对角线）地雷的已挖出的空白方块，数字（'1' 到 '8'）表示有多少地雷与这块已挖出的方块相邻，'X' 则表示一个已挖出的地雷。
 * 现在给出在所有未挖出的方块中（'M'或者'E'）的下一个点击位置（行和列索引），根据以下规则，返回相应位置被点击后对应的面板：
 * 如果一个地雷（'M'）被挖出，游戏就结束了- 把它改为 'X'。
 * 如果一个没有相邻地雷的空方块（'E'）被挖出，修改它为（'B'），并且所有和其相邻的未挖出方块都应该被递归地揭露。
 * 如果一个至少与一个地雷相邻的空方块（'E'）被挖出，修改它为数字（'1'到'8'），表示相邻地雷的数量。
 * 如果在此次点击中，若无更多方块可被揭露，则返回面板。
 * 示例 1：
 * 输入:
 * [['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'M', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E'],
 * ['E', 'E', 'E', 'E', 'E']]
 * Click : [3,0]
 * 输出:
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * 解释:
 * 示例 2：
 * 输入:
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'M', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * Click : [1,2]
 * 输出:
 * [['B', '1', 'E', '1', 'B'],
 * ['B', '1', 'X', '1', 'B'],
 * ['B', '1', '1', '1', 'B'],
 * ['B', 'B', 'B', 'B', 'B']]
 * 解释:
 * 注意：
 * 输入矩阵的宽和高的范围为 [1,50]。
 * 点击的位置只能是未被挖出的方块 ('M' 或者 'E')，这也意味着面板至少包含一个可点击的方块。
 * 输入面板不会是游戏结束的状态（即有地雷已被挖出）。
 * 简单起见，未提及的规则在这个问题中可被忽略。例如，当游戏结束时你不需要挖出所有地雷，考虑所有你可能赢得游戏或标记方块的情况。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/20 0:06
 */
public class UpdateBoard {
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        // 1. 如果点击位置是一个雷，则该位置变为X返回数组
        // 2. 如果不是雷计算其周围雷数量，则进行迭代
        if (board[x][y] == 'M') {
            board[x][y] = 'X';
        } else {
            //dfs(board, board.length, board[0].length, x, y);
            bfs(board, board.length, board[0].length, x, y);
        }
        return board;
    }

    /**
     * board上i、j位置周围的雷数
     *
     * @param board 矩阵
     * @param m     m上限
     * @param n     n上限
     * @param x     x坐标
     * @param y     y坐标
     * @return 周围雷数量
     */
    private int aroundMine(char[][] board, int m, int n, int x, int y) {
        int count = 0;
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * dfs
     */
    private void dfs(char[][] board, int m, int n, int x, int y) {
        int count = aroundMine(board, m, n, x, y);
        // 2.1 如果雷的数量大于0，则设置其为该数字字符，返回数组
        if (count > 0) {
            board[x][y] = (char) (count + '0');
            return;
        }
        // 2.2 如果雷的数量等于0，则设置其为B，然后进一步迭代
        board[x][y] = 'B';
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                if (i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'E') {
                    dfs(board, m, n, i, j);
                }
            }
        }
    }

    /**
     * bfs 注意一定要visited标记，否则同一点会加入队列多次，超出内存限制
     */
    private void bfs(char[][] board, int m, int n, int x, int y) {
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] ij = queue.poll();
            int count = aroundMine(board, m, n, ij[0], ij[1]);
            // 2.1 如果雷的数量大于0，则设置其为该数字字符，继续下一个点
            if (count > 0) {
                board[ij[0]][ij[1]] = (char) (count + '0');
                continue;
            }
            // 2.2 如果雷的数量等于0，则设置其为B，然后进一步迭代
            board[ij[0]][ij[1]] = 'B';
            for (int i = ij[0] - 1; i <= ij[0] + 1; i++) {
                for (int j = ij[1] - 1; j <= ij[1] + 1; j++) {
                    if (i >= 0 && i < m && j >= 0 && j < n && board[i][j] == 'E') {
                        queue.offer(new int[]{i, j});
                        board[i][j] = '*';
                    }
                }
            }
        }
    }
}
