package club.huangdu94.specific.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author duhuang@iflytek.com
 * @version 2020/7/16 17:42
 */
public class Test {
    public static void main(String[] args) {
        // int[] nums = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        int[] nums = testData();
        // int[] nums = {1, 2, 2};
        int[] nums_copy;
        nums_copy = Arrays.copyOf(nums, nums.length);
        javaApi(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Bubble.sort(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Selection.sort(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Insertion.sort(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Shell.sort(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Merge.sort(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Quick.sort(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Quick.sort2(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Heap.sort(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Counting.sort(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Bucket.sort(nums_copy);
        nums_copy = Arrays.copyOf(nums, nums.length);
        Radix.sort(nums_copy);
    }

    /**
     * 制造测试数据
     */
    private static int[] testData() {
        int len = 20000;
        Random random = new Random();
        int[] nums = new int[len + 1];
        for (int i = 0; i < len; i++) {
            nums[i] = random.nextInt(len);
            //nums[i] = i;
        }
        // 测试数据加负数
        nums[len] = -1;
        System.out.printf("测试数据：%s%n", Arrays.toString(nums));
        return nums;
    }

    private static void javaApi(int[] nums) {
        long start = System.currentTimeMillis();
        Arrays.sort(nums);
        long end = System.currentTimeMillis();
        System.out.printf("Arrays.sort()排序耗时: %s ms%n", end - start);
        System.out.printf("排序结果: %s%n", Arrays.toString(nums));
    }
}
