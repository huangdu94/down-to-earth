package work.huangdu.exploration.advanced_algorithms.sort_search;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 有序矩阵中第K小的元素
 * 给定一个n x n矩阵，其中每行和每列元素均按升序排序，找到矩阵中第 k 小的元素。
 * 请注意，它是排序后的第 k 小元素，而不是第 k 个不同的元素。
 * 示例：
 * matrix = [
 * [ 1,  5,  9],
 * [10, 11, 13],
 * [12, 13, 15]
 * ],
 * k = 8,
 * 返回 13。
 * 提示：
 * 你可以假设 k 的值永远是有效的，1 ≤ k ≤ n^2。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/21 11:39
 */
public class KthSmallest {
    /*
    分析：
    1. 矩阵每行每列都是升序，则将矩阵按照平行于反对角线的一条条平行线进行分层，上层的元素一定都小于等于下层的元素
    2. 每一层的数量规律，n x n矩阵，1,2,3...n...3,2,1
    3. 对于每一层上的元素，顺序是不定的，可以先定位到目标所在的层，然后再对该层排序后寻找结果
    4. 由于是确定了找第K个最小的，所以可以使用特殊的快速排序，每次只排目标区域，时间复杂度o(n)
    */
    /*
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, d_n = 2 * n, layer = 1, sum = 1;
        // 1. 找到k所在的层数
        while (k > sum) {
            layer++;
            sum += layer > n ? d_n - layer : layer;
        }
        // 2. 遍历k所在层
        // k所在层元素数量
        int count = layer > n ? d_n - layer : layer;
        // 3. 得到该层数组(分为layer>n和layer<=n两种情况)
        int[] nums = new int[count];
        if (layer <= n) {
            for (int i = 0; i < layer; i++) {
                nums[i] = matrix[i][layer - 1 - i];
            }
        } else {
            int index = 0;
            for (int i = layer - n; i < n; i++) {
                nums[index++] = matrix[i][layer - 1 - i];
            }
        }
        // 4. 对该层进行时间复杂度为o(n)的不完全快速排序
        // 转化k为该层元素升序排序后，目标数的在该层数组中的索引下标
        k = k - sum + count - 1;
        specialQuickSort(nums, 0, count - 1, k);
        // 5. 返回结果
        return nums[k];
    }*/

    private void specialQuickSort(int[] nums, int l, int r, int k) {
        if (l >= r) return;
        int pivot = nums[l], i = l, j = r;
        while (i < j) {
            while (i < j && nums[j] >= pivot) {
                j--;
            }
            while (i < j && nums[i] <= pivot) {
                i++;
            }
            if (i < j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        nums[l] = nums[i];
        nums[i] = pivot;
        if (i > k) {
            specialQuickSort(nums, l, i - 1, k);
        } else if (i < k) {
            specialQuickSort(nums, i + 1, r, k);
        }
    }

    public static void main(String[] args) {
        KthSmallest kthSmallest = new KthSmallest();
        int[][] matrix = {
                {1, 3, 5},
                {6, 7, 12},
                {11, 14, 14}
        };
        int k = 3;
        int res = kthSmallest.kthSmallest(matrix, k);
        System.out.println(res);
    }

    // 1. 直接排序后返回（时间复杂度o(n^2log n)，采用特殊的快速排序时间复杂度o(n^2)）
    public int kthSmallest1(int[][] matrix, int k) {
        int n = matrix.length, index = 0;
        int[] nums = new int[n * n];
        for (int[] row : matrix) {
            for (int num : row) {
                nums[index++] = num;
            }
        }
        specialQuickSort(nums, 0, nums.length - 1, k - 1);
        return nums[k - 1];
    }

    // 2. 维护优先级队列，时间复杂度 k*log(n)
    public int kthSmallest2(int[][] matrix, int k) {
        int n = matrix.length;
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < n; i++) {
            queue.offer(new int[]{matrix[i][0], i, 0});
        }
        for (int i = 0; i < k - 1; i++) {
            int[] poll = queue.remove();
            if (poll[2] != n - 1) {
                queue.offer(new int[]{matrix[poll[1]][poll[2] + 1], poll[1], poll[2] + 1});
            }
        }
        return queue.remove()[0];
    }

    // 3. 二分查找法 n*log(max-min)
    public int kthSmallest(int[][] matrix, int k) {
        int n = matrix.length, l = matrix[0][0], r = matrix[n - 1][n - 1];
        while (l < r) {
            int mid = l + ((r - l) >> 1);
            if (k <= lessAndEqualCount(matrix, n, mid)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    private int lessAndEqualCount(int[][] matrix, int n, int mid) {
        int i = n - 1, j = 0, count = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] <= mid) {
                count += (i + 1);
                j++;
            } else {
                i--;
            }
        }
        return count;
    }
}
