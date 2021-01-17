package work.huangdu.exploration.start_from_scratch.greedy.sub_array;

/**
 * 581. 最短无序连续子数组
 * 给定一个整数数组，你需要寻找一个连续的子数组，如果对这个子数组进行升序排序，那么整个数组都会变为升序排序。
 * 你找到的子数组应是最短的，请输出它的长度。
 * 示例 1:
 * 输入: [2, 6, 4, 8, 10, 9, 15]
 * 输出: 5
 * 解释: 你只需要对 [6, 4, 8, 10, 9] 进行升序排序，那么整个表都会变为升序排序。
 * 说明 :
 * 输入的数组长度范围在 [1, 10,000]。
 * 输入的数组可能包含重复元素 ，所以升序的意思是<=。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/18 13:12
 */
public class FindUnsortedSubarray {
    public int findUnsortedSubarray(int[] nums) {
        int n = nums.length;
        // 下标[0,i]之间最大的数;下标[i,n-1]之间最小的数
        int[] leftMax = new int[n], rightMin = new int[n];
        leftMax[0] = nums[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
        }
        rightMin[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], nums[i]);
        }
        int start = 0;
        while (start + 1 < n && leftMax[start] <= rightMin[start + 1]) {
            start++;
        }
        if (start == n - 1) return 0;
        int end = n - 1;
        while (leftMax[end - 1] <= rightMin[end]) {
            end--;
        }
        return end - start + 1;
    }
}
