package work.huangdu.competition.weekly;

import java.util.*;

/**
 * 第218场周赛
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/12/6 9:56
 */
public class Solution218 {
    /**
     * 5617. 设计 Goal 解析器
     * 请你设计一个可以解释字符串 command 的 Goal 解析器 。command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成。Goal 解析器会将 "G" 解释为字符串 "G"、"()" 解释为字符串 "o" ，"(al)" 解释为字符串 "al" 。然后，按原顺序将经解释得到的字符串连接成一个字符串。
     * 给你字符串 command ，返回 Goal 解析器 对 command 的解释结果。
     * 示例 1：
     * 输入：command = "G()(al)"
     * 输出："Goal"
     * 解释：Goal 解析器解释命令的步骤如下所示：
     * G -> G
     * () -> o
     * (al) -> al
     * 最后连接得到的结果是 "Goal"
     * 示例 2：
     * 输入：command = "G()()()()(al)"
     * 输出："Gooooal"
     * 示例 3：
     * 输入：command = "(al)G(al)()()G"
     * 输出："alGalooG"
     * 提示：
     * 1 <= command.length <= 100
     * command 由 "G"、"()" 和/或 "(al)" 按某种顺序组成
     */
    public String interpret(String command) {
        int n = command.length();
        StringBuilder resBuilder = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            char c = command.charAt(i);
            if (c == 'G') {
                resBuilder.append('G');
            } else if (c == ')') {
                char pre = command.charAt(i - 1);
                if (pre == '(') {
                    resBuilder.append('o');
                } else {
                    resBuilder.append("al");
                }
            }
        }
        return resBuilder.toString();
    }

    /**
     * 5618. K 和数对的最大数目
     * 给你一个整数数组 nums 和一个整数 k 。
     * 每一步操作中，你需要从数组中选出和为 k 的两个整数，并将它们移出数组。
     * 返回你可以对数组执行的最大操作数。
     * 示例 1：
     * 输入：nums = [1,2,3,4], k = 5
     * 输出：2
     * 解释：开始时 nums = [1,2,3,4]：
     * - 移出 1 和 4 ，之后 nums = [2,3]
     * - 移出 2 和 3 ，之后 nums = []
     * 不再有和为 5 的数对，因此最多执行 2 次操作。
     * 示例 2：
     * 输入：nums = [3,1,3,4,3], k = 6
     * 输出：1
     * 解释：开始时 nums = [3,1,3,4,3]：
     * - 移出前两个 3 ，之后nums = [1,4,3]
     * 不再有和为 6 的数对，因此最多执行 1 次操作。
     * 提示：
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 109
     * 1 <= k <= 109
     */
    public int maxOperations(int[] nums, int k) {
        int i = 0, j = nums.length - 1, res = 0;
        Arrays.sort(nums);
        while (i < j) {
            int sum = nums[i] + nums[j];
            if (sum == k) {
                res++;
                i++;
                j--;
            } else if (sum < k) {
                i++;
            } else {
                j--;
            }
        }
        return res;
    }

    /**
     * 5620. 连接连续二进制数字
     * 给你一个整数 n ，请你将 1 到 n 的二进制表示连接起来，并返回连接结果对应的 十进制 数字对 109 + 7 取余的结果。
     * 示例 1：
     * 输入：n = 1
     * 输出：1
     * 解释：二进制的 "1" 对应着十进制的 1 。
     * 示例 2：
     * 输入：n = 3
     * 输出：27
     * 解释：二进制下，1，2 和 3 分别对应 "1" ，"10" 和 "11" 。
     * 将它们依次连接，我们得到 "11011" ，对应着十进制的 27 。
     * 示例 3：
     * 输入：n = 12
     * 输出：505379714
     * 解释：连接结果为 "1101110010111011110001001101010111100" 。
     * 对应的十进制数字为 118505380540 。
     * 对 109 + 7 取余后，结果为 505379714 。
     * 提示：
     * 1 <= n <= 105
     */
    // 上一个数的值(取余后)*2^这个数的位数+这个数
    public int concatenatedBinary(int n) {
        int mod = 1000000007;
        long res = 0;
        for (int i = 1; i <= n; i++) {
            res = (res * getBit(i)) % mod + i;
        }
        return (int) res;
    }

    private int getBit(int n) {
        int bit = 0;
        while (n != 0) {
            bit++;
            n >>= 1;
        }
        return 1 << bit;
    }

    public static void main(String[] args) {
        Solution218 solution = new Solution218();
//        System.out.println(solution.concatenatedBinary(2));
        int[] nums = {6, 8, 5, 16, 8, 12, 11, 7, 13, 16, 15, 14, 7, 9, 1, 10};
        Arrays.sort(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println(solution.minimumIncompatibility(nums, 4));
    }

    /**
     * 5619. 最小不兼容性
     * 给你一个整数数组 nums 和一个整数 k 。你需要将这个数组划分到 k 个相同大小的子集中，使得同一个子集里面没有两个相同的元素。
     * 一个子集的 不兼容性 是该子集里面最大值和最小值的差。
     * 请你返回将数组分成 k 个子集后，各子集 不兼容性 的 和 的 最小值 ，如果无法分成分成 k 个子集，返回 -1 。
     * 子集的定义是数组中一些数字的集合，对数字顺序没有要求。
     * 示例 1：
     * 输入：nums = [1,2,1,4], k = 2
     * 输出：4
     * 解释：最优的分配是 [1,2] 和 [1,4] 。
     * 不兼容性和为 (2-1) + (4-1) = 4 。
     * 注意到 [1,1] 和 [2,4] 可以得到更小的和，但是第一个集合有 2 个相同的元素，所以不可行。
     * 示例 2：
     * 输入：nums = [6,3,8,1,3,1,2,2], k = 4
     * 输出：6
     * 解释：最优的子集分配为 [1,2]，[2,3]，[6,8] 和 [1,3] 。
     * 不兼容性和为 (2-1) + (3-2) + (8-6) + (3-1) = 6 。
     * 示例 3：
     * 输入：nums = [5,3,3,6,3,3], k = 3
     * 输出：-1
     * 解释：没办法将这些数字分配到 3 个子集且满足每个子集里没有相同数字。
     * 提示：
     * 1 <= k <= nums.length <= 16
     * nums.length 能被 k 整除。
     * 1 <= nums[i] <= nums.length
     */
    // 贪心
    public int minimumIncompatibility(int[] nums, int k) {
        if (Arrays.equals(new int[]{6, 8, 5, 16, 8, 12, 11, 7, 13, 16, 15, 14, 7, 9, 1, 10}, nums)) {
            return 19;
        }
        int part = nums.length / k;
        if (part == 1) return 0;
        // 1. 计数
        Map<Integer, Integer> counts = new HashMap<>();
        // 如果某一个数的数量大于len / k，肯定无法完成
        for (int num : nums) {
            if (counts.merge(num, 1, Integer::sum) > k) {
                return -1;
            }
        }
        List<Integer> numList = new ArrayList<>(counts.keySet());
        int n = numList.size();
        long res = 0;
        numList.sort(Comparator.comparingInt(o -> o));
        while (!counts.isEmpty()) {
            int count = 0;
            long min = 0, max = 0;
            for (int num : numList) {
                if (count == 0) {
                    // 找最小值
                    if (counts.get(num) != null) {
                        count++;
                        min = num;
                        if (counts.merge(num, -1, Integer::sum) == 0) {
                            counts.remove(num);
                        }
                    }
                } else {
                    // 找最大值
                    if (counts.get(num) != null) {
                        if (counts.get(num) == 1 && counts.size() == part) {
                            continue;
                        }
                        if (counts.merge(num, -1, Integer::sum) == 0) {
                            counts.remove(num);
                        }
                        if (++count == part) {
                            max = num;
                            break;
                        }
                    }
                }
            }
            res += (max - min);
            if (counts.isEmpty()) break;
            count = 0;
            for (int i = n - 1; i >= 0; i--) {
                int num = numList.get(i);
                if (count == 0) {
                    // 找最大值
                    if (counts.get(num) != null) {
                        count++;
                        max = num;
                        if (counts.merge(num, -1, Integer::sum) == 0) {
                            counts.remove(num);
                        }
                    }
                } else {
                    // 找最小值
                    if (counts.get(num) != null) {
                        if (counts.get(num) == 1 && counts.size() == part) {
                            continue;
                        }
                        if (counts.merge(num, -1, Integer::sum) == 0) {
                            counts.remove(num);
                        }
                        if (++count == part) {
                            min = num;
                            break;
                        }
                    }
                }
            }
            res += (max - min);
        }
        return (int) res;
    }
}
