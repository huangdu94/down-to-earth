package work.huangdu.exploration.start_from_scratch.array.change_move;

/**
 * 665. 非递减数列
 * 给你一个长度为 n 的整数数组，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * 我们是这样定义一个非递减数列的： 对于数组中所有的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * 示例 1:
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个4变成1来使得它成为一个非递减数列。
 * 示例 2:
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 * 说明：
 * 1 <= n <= 10 ^ 4
 * - 10 ^ 5 <= nums[i] <= 10 ^ 5
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/15 12:33
 */
public class CheckPossibility {
    public boolean checkPossibility(int[] nums) {
        int len = nums.length;
        if (len <= 2) return true;
        boolean change = false;
        for (int i = 0; i < len - 1; i++) {
            if (nums[i] > nums[i + 1]) {
                if (change) return false;
                // 两种改法：
                // 1. nums[i]=nums[i + 1] nums[i]减小 这样的话需要保证 nums[i+1]>=num[i-1]
                // 2. nums[i + 1]=nums[i] nums[i+1]增大 继续判断即可
                // 如果第一种可以优先使用第一种改法，因为第一种改法不影响后面原本可能正确的
                if (i != 0 && nums[i - 1] > nums[i + 1]) {
                    nums[i + 1] = nums[i];
                }
                change = true;
            }
        }
        return true;
    }

    public boolean checkPossibility2(int[] nums) {
        int n = nums.length;
        boolean changed = false;
        for (int i = 0; i < n - 1; i++) {
            // 遇到这种情况要么把nums[i]调小，要么把nums[i+1]调大
            // 1. 如果i=0，nums[i]可以调的无限小，根据贪心调nums[i]即可
            // 2. 如果i=n-2，nums[i+1]可以调的无限大，根据贪心调nums[i+1]即可
            // 3. 如果i的范围(0,n-2)，要保证nums[i-1]<=nums[i+1](可以把nums[i]调到nums[i-1])或nums[i]<=nums[i+2](可以把nums[i+1]调到nums[i+2])
            if (nums[i] > nums[i + 1]) {
                if (i == 0) {
                    if (changed) return false;
                } else if (i == n - 2) {
                    return !changed;
                } else {
                    if (changed || nums[i - 1] > nums[i + 1] && nums[i] > nums[i + 2]) return false;
                    // 贪心，因为nums[i]已经满足要求，所以即使可以通过调nums[i]解决，能调nums[i+1]解决的机会也不能放过
                    if (nums[i] <= nums[i + 2]) i++;
                }
                changed = true;
            }
        }
        return true;
    }
}
