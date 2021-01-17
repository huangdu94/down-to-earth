package work.huangdu.exploration.primary_algorithms.dynamic;

/**
 * 53. 最大子序和
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 示例:
 * 输入: [-2,1,-3,4,-1,2,1,-5,4],
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 * 进阶:
 * 如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的分治法求解。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/6/29 9:23
 */
public class MaxSubArray {
    public int maxSubArray1(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new RuntimeException();
        int result = nums[0];
        if (nums.length == 1)
            return result;
        for (int i = 0; i < nums.length; i++) {
            int temp = 0;
            for (int j = i; j < nums.length; j++) {
                temp += nums[j];
                if (result < temp)
                    result = temp;
            }
        }
        return result;
    }

    public int maxSubArray2(int[] nums) {
        if (nums == null || nums.length == 0)
            throw new RuntimeException();
        int result = nums[0];
        if (nums.length == 1)
            return result;
        int temp = Math.max(nums[0], 0);
        for (int i = 1; i < nums.length; i++) {
            temp += nums[i];
            if (result < temp)
                result = temp;
            temp = Math.max(temp, 0);
        }
        return result;
    }

    private class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    private Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }

    private Status getInfo(int[] a, int l, int r) {
        if (l == r) return new Status(a[l], a[l], a[l], a[l]);
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public int maxSubArray3(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public int maxSubArray4(int[] nums) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int num : nums) {
            // 1. 求和
            sum += num;
            if (max < sum) {
                max = sum;
            }
            // 2. sum < 0 表示前面的结果可以被抛弃了
            if (sum < 0) {
                sum = 0;
            }
        }
        return max;
    }
}
