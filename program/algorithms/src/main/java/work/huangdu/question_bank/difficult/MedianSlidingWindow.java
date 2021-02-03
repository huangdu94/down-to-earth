package work.huangdu.question_bank.difficult;

import java.util.*;

/**
 * 480. 滑动窗口中位数
 * 中位数是有序序列最中间的那个数。如果序列的大小是偶数，则没有最中间的数；此时中位数是最中间的两个数的平均数。
 * 例如：
 * [2,3,4]，中位数是 3
 * [2,3]，中位数是 (2 + 3) / 2 = 2.5
 * 给你一个数组 nums，有一个大小为 k 的窗口从最左端滑动到最右端。窗口中有 k 个数，每次窗口向右移动 1 位。你的任务是找出每次窗口移动后得到的新窗口中元素的中位数，并输出由它们组成的数组。
 * 示例：
 * 给出 nums = [1,3,-1,-3,5,3,6,7]，以及 k = 3。
 * 窗口位置                      中位数
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       1
 * 1 [3  -1  -3] 5  3  6  7      -1
 * 1  3 [-1  -3  5] 3  6  7      -1
 * 1  3  -1 [-3  5  3] 6  7       3
 * 1  3  -1  -3 [5  3  6] 7       5
 * 1  3  -1  -3  5 [3  6  7]      6
 * 因此，返回该滑动窗口的中位数数组 [1,-1,-1,3,5,6]。
 * 提示：
 * 你可以假设 k 始终有效，即：k 始终小于输入的非空数组的元素个数。
 * 与真实值误差在 10 ^ -5 以内的答案将被视作正确答案。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/3
 */
public class MedianSlidingWindow {
    // 暴力法
    public double[] medianSlidingWindow2(int[] nums, int k) {
        int n = nums.length, index = 0;
        int[] window = new int[k];
        double[] result = new double[n - k + 1];
        System.arraycopy(nums, 0, window, 0, k);
        Arrays.sort(window);
        result[index++] = getMedian(window, k);
        for (int i = k; i < n; i++) {
            replaceAndSort(window, nums[i - k], nums[i]);
            result[index++] = getMedian(window, k);
        }
        return result;
    }

    private double getMedian(int[] window, int k) {
        int mid = k >> 1;
        if ((k & 1) == 0) {
            return ((double) window[mid - 1] + window[mid]) / 2;
        }
        return window[mid];
    }

    private void replaceAndSort(int[] window, int origin, int replace) {
        int originIndex = Arrays.binarySearch(window, origin), replaceIndex = Arrays.binarySearch(window, replace);
        if (replaceIndex < 0) {
            replaceIndex = -replaceIndex - 1;
        }
        if (originIndex != replaceIndex) {
            if (originIndex < replaceIndex) {
                replaceIndex--;
                System.arraycopy(window, originIndex + 1, window, originIndex, replaceIndex - originIndex);
            } else {
                System.arraycopy(window, replaceIndex, window, replaceIndex + 1, originIndex - replaceIndex);
            }
        }
        window[replaceIndex] = replace;
    }

    private void replaceAndSort(int[] window, int k, int origin, int replace) {
        for (int i = 0; i < k; i++) {
            if (window[i] == origin) {
                window[i] = replace;
                break;
            }
        }
        Arrays.sort(window);
    }

    // 大顶堆+小顶堆+延迟删除(实现较为复杂，略)

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 2, 3, 1, 4, 2};
        int k = 3;
//        int[] nums = {-2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647, 2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648};
//        int k = 3;
        MedianSlidingWindow msw = new MedianSlidingWindow();
        double[] result = msw.medianSlidingWindow(nums, k);
        System.out.println(Arrays.toString(result));
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        DualHeap dh = new DualHeap(k);
        for (int i = 0; i < k; ++i) {
            dh.insert(nums[i]);
        }
        double[] result = new double[n - k + 1];
        result[0] = dh.getMedian();
        for (int i = k; i < n; ++i) {
            dh.insert(nums[i]);
            dh.erase(nums[i - k]);
            result[i - k + 1] = dh.getMedian();
        }
        return result;
    }

    static class DualHeap {
        // 大根堆，维护较小的一半元素
        private final PriorityQueue<Integer> small;
        // 小根堆，维护较大的一半元素
        private final PriorityQueue<Integer> large;
        // 哈希表，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
        private final Map<Integer, Integer> delayed;
        private final int k;
        // small 和 large 当前包含的元素个数，需要扣除被「延迟删除」的元素
        private int smallSize, largeSize;

        public DualHeap(int k) {
            this.small = new PriorityQueue<>(Comparator.reverseOrder());
            this.large = new PriorityQueue<>();
            this.delayed = new HashMap<>();
            this.k = k;
            this.smallSize = 0;
            this.largeSize = 0;
        }

        public double getMedian() {
            return (k & 1) == 1 ? small.peek() : ((double) small.peek() + large.peek()) / 2;
        }

        public void insert(int num) {
            if (small.isEmpty() || num <= small.peek()) {
                small.offer(num);
                ++smallSize;
            } else {
                large.offer(num);
                ++largeSize;
            }
            makeBalance();
        }

        public void erase(int num) {
            delayed.merge(num, 1, Integer::sum);
            if (num <= small.peek()) {
                --smallSize;
                if (num == small.peek()) {
                    prune(small);
                }
            } else {
                --largeSize;
                if (num == large.peek()) {
                    prune(large);
                }
            }
            makeBalance();
        }

        // 不断地弹出 heap 的堆顶元素，并且更新哈希表
        private void prune(PriorityQueue<Integer> heap) {
            while (!heap.isEmpty()) {
                int num = heap.peek();
                if (delayed.containsKey(num)) {
                    if (delayed.merge(num, -1, Integer::sum) == 0) {
                        delayed.remove(num);
                    }
                    heap.poll();
                } else {
                    break;
                }
            }
        }

        // 调整 small 和 large 中的元素个数，使得二者的元素个数满足要求
        private void makeBalance() {
            if (smallSize > largeSize + 1) {
                // small 比 large 元素多 2 个
                large.offer(small.poll());
                --smallSize;
                ++largeSize;
                // small 堆顶元素被移除，需要进行 prune
                prune(small);
            } else if (smallSize < largeSize) {
                // large 比 small 元素多 1 个
                small.offer(large.poll());
                ++smallSize;
                --largeSize;
                // large 堆顶元素被移除，需要进行 prune
                prune(large);
            }
        }
    }
}
