package work.huangdu.exploration.advanced_algorithms.array_string;

import java.util.HashSet;
import java.util.Set;

/**
 * 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
 * 示例 1:
 * 输入: [1,3,4,2,2]
 * 输出: 2
 * 示例 2:
 * 输入: [3,1,3,4,2]
 * 输出: 3
 * 说明：
 * 不能更改原数组（假设数组是只读的）。
 * 只能使用额外的 O(1) 的空间。
 * 时间复杂度小于 O(n^2) 。
 * 数组中只有一个重复的数字，但它可能不止重复出现一次。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/7/27 19:16
 */
public class FindDuplicate {
    // 交换法 把每一个数字都交换到与之数字对应的index位置上 交换停止后输出index为0的数
    public int findDuplicate(int[] nums) {
        // num一定大于0，所以第一次交换一定能实质性发生
        // num一定小于等于n，数组长度n+1一定不会越界
        // 第二次交换就有可能找到重复数字了
        do {
            int n = nums[0];
            nums[0] = nums[n];
            nums[n] = n;
        } while (nums[nums[0]] != nums[0]);
        return nums[0];
    }

    // 其它方法 1.二分查找 2.二进制 3.快慢指针

    public int findDuplicate2(int[] nums) {
        Set<Integer> exists = new HashSet<>();
        for (int num : nums) {
            if (!exists.add(num)) {
                return num;
            }
        }
        return -1;
    }

    public int findDuplicate3(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] > 0) {
                nums[index] *= -1;
            } else {
                return index + 1;
            }
        }
        return -1;
    }

    public int findDuplicate4(int[] nums) {
        int slow = 0, fast = 0;
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                slow = 0;
                while (slow != fast) {
                    slow = nums[slow];
                    fast = nums[fast];
                }
                return slow;
            }
        }
    }
}
