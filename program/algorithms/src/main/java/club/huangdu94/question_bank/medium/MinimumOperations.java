package club.huangdu94.question_bank.medium;

/**
 * LCP 19. 秋叶收藏集
 * 小扣出去秋游，途中收集了一些红叶和黄叶，他利用这些叶子初步整理了一份秋叶收藏集 leaves， 字符串 leaves 仅包含小写字符 r 和 y， 其中字符 r 表示一片红叶，字符 y 表示一片黄叶。
 * 出于美观整齐的考虑，小扣想要将收藏集中树叶的排列调整成「红、黄、红」三部分。每部分树叶数量可以不相等，但均需大于等于 1。每次调整操作，小扣可以将一片红叶替换成黄叶或者将一片黄叶替换成红叶。请问小扣最少需要多少次调整操作才能将秋叶收藏集调整完毕。
 * 示例 1：
 * 输入：leaves = "rrryyyrryyyrr"
 * 输出：2
 * 解释：调整两次，将中间的两片红叶替换成黄叶，得到 "rrryyyyyyyyrr"
 * 示例 2：
 * 输入：leaves = "ryr"
 * 输出：0
 * 解释：已符合要求，不需要额外操作
 * 提示：
 * 3 <= leaves.length <= 10^5
 * leaves 中只包含字符 'r' 和字符 'y'
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/1 10:57
 */
public class MinimumOperations {
    public int minimumOperations3(String leaves) {
        char[] chars = leaves.toCharArray();
        int y = 0;
        // 1.统计leaves中y的数量
        for (char c : chars) {
            if (c == 'y') {
                y++;
            }
        }
        int r = chars.length - y;
        // 2.统计从index 1->chars.length-2 中需要改变的数量
        int i = 1, j = chars.length - 2, minvalue;
        if (chars[0] == 'r' && chars[chars.length - 1] == 'r') {
            r -= 2;
            minvalue = r;
        } else if (chars[0] == 'r' || chars[chars.length - 1] == 'r') {
            r -= 1;
            minvalue = r + 1;
        } else {
            minvalue = r + 2;
        }
        // 3.把必定会减小结果的部分处理完
        while (i < j && chars[i] == 'r') {
            r--;
            minvalue--;
            i++;
        }
        while (i < j && chars[j] == 'r') {
            r--;
            minvalue--;
            j--;
        }
        int add = 0, rBack = r, min = minvalue, addBack = 0;
        while (i < j) {
            int j0 = j;
            while (i < j0) {
                while (i < j0 && chars[j0] == 'y') {
                    add++;
                    j0--;
                }
                if (add >= r) break;
                while (i < j0 && chars[j0] == 'r') {
                    add--;
                    r--;
                    j0--;
                }
                if (add < 0) {
                    if (min + add < minvalue) {
                        minvalue = min + add;
                    }
                }
            }
            add = addBack;
            r = rBack;
            boolean flag = true;
            do {
                while (i < j && chars[i] == 'y') {
                    add++;
                    i++;
                }
                if (add >= r) {
                    flag = false;
                    break;
                }
                while (i < j && chars[i] == 'r') {
                    add--;
                    r--;
                    rBack--;
                    i++;
                }
            } while (add >= 0);
            if (!flag) break;
            addBack = add;
            if (min + add < minvalue) {
                minvalue = min + add;
            }
        }
        return minvalue;
    }

    public static void main(String[] args) {
        MinimumOperations operations = new MinimumOperations();
        String leaves = "rrryyyrryyyrr";
        System.out.println(operations.minimumOperations(leaves));
    }

    public int minimumOperations(String leaves) {
        int n = leaves.length();
        int[][] dp = new int[n][3];
        char[] chars = leaves.toCharArray();
        dp[0][0] = chars[0] == 'y' ? 1 : 0;
        dp[0][1] = dp[0][2] = dp[1][2] = Integer.MAX_VALUE;
        for (int i = 1; i < n; i++) {
            int isYellow = chars[i] == 'y' ? 1 : 0;
            int isRed = (isYellow + 1) & 1;
            dp[i][0] = dp[i - 1][0] + isYellow;
            dp[i][1] = Math.min(dp[i - 1][0], dp[i - 1][1]) + isRed;
            if (i >= 2) {
                dp[i][2] = Math.min(dp[i - 1][1], dp[i - 1][2]) + isYellow;
            }
        }
        return dp[n - 1][2];
    }
}
