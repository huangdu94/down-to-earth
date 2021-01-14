package work.huangdu.exploration.advanced_algorithms.dynamic;

/**
 * 312. 戳气球
 * 有 n 个气球，编号为0 到 n-1，每个气球上都标有一个数字，这些数字存在数组nums中。
 * 现在要求你戳破所有的气球。如果你戳破气球 i ，就可以获得nums[left] * nums[i] * nums[right]个硬币。这里的left和right代表和i相邻的两个气球的序号。注意当你戳破了气球 i 后，气球left和气球right就变成了相邻的气球。
 * 求所能获得硬币的最大数量。
 * 说明:
 * 你可以假设nums[-1] = nums[n] = 1，但注意它们不是真实存在的所以并不能被戳破。
 * 0 ≤ n ≤ 500, 0 ≤ nums[i] ≤ 100
 * 示例:
 * 输入: [3,1,5,8]
 * 输出: 167
 * 解释: nums = [3,1,5,8] --> [3,5,8] -->   [3,8]   -->  [8]  --> []
 * coins =  3*1*5      +  3*5*8    +  1*3*8      + 1*8*1   = 167
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/4 15:20
 */
public class MaxCoins {
    /*    private List<Integer> numList;
        private Map<Integer, Integer> memo;

        public int maxCoins(int[] nums) {
            this.memo = new HashMap<>();
            this.numList = new ArrayList<>(nums.length);
            for (int num : nums) numList.add(num);
            return helper(0);
        }

        private int helper(int score) {
            int len = numList.size();
            if (len == 0) return score;
            int hash = numList.hashCode();
            if (memo.containsKey(hash)) return score + memo.get(hash);
            int max = 0;
            for (int i = 0; i < len; i++) {
                int add = (i == 0 ? 1 : numList.get(i - 1)) * numList.get(i) * (i == len - 1 ? 1 : numList.get(i + 1));
                Integer temp = numList.remove(i);
                int s = helper(add);
                numList.add(i, temp);
                if (s > max) max = s;
            }
            memo.put(hash, max);
            return score + max;
        }*/
    private int[][] memo;
    private int[] array;

    public int maxCoins1(int[] nums) {
        int len = nums.length + 2;
        this.memo = new int[len][len];
        this.array = new int[len];
        System.arraycopy(nums, 0, this.array, 1, len - 2);
        this.array[0] = 1;
        this.array[len - 1] = 1;
        return solver(0, len - 1);
    }

    private int solver(int start, int end) {
        if (start >= end - 1) return 0;
        if (memo[start][end] != 0) return memo[start][end];
        int max = 0;
        for (int i = start + 1; i < end; i++) {
            int score = array[start] * array[end] * array[i] + solver(start, i) + solver(i, end);
            if (score > max) max = score;
        }
        memo[start][end] = max;
        return max;
    }

    public int maxCoins(int[] nums) {
        int len = nums.length + 2;
        int[][] dp = new int[len][len];
        int[] array = new int[len];
        System.arraycopy(nums, 0, array, 1, len - 2);
        array[0] = 1;
        array[len - 1] = 1;
        for (int i = 2; i < len; i++) {
            for (int start = 0; start + i < len; start++) {
                int max = 0;
                for (int k = start + 1; k < start + i; k++) {
                    int score = array[start] * array[start + i] * array[k] + dp[start][k] + dp[k][start + i];
                    if (score > max) max = score;
                }
                dp[start][start + i] = max;
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        MaxCoins maxCoins = new MaxCoins();
        int[] nums = {3, 1, 5, 8};
        System.out.println(maxCoins.maxCoins(nums));
    }
}