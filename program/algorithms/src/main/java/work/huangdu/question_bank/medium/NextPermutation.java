package work.huangdu.question_bank.medium;

/**
 * 31. 下一个排列
 * 实现获取下一个排列的函数，算法需要将给定数字序列重新排列成字典序中下一个更大的排列。
 * 如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。
 * 必须原地修改，只允许使用额外常数空间。
 * 以下是一些例子，输入位于左侧列，其相应输出位于右侧列。
 * 1,2,3 → 1,3,2
 * 3,2,1 → 1,2,3
 * 1,1,5 → 1,5,1
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/10 12:22
 */
public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int n = nums.length, start = n - 1;
        // 1. 从后往前找到第一个降序位置（从前往后看是升序）
        while (start != 0 && nums[start - 1] >= nums[start]) {
            start--;
        }
        // 2. 如果该位置是0，则翻转nums
        if (start == 0) {
            reverse(nums, 0, n - 1);
        } else {
            // 3. 从后往前找第一个大于start-1位置的数的位置
            int needChange = nums[start - 1], end = n - 1;
            while (nums[end] <= needChange) {
                end--;
            }
            // 4. 交换needChange和end位置的数
            swap(nums, start - 1, end);
            // 5. 翻转从start到n-1之间的数
            reverse(nums, start, n - 1);
        }
    }

    private void reverse(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
