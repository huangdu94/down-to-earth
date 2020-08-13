package club.huangdu94.exploration.intermediate_algorithms.recall;

/**
 * 单词搜索
 * 给定一个二维网格和一个单词，找出该单词是否存在于网格中。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 * 示例:
 * board =
 * [
 * ['A','B','C','E'],
 * ['S','F','C','S'],
 * ['A','D','E','E']
 * ]
 * 给定 word = "ABCCED", 返回 true
 * 给定 word = "SEE", 返回 true
 * 给定 word = "ABCB", 返回 false
 * 提示：
 * board 和 word 中只包含大写和小写英文字母。
 * 1 <= board.length <= 200
 * 1 <= board[i].length <= 200
 * 1 <= word.length <= 10^3
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/9 0:24
 */
public class Exist {
    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int coj = board[0].length;
        int len = word.length();
        char[] wordArr = word.toCharArray();
        boolean[][] haveStep = new boolean[row][coj];
        for (int i = 0; i < row; i++)
            for (int j = 0; j < coj; j++)
                if (board[i][j] == wordArr[0]) {
                    if (this.dfs(board, haveStep, row, coj, i, j, wordArr, len, 0))
                        return true;
                }
        return false;
    }

    private boolean dfs(char[][] board, boolean[][] haveStep, int row, int coj, int i, int j, char[] word, int len, int index) {
        if (i < 0 || i > row - 1 || j < 0 || j > coj - 1 || haveStep[i][j] || board[i][j] != word[index])
            return false;
        if (index == len - 1)
            return true;
        haveStep[i][j] = true;
        if (this.dfs(board, haveStep, row, coj, i - 1, j, word, len, index + 1) ||
                this.dfs(board, haveStep, row, coj, i + 1, j, word, len, index + 1) ||
                this.dfs(board, haveStep, row, coj, i, j - 1, word, len, index + 1) ||
                this.dfs(board, haveStep, row, coj, i, j + 1, word, len, index + 1))
            return true;
        haveStep[i][j] = false;
        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}};
        char[][] board2 = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };
        String word = "ABCCED";
        String word2 = "SEE";
        String word3 = "ABCB";
        long start = System.currentTimeMillis();
        boolean result = false;
        //result = new Exist().exist(board, word);
        result = new Exist().exist(board2, word3);
        long end = System.currentTimeMillis();
        System.out.println("结果：" + result);
        System.out.println("时间(ms)：" + (end - start));
    }
}
