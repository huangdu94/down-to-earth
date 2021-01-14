package work.huangdu.competition.biweekly;

import java.util.Arrays;

/**
 * 第 34 场双周赛
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/5 21:18
 */
public class Solution34 {
    /**
     * 5491. 矩阵对角线元素的和
     * 给你一个正方形矩阵 mat，请你返回矩阵对角线元素的和。
     * 请你返回在矩阵主对角线上的元素和副对角线上且不在主对角线上元素的和。
     * 示例  1：
     * 输入：mat = [[1,2,3],
     * *             [4,5,6],
     * *             [7,8,9]]
     * 输出：25
     * 解释：对角线的和为：1 + 5 + 9 + 3 + 7 = 25
     * 请注意，元素 mat[1][1] = 5 只会被计算一次。
     * 示例  2：
     * 输入：mat = [[1,1,1,1],
     * *            [1,1,1,1],
     * *            [1,1,1,1],
     * *           [1,1,1,1]]
     * 输出：8
     * 示例 3：
     * 输入：mat = [[5]]
     * 输出：5
     * 提示：
     * n == mat.length == mat[i].length
     * 1 <= n <= 100
     * 1 <= mat[i][j] <= 100
     */
    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int l = 0, r = n - 1, sum = 0;
        for (int[] ints : mat) {
            sum += ints[l++];
            sum += ints[r--];
        }
        if (n % 2 == 1) {
            sum -= mat[n / 2][n / 2];
        }
        return sum;
    }

    /**
     * 5492. 分割字符串的方案数
     * 给你一个二进制串 s  （一个只包含 0 和 1 的字符串），我们可以将 s 分割成 3 个 非空 字符串 s1, s2, s3 （s1 + s2 + s3 = s）。
     * 请你返回分割 s 的方案数，满足 s1，s2 和 s3 中字符 '1' 的数目相同。
     * 由于答案可能很大，请将它对 10^9 + 7 取余后返回。
     * 示例 1：
     * 输入：s = "10101"
     * 输出：4
     * 解释：总共有 4 种方法将 s 分割成含有 '1' 数目相同的三个子字符串。
     * "1|010|1"
     * "1|01|01"
     * "10|10|1"
     * "10|1|01"
     * 示例 2：
     * 输入：s = "1001"
     * 输出：0
     * 示例 3：
     * 输入：s = "0000"
     * 输出：3
     * 解释：总共有 3 种分割 s 的方法。
     * "0|0|00"
     * "0|00|0"
     * "00|0|0"
     * 示例 4：
     * 输入：s = "100100010100110"
     * 提示：
     * s[i] == '0' 或者 s[i] == '1'
     * 3 <= s.length <= 10^5
     */
    public int numWays(String s) {
        int mod = 1000000007;
        char[] chars = s.toCharArray();
        int len = chars.length, count = 0;
        for (char c : chars) {
            if (c == '1') {
                count++;
            }
        }
        if (count == 0) {
            return (int) ((long) (len - 1) * (len - 2) / 2 % mod);
        }
        if (count % 3 != 0) {
            return 0;
        }
        int first = 1, second = 1;
        int amount = count / 3, a = 0, i = 0;
        while (a != amount) {
            if (chars[i++] == '1') {
                a++;
            }
        }
        while (chars[i] != '1') {
            first++;
            i++;
        }
        a = 0;
        while (a != amount) {
            if (chars[i++] == '1') {
                a++;
            }
        }
        while (chars[i] != '1') {
            second++;
            i++;
        }
        return (int) ((long) first * second % mod);
    }

    /**
     * 5493. 删除最短的子数组使剩余数组有序
     * 给你一个整数数组 arr ，请你删除一个子数组（可以为空），使得 arr 中剩下的元素是 非递减 的。
     * 一个子数组指的是原数组中连续的一个子序列。
     * 请你返回满足题目要求的最短子数组的长度。
     * 示例 1：
     * 输入：arr = [1,2,3,10,4,2,3,5]
     * 输出：3
     * 解释：我们需要删除的最短子数组是 [10,4,2] ，长度为 3 。剩余元素形成非递减数组 [1,2,3,3,5] 。
     * 另一个正确的解为删除子数组 [3,10,4] 。
     * 示例 2：
     * 输入：arr = [5,4,3,2,1]
     * 输出：4
     * 解释：由于数组是严格递减的，我们只能保留一个元素。所以我们需要删除长度为 4 的子数组，要么删除 [5,4,3,2]，要么删除 [4,3,2,1]。
     * 示例 3：
     * 输入：arr = [1,2,3]
     * 输出：0
     * 解释：数组已经是非递减的了，我们不需要删除任何元素。
     * 示例 4：
     * 输入：arr = [1]
     * 输出：0
     * 提示：
     * 1 <= arr.length <= 10^5
     * 0 <= arr[i] <= 10^9
     */
    public int findLengthOfShortestSubarray(int[] arr) {
        int len = arr.length;
        if (len == 1) return 0;
        int headTailIndex = -1, tailHeadIndex = -1;
        for (int i = 1; i < len; i++) {
            if (arr[i] < arr[i - 1]) {
                headTailIndex = i - 1;
                break;
            }
        }
        if (headTailIndex == -1) {
            return 0;
        }
        for (int i = len - 2; i >= 0; i--) {
            if (arr[i] > arr[i + 1]) {
                tailHeadIndex = i + 1;
                break;
            }
        }
        if (arr[headTailIndex] <= arr[tailHeadIndex]) {
            return tailHeadIndex - headTailIndex - 1;
        }
        int res = tailHeadIndex;
        for (int i = headTailIndex; i >= 0; i--) {
            int headTail = arr[i];
            int j = tailHeadIndex;
            while (j < len && arr[j] < headTail) {
                j++;
            }
            res = Math.min(res, j - i - 1);
        }
        return res;
    }

    private int[][] memo;
    private int[][] map;
    private int len;
    private int finish;

    /**
     * 5494. 统计所有可行路径
     * 给你一个 互不相同 的整数数组，其中 locations[i] 表示第 i 个城市的位置。同时给你 start，finish 和 fuel 分别表示出发城市、目的地城市和你初始拥有的汽油总量
     * 每一步中，如果你在城市 i ，你可以选择任意一个城市 j ，满足  j != i 且 0 <= j < locations.length ，并移动到城市 j 。从城市 i 移动到 j 消耗的汽油量为 |locations[i] - locations[j]|，|x| 表示 x 的绝对值。
     * 请注意， fuel 任何时刻都 不能 为负，且你 可以 经过任意城市超过一次（包括 start 和 finish ）。
     * 请你返回从 start 到 finish 所有可能路径的数目。
     * 由于答案可能很大， 请将它对 10^9 + 7 取余后返回。
     * 示例 1：
     * 输入：locations = [2,3,6,8,4], start = 1, finish = 3, fuel = 5
     * 输出：4
     * 解释：以下为所有可能路径，每一条都用了 5 单位的汽油：
     * 1 -> 3
     * 1 -> 2 -> 3
     * 1 -> 4 -> 3
     * 1 -> 4 -> 2 -> 3
     * 示例 2：
     * 输入：locations = [4,3,1], start = 1, finish = 0, fuel = 6
     * 输出：5
     * 解释：以下为所有可能的路径：
     * 1 -> 0，使用汽油量为 fuel = 1
     * 1 -> 2 -> 0，使用汽油量为 fuel = 5
     * 1 -> 2 -> 1 -> 0，使用汽油量为 fuel = 5
     * 1 -> 0 -> 1 -> 0，使用汽油量为 fuel = 3
     * 1 -> 0 -> 1 -> 0 -> 1 -> 0，使用汽油量为 fuel = 5
     * 示例 3：
     * 输入：locations = [5,2,1], start = 0, finish = 2, fuel = 3
     * 输出：0
     * 解释：没有办法只用 3 单位的汽油从 0 到达 2 。因为最短路径需要 4 单位的汽油。
     * 示例 4 ：
     * 输入：locations = [2,1,5], start = 0, finish = 0, fuel = 3
     * 输出：2
     * 解释：总共有两条可行路径，0 和 0 -> 1 -> 0 。
     * 示例 5：
     * 输入：locations = [1,2,3], start = 0, finish = 2, fuel = 40
     * 输出：615088286
     * 解释：路径总数为 2615088300 。将结果对 10^9 + 7 取余，得到 615088286 。
     * 提示：
     * 2 <= locations.length <= 100
     * 1 <= locations[i] <= 10^9
     * 所有 locations 中的整数 互不相同 。
     * 0 <= start, finish < locations.length
     * 1 <= fuel <= 200
     */
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        if (start != finish && Math.abs(locations[start] - locations[finish]) > fuel) {
            return 0;
        }
        this.len = locations.length;
        this.memo = new int[len][fuel + 1];
        this.map = new int[len][len];
        this.finish = finish;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                map[i][j] = Math.abs(locations[i] - locations[j]);
                map[j][i] = map[i][j];
            }
        }
        for (int[] memoRow : memo) {
            Arrays.fill(memoRow, -1);
        }
        return helper(start, fuel);
    }

    private int helper(int cur, int fuel) {
        if (cur == finish && fuel == 0) {
            return 1;
        }
        if (map[cur][finish] > fuel) {
            return 0;
        }
        if (memo[cur][fuel] != -1) {
            return memo[cur][fuel];
        }
        int routes = cur == finish ? 1 : 0;
        for (int i = 0; i < len; i++) {
            if (i == cur) continue;
            if (fuel - map[cur][i] >= 0) {
                routes += helper(i, fuel - map[cur][i]);
                if (routes > 1000000007) {
                    routes %= 1000000007;
                }
            }
        }
        memo[cur][fuel] = routes;
        return routes;
    }

    public static void main(String[] args) {
        Solution34 solution = new Solution34();
//        int[] arr = {11, 26, 3, 14, 24, 6, 10, 16, 32, 9, 36, 24, 27, 17, 31, 32, 35, 36, 11, 22, 30};
//        System.out.println(solution.findLengthOfShortestSubarray(arr));
        int[] locations = {1, 2, 3};
        int start = 0, finish = 2, fuel = 40;
        System.out.println(solution.countRoutes(locations, start, finish, fuel));
    }
}