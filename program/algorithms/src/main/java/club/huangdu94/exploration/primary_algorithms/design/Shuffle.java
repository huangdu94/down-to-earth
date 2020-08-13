package club.huangdu94.exploration.primary_algorithms.design;

import java.util.Arrays;
import java.util.Random;

/**
 * 打乱一个没有重复元素的数组。
 * 示例:
 * // 以数字集合 1, 2 和 3 初始化数组。
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * // 打乱数组 [1,2,3] 并返回结果。任何 [1,2,3]的排列返回的概率应该相同。
 * solution.shuffle();
 * // 重设数组到它的初始状态[1,2,3]。
 * solution.reset();
 * // 随机返回数组[1,2,3]打乱后的结果。
 * solution.shuffle();
 *
 * @author duhuang@iflytek.com
 * @version 2020/6/29 13:31
 */
public class Shuffle {
    private final int[] nums;

    public Shuffle(int[] nums) {
        if (nums == null)
            this.nums = new int[0];
        else
            this.nums = nums;
    }

    /**
     * Resets the array to its original configuration and return it.
     */
    public int[] reset() {
        return nums;
    }

    /**
     * Returns a random shuffling of the array.
     */
    public int[] shuffle() {
        new Random().nextInt(1);
        int len = nums.length;
        int[] result = new int[len];
        boolean[] flagArr = new boolean[len];
        for (int i = 0; i < len; i++) {
            int index;
            do {
                index = (int) (Math.random() * len);
            } while (flagArr[index]);
            flagArr[index] = true;
            result[i] = nums[index];
        }
        return result;
    }

    public static void main(String[] args) {
        Shuffle shuffle = new Shuffle(new int[]{1, 2, 3});
        System.out.println(Arrays.toString(shuffle.shuffle()));
        System.out.println(Arrays.toString(shuffle.reset()));
        System.out.println(Arrays.toString(shuffle.shuffle()));
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */