package work.huangdu.competition.weekly;

/**
 * 第219场周赛
 *
 * @author duhuang@iflytek.com
 * @version 2020/12/13 9:58
 */
public class Solution219 {
    public int numberOfMatches(int n) {
        int count = 0;
        while (n > 1) {
            count += (n / 2);
            n = n / 2 + n % 2;
        }
        return count;
    }

    public int minPartitions(String s) {
        if (s.charAt(0) == '0') return 1;
        int[] stack = new int[9];
        int top = 0, n = s.length();
        for (int i = 0; i < n; i++) {
            int num = s.charAt(i) - '0';
            if (top == 0 || stack[top - 1] < num) {
                stack[top++] = num;
            }
            if (num == 9) break;
        }
        int result = stack[0];
        for (int i = 1; i < top; i++) {
            int cur = stack[i] - result;
            if (cur <= 0) return result;
            result += cur;
        }
        return result;
    }

    public int stoneGameVII(int[] stones) {
        int sum = 0;
        for (int stone : stones) {
            sum += stone;
        }
        memo = new int[stones.length][stones.length];
        return helper(stones, 0, stones.length - 1, sum);
    }

    private int[][] memo;

    /**
     * 选择最优方案
     */
    private int helper(int[] stones, int start, int end, int sum) {
        if (start == end) {
            return 0;
        }
        if (memo[start][end] != 0) {
            return memo[start][end];
        }
        int head = (sum - stones[start]) - helper(stones, start + 1, end, sum - stones[start]);
        int tail = (sum - stones[end]) - helper(stones, start, end - 1, sum - stones[end]);
        return memo[start][end] = Math.max(head, tail);
    }

    public int maxHeight(int[][] cuboids) {
        return 0;
    }

    public static void main(String[] args) {
        Solution219 solution = new Solution219();
        int[] stones = {5, 3, 1, 4, 2};
        System.out.println(solution.stoneGameVII(stones));
    }
}