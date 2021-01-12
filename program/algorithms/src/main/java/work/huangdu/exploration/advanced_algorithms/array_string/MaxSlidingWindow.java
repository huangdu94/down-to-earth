package work.huangdu.exploration.advanced_algorithms.array_string;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * 239. 滑动窗口最大值
 * 给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回滑动窗口中的最大值。
 * 进阶：
 * 你能在线性时间复杂度内解决此题吗？
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/7/27 20:27
 */
public class MaxSlidingWindow {
    // 时间复杂度依然不是线性，但相比暴力解大大优化
    //最好的情况下时间复杂度为o(n-k) 最坏的情况下o(n*k-k^2)
    public int[] maxSlidingWindow(int[] nums, int k) {
        int len = nums.length - k + 1;
        int[] result = new int[len];
        int max = this.findMax(nums, 0, k - 1);
        result[0] = max;
        for (int i = 1; i < len; i++) {
            // 新来的比原max大或等于，直接更新max
            if (max <= nums[i + k - 1])
                max = nums[i + k - 1];
                // 新来的比原来的小，但原最大的不是消失的则max不变
                // 新来的比原来的小，但原最大的是消失的则max需要被重新找
            else if (max == nums[i - 1])
                max = this.findMax(nums, i, i + k - 1);
            result[i] = max;
        }
        return result;
    }

    // 暴力解 时间复杂度o(n*k-k^2)
    public int[] maxSlidingWindow2(int[] nums, int k) {
        int len = nums.length - k + 1;
        int[] result = new int[len];
        for (int i = 0; i < len; i++)
            result[i] = this.findMax(nums, i, i + k - 1);
        return result;
    }

    private int findMax(int[] nums, int l, int r) {
        int max = nums[l];
        for (int i = l + 1; i <= r; i++)
            if (max < nums[i])
                max = nums[i];
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1};
        MaxSlidingWindow maxSlidingWindow = new MaxSlidingWindow();
        System.out.println(Arrays.toString(maxSlidingWindow.maxSlidingWindow(nums, 1)));
    }

    public int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length, index = 0, len = n - k + 1;
        int[] result = new int[len];
        Deque<Integer> numQueue = new ArrayDeque<>(), indexQueue = new ArrayDeque<>();
        for (int i = 0; i < k - 1; i++) {
            int num = nums[i];
            while (!numQueue.isEmpty() && numQueue.peekLast() <= num) {
                numQueue.removeLast();
                indexQueue.removeLast();
            }
            numQueue.offer(num);
            indexQueue.offer(i);
        }
        for (int i = k - 1; i < n; i++) {
            int num = nums[i];
            // 默认从last进，从first出
            if (!indexQueue.isEmpty() && indexQueue.peek() == i - k) {
                numQueue.remove();
                indexQueue.remove();
            }
            while (!numQueue.isEmpty() && numQueue.peekLast() <= num) {
                numQueue.removeLast();
                indexQueue.removeLast();
            }
            numQueue.offer(num);
            indexQueue.offer(i);
            result[index++] = numQueue.peek();
        }
        return result;
    }

    public int[] maxSlidingWindow4(int[] nums, int k) {
        int n = nums.length, index = 0;
        int[] result = new int[n - k + 1];
        Deque<Integer> queue = new ArrayDeque<>();
        for (int i = 0; i < k - 1; i++) {
            int num = nums[i];
            while (!queue.isEmpty() && nums[queue.peekLast()] <= num) {
                queue.removeLast();
            }
            queue.offer(i);
        }
        for (int i = k - 1; i < n; i++) {
            int num = nums[i];
            // 默认从last进，从first出
            if (!queue.isEmpty() && queue.peek() == i - k) {
                queue.remove();
            }
            while (!queue.isEmpty() && nums[queue.peekLast()] <= num) {
                queue.removeLast();
            }
            queue.offer(i);
            result[index++] = nums[queue.peek()];
        }
        return result;
    }
}
