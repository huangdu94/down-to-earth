package club.huangdu94.algorithm_easy.other;

/**
 * 缺失数字
 * 给定一个包含 0, 1, 2, ..., n 中 n 个数的序列，找出 0 .. n 中没有出现在序列中的那个数。
 * 示例 1:
 * 输入: [3,0,1]
 * 输出: 2
 * 示例 2:
 * 输入: [9,6,4,2,3,5,7,0,1]
 * 输出: 8
 * 说明:
 * 你的算法应具有线性时间复杂度。你能否仅使用额外常数空间来实现?
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/1 23:10
 */
public class MissingNumber {
    public int missingNumber(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] sortNums = new int[nums.length + 1];
        for (int n : nums)
            sortNums[n] = n;
        for (int i = 0; i < sortNums.length; i++) {
            if (sortNums[i] != i)
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(new MissingNumber().missingNumber(new int[]{9, 6, 4, 2, 3, 5, 7, 0, 1}));
    }
}
