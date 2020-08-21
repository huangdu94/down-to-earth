package club.huangdu94.exploration.intermediate_algorithms.sort_search;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/12 15:46
 */
public class FindKthLargest {
    public int findKthLargest(int[] nums, int k) {
        quick(nums, 0, nums.length - 1, k);
        return nums[k - 1];
    }

    private static void quick(int[] arr, int l, int r, int k) {
        if (l >= r)
            return;
        int pivot = arr[l];
        int mid = l;
        for (int i = l + 1; i < r + 1; i++) {
            if (arr[i] > pivot) {
                int temp = arr[++mid];
                arr[mid] = arr[i];
                arr[i] = temp;
            }
        }
        arr[l] = arr[mid];
        arr[mid] = pivot;
        if (k - 1 < mid)
            quick(arr, l, mid - 1, k);
        else if (k - 1 > mid)
            quick(arr, mid + 1, r, k);
    }

    public int findKthLargest4(int[] nums, int k) {
        int len = nums.length;
        buildMaxHeap(nums, len);
        for (int i = 0; i < k - 1; i++) {
            int temp = nums[0];
            nums[0] = nums[len - 1];
            nums[len - 1] = temp;
            heapify(nums, --len, 0);
        }
        return nums[0];
    }

    /**
     * 构造大顶堆
     * 根 n 左子树 2n 右子树 2n+1
     * 转换为index要减一,根 n-1 左子树 2(n-1)+1 右 2(n-1)+2
     * 由子节点index找根节点index公式:root=(child+1)/2-1
     * 找规律可得所有有子节点的index范围为 nums.length/2-1 -> 0
     */
    private static void buildMaxHeap(int[] arr, int len) {
        for (int i = len / 2 - 1; i >= 0; i--) {
            heapify(arr, len, i);
        }
    }

    /**
     * 调整以index为根的子树为大顶堆
     */
    private static void heapify(int[] arr, int len, int index) {
        int l = 2 * index + 1;
        int r = 2 * index + 2;
        int largeIndex = index;
        if (l < len && arr[l] > arr[largeIndex])
            largeIndex = l;
        if (r < len && arr[r] > arr[largeIndex])
            largeIndex = r;
        if (largeIndex != index) {
            int temp = arr[index];
            arr[index] = arr[largeIndex];
            arr[largeIndex] = temp;
            // largeIndex位置数改变,所以以其为根的子大顶堆重新调整
            heapify(arr, len, largeIndex);
        }
    }

    public int findKthLargest3(int[] nums, int k) {
        if (k < nums.length / 2) {
            for (int i = 0; i < k; i++) {
                int max = nums[i];
                int maxIndex = i;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] > max) {
                        max = nums[j];
                        maxIndex = j;
                    }
                }
                nums[maxIndex] = nums[i];
                nums[i] = max;
            }
        } else {
            k = nums.length - k + 1;
            for (int i = 0; i < k; i++) {
                int min = nums[i];
                int minIndex = i;
                for (int j = i + 1; j < nums.length; j++) {
                    if (nums[j] < min) {
                        min = nums[j];
                        minIndex = j;
                    }
                }
                nums[minIndex] = nums[i];
                nums[i] = min;
            }
        }
        return nums[k - 1];
    }

    public int findKthLargest1(int[] nums, int k) {
        Arrays.sort(nums);
        return nums[nums.length - k];
    }

    public int findKthLargest2(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int n : nums) {
            queue.add(n);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        long start = System.currentTimeMillis();
        int result = 0;
        result = new FindKthLargest().findKthLargest(nums, k);
        long end = System.currentTimeMillis();
        System.out.println("结果：" + result);
        System.out.println("时间(ms)：" + (end - start));
    }
}
