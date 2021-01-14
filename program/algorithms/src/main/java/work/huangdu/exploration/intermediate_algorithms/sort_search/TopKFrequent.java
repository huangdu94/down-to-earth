package work.huangdu.exploration.intermediate_algorithms.sort_search;

import java.util.*;

/**
 * 347. 前K个高频元素
 * 给定一个非空的整数数组，返回其中出现频率前 k 高的元素。
 * 示例 1:
 * 输入: nums = [1,1,1,2,2,3], k = 2
 * 输出: [1,2]
 * 示例 2:
 * 输入: nums = [1], k = 1
 * 输出: [1]
 * 提示：
 * 你可以假设给定的 k 总是合理的，且 1 ≤ k ≤ 数组中不相同的元素的个数。
 * 你的算法的时间复杂度必须优于 O(n log n) , n 是数组的大小。
 * 题目数据保证答案唯一，换句话说，数组中前 k 个高频元素的集合是唯一的。
 * 你可以按任意顺序返回答案。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/7/12 15:45
 */
public class TopKFrequent {
    @SuppressWarnings("rawtypes")
    public int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        // o(n) 计数
        for (int n : nums)
            //countMap.put(n, countMap.getOrDefault(n, 0) + 1);
            countMap.merge(n, 1, Integer::sum);
        // 生成Entry数组
        // Map.Entry[] entries = countMap.entrySet().toArray(new Map.Entry[0]);
        Map.Entry[] entries = new Map.Entry[countMap.size()];
        int index = 0;
        for (Map.Entry entry : countMap.entrySet())
            entries[index++] = entry;
        // 对Entry进行按count降序 快速排序(只排包含k的那一边) O(n)?
        quickSort(entries, 0, index - 1, k);
        // 生成结果 o(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++)
            result[i] = (int) entries[i].getKey();
        return result;
    }

    @SuppressWarnings("rawtypes")
    private void quickSort(Map.Entry[] entries, int l, int r, int k) {
        if (l > r)
            return;
        Map.Entry pivot = entries[l];
        int pivotCount = (int) pivot.getValue();
        int i = l, j = r;
        while (i < j) {
            while (i < j && (int) entries[j].getValue() < pivotCount)
                j--;
            while (i < j && (int) entries[i].getValue() >= pivotCount)
                i++;
            if (i < j) {
                Map.Entry temp = entries[i];
                entries[i] = entries[j];
                entries[j] = temp;
            }
        }
        entries[l] = entries[i];
        entries[i] = pivot;
        if (i < k)
            quickSort(entries, i + 1, r, k);
        else if (i > k)
            quickSort(entries, l, i - 1, k);
    }

    /**
     * 超出内存限制
     */
    public int[] topKFrequent2(int[] nums, int k) {
        // o(n) 求最大值最小值
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++)
            if (max < nums[i])
                max = nums[i];
            else if (min > nums[i])
                min = nums[i];
        // o(n) 计数
        int[] counts = new int[max - min + 1];
        for (int n : nums)
            counts[n - min]++;
        // o(n) 构造元组数组 value count
        Tuple[] tuples = new Tuple[counts.length];
        int index = 0;
        for (int i = 0; i < counts.length; i++)
            if (counts[i] > 0)
                tuples[index++] = new Tuple(i + min, counts[i]);
        // 对Tuple进行按count降序 快速排序(只排包含k的那一边) O(n)?
        quickSort2(tuples, 0, index - 1, k);
        // 生成结果 o(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++)
            result[i] = tuples[i].value;
        return result;
    }

    public int[] topKFrequent3(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        // o(n) 计数
        for (int n : nums)
            countMap.merge(n, 1, Integer::sum);
        // o(n) 构造元组数组 value count
        Tuple[] tuples = new Tuple[countMap.size()];
        int index = 0;
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet())
            tuples[index++] = new Tuple(entry.getKey(), entry.getValue());
        // 对Tuple进行按count降序 快速排序(只排包含k的那一边) O(n)?
        quickSort2(tuples, 0, index - 1, k);
        // 生成结果 o(k)
        int[] result = new int[k];
        for (int i = 0; i < k; i++)
            result[i] = tuples[i].value;
        return result;
    }

    private void quickSort2(Tuple[] tuples, int l, int r, int k) {
        if (l > r)
            return;
        Tuple pivot = tuples[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && tuples[j].count < pivot.count)
                j--;
            while (i < j && tuples[i].count >= pivot.count)
                i++;
            if (i < j) {
                Tuple temp = tuples[i];
                tuples[i] = tuples[j];
                tuples[j] = temp;
            }
        }
        tuples[l] = tuples[i];
        tuples[i] = pivot;
        if (i < k)
            quickSort2(tuples, i + 1, r, k);
        else if (i > k)
            quickSort2(tuples, l, i - 1, k);
    }

    private static class Tuple {
        final int value;
        final int count;

        Tuple(int value, int count) {
            this.value = value;
            this.count = count;
        }
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> countMap = new HashMap<>();
        // o(n) 计数
        for (int n : nums)
            countMap.merge(n, 1, Integer::sum);
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (queue.size() == k) {
                if (queue.peek() != null && queue.peek()[1] < entry.getValue()) {
                    queue.poll();
                    queue.offer(new int[]{entry.getKey(), entry.getValue()});
                }
            } else {
                queue.offer(new int[]{entry.getKey(), entry.getValue()});
            }
        }
        int[] res = new int[k];
        for (int i = 0; i < k; i++) {
            res[i] = queue.remove()[0];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 33333333};
        int[] nums2 = {4, 1, -1, 2, -1, 2, 3};
        int[] nums3 = {5, 2, 5, 3, 5, 3, 1, 1, 3};
        int[] nums4 = {3, 0, 1, 0};
        int k = 2;
        int k2 = 1;
        int[] result = new TopKFrequent().topKFrequent3(nums2, k);
        System.out.println(Arrays.toString(result));
    }
}
