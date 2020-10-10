package work.huangdu.exploration.primary_algorithms.array;

/**
 * 189. 旋转数组
 * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
 * 示例 1:
 * 输入: [1,2,3,4,5,6,7] 和 k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右旋转 1 步: [7,1,2,3,4,5,6]
 * 向右旋转 2 步: [6,7,1,2,3,4,5]
 * 向右旋转 3 步: [5,6,7,1,2,3,4]
 * 示例 2:
 * 输入: [-1,-100,3,99] 和 k = 2
 * 输出: [3,99,-1,-100]
 * 解释:
 * 向右旋转 1 步: [99,-1,-100,3]
 * 向右旋转 2 步: [3,99,-1,-100]
 * 说明:
 * 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。
 * 要求使用空间复杂度为 O(1) 的 原地 算法。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/26 15:09
 */
public class Rotate {
    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2)
            return;
        k = k % nums.length;
        if (k == 0)
            return;
        int start = -1;
        int count = 0;
        while (count < nums.length) {
            int temp = nums[++start];
            int current = start;
            while (true) {
                int next = (current + nums.length - k) % nums.length;
                if (next == start) {
                    nums[current] = temp;
                    count++;
                    break;
                }
                nums[current] = nums[next];
                current = next;
                count++;
            }
        }
    }

    // 模拟旋转K次
    public void rotate1(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        for (int i = 0; i < k; i++) {
            int tail = nums[n - 1];
            System.arraycopy(nums, 0, nums, 1, n - 1);
            nums[0] = tail;
        }
    }

    // 找规律，翻转K次后，相当于前n-k个翻转，后k个翻转，再总的翻转一次
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            int temp = nums[i];
            nums[i++] = nums[j];
            nums[j--] = temp;
        }
    }
}
