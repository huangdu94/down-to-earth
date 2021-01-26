package work.huangdu.question_bank.easy;

/**
 * 1128. 等价多米诺骨牌对的数量
 * 给你一个由一些多米诺骨牌组成的列表 dominoes。
 * 如果其中某一张多米诺骨牌可以通过旋转 0 度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
 * 形式上，dominoes[i] = [a, b] 和 dominoes[j] = [c, d] 等价的前提是 a==c 且 b==d，或是 a==d 且 b==c。
 * 在 0 <= i < j < dominoes.length 的前提下，找出满足 dominoes[i] 和 dominoes[j] 等价的骨牌对 (i, j) 的数量。
 * 示例：
 * 输入：dominoes = [[1,2],[2,1],[3,4],[5,6]]
 * 输出：1
 * 提示：
 * 1 <= dominoes.length <= 40000
 * 1 <= dominoes[i][j] <= 9
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/26
 */
public class NumEquivDominoPairs {
    public int numEquivDominoPairs2(int[][] dominoes) {
        int[] counts = new int[100];
        for (int[] domino : dominoes) {
            int a = domino[0], b = domino[1];
            if (a > b) {
                int temp = b;
                b = a;
                a = temp;
            }
            counts[a * 10 + b]++;
        }
        int pairs = 0;
        for (int[] domino : dominoes) {
            int a = domino[0], b = domino[1], c;
            if (a > b) {
                int temp = b;
                b = a;
                a = temp;
            }
            c = a * 10 + b;
            counts[c]--;
            pairs += counts[c];
        }
        return pairs;
    }

    public int numEquivDominoPairs(int[][] dominoes) {
        int[] counts = new int[100];
        for (int[] domino : dominoes) {
            int a = domino[0], b = domino[1];
            if (a > b) {
                int temp = b;
                b = a;
                a = temp;
            }
            counts[a * 10 + b]++;
        }
        int pairs = 0;
        for (int count : counts) {
            pairs += count * (count - 1) / 2;
        }
        return pairs;
    }

    public static void main(String[] args) {
        // [[1,2],[1,2],[1,1],[1,2],[2,2]]
        int[][] dominoes = {{1, 2}, {1, 2}, {1, 1}, {1, 2}, {2, 2}};
        System.out.println(new NumEquivDominoPairs().numEquivDominoPairs(dominoes));
    }
}
