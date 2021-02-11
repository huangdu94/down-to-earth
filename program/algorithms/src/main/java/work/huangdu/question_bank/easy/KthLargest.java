package work.huangdu.question_bank.easy;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 703. 数据流中的第 K 大元素
 * 设计一个找到数据流中第 k 大元素的类（class）。注意是排序后的第 k 大元素，不是第 k 个不同的元素。
 * 请实现 KthLargest 类：
 * KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象。
 * int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素。
 * 示例：
 * 输入：
 * ["KthLargest", "add", "add", "add", "add", "add"]
 * [[3, [4, 5, 8, 2]], [3], [5], [10], [9], [4]]
 * 输出：
 * [null, 4, 5, 5, 8, 8]
 * 解释：
 * KthLargest kthLargest = new KthLargest(3, [4, 5, 8, 2]);
 * kthLargest.add(3);   // return 4
 * kthLargest.add(5);   // return 5
 * kthLargest.add(10);  // return 5
 * kthLargest.add(9);   // return 8
 * kthLargest.add(4);   // return 8
 * 提示：
 * 1 <= k <= 10^4
 * 0 <= nums.length <= 10^4
 * -10^4 <= nums[i] <= 10^4
 * -10^4 <= val <= 10^4
 * 最多调用 add 方法 10^4 次
 * 题目数据保证，在查找第 k 大元素时，数组中至少有 k 个元素
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/11
 */
public class KthLargest {
    private final int k;
    private final int[] nums;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        int n = nums.length;
        quickSort(nums, 0, n - 1);
        this.nums = Arrays.copyOf(nums, k);
        if (n < k) {
            // Arrays.fill(this.nums, n, k, Integer.MIN_VALUE);
            for (int i = n; i < k; i++) {
                this.nums[i] = Integer.MIN_VALUE;
            }
        }
    }

    public int add(int val) {
        int index = binarySearch(nums, val);
        if (index < 0) {
            index = -index - 1;
        }
        if (k - 1 - index >= 0) {
            System.arraycopy(nums, index, nums, index + 1, k - 1 - index);
            nums[index] = val;
        }
        return nums[k - 1];
    }

    private static void quickSort(int[] arr, int l, int r) {
        if (l >= r) return;
        int pivot = arr[l];
        int i = l, j = r;
        while (i < j) {
            // 一定要先右再左
            while (i < j && arr[j] <= pivot)
                j--;
            while (i < j && arr[i] >= pivot)
                i++;
            if (i < j) {
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // 基准数归位
        arr[l] = arr[i];
        arr[i] = pivot;
        quickSort(arr, l, i);
        quickSort(arr, i + 1, r);
    }

    private int binarySearch(int[] a, int key) {
        int low = 0;
        int high = a.length - 1;
        while (low <= high) {
            int mid = (low + high) >>> 1;
            int midVal = a[mid];

            if (midVal > key)
                low = mid + 1;
            else if (midVal < key)
                high = mid - 1;
            else
                return mid; // key found
        }
        return -(low + 1);  // key not found.
    }

    public static void main(String[] args) {
        KthLargest kl = new KthLargest(3, new int[]{4, 5, 8, 2});
        System.out.println(kl.add(3));
    }
}

class KthLargest2 {
    private final PriorityQueue<Integer> queue;
    private final int k;

    public KthLargest2(int k, int[] nums) {
        this.k = k;
        this.queue = new PriorityQueue<>();
        for (int x : nums) {
            add(x);
        }
    }

    public int add(int val) {
        queue.offer(val);
        if (queue.size() > k) {
            queue.poll();
        }
        return queue.element();
    }
}