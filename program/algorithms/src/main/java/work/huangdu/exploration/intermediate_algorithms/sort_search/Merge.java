package work.huangdu.exploration.intermediate_algorithms.sort_search;

import java.util.Arrays;

/**
 * 56. 合并区间
 * 给出一个区间的集合，请合并所有重叠的区间。
 * 示例 1:
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * 解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 * 示例 2:
 * 输入: [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/12 15:50
 */
public class Merge {
    public int[][] merge(int[][] intervals) {
        if (intervals == null || intervals.length <= 1)
            return intervals;
        // 快速排序o(n log n)
        quickSort(intervals, 0, intervals.length - 1);
        // 遍历合并o(n)
        int[][] result = new int[intervals.length][];
        int count = 0;
        int[] pre = intervals[0];
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            //if (canMerge(pre, cur)) {
            if (cur[0] <= pre[1]) {
                pre[1] = Math.max(pre[1], cur[1]);
                //pre = new int[]{pre[0], Math.max(pre[1], cur[1])};
            } else {
                result[count++] = pre;
                pre = cur;
            }
        }
        result[count++] = pre;
        return Arrays.copyOfRange(result, 0, count);
    }

    private void quickSort(int[][] intervals, int l, int r) {
        if (l >= r) return;
        int[] pivot = intervals[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && intervals[j][0] >= pivot[0])
                j--;
            while (i < j && intervals[i][0] <= pivot[0])
                i++;
            if (i < j) {
                int[] temp = intervals[j];
                intervals[j] = intervals[i];
                intervals[i] = temp;
            }
        }
        intervals[l] = intervals[i];
        intervals[i] = pivot;
        quickSort(intervals, l, i - 1);
        quickSort(intervals, i + 1, r);
    }

    private boolean canMerge(int[] pre, int[] cur) {
        int x1 = pre[0] - (cur[1] - cur[0]);
        int x2 = pre[1];
        return cur[0] >= x1 && cur[0] <= x2;
    }

    public static void main(String[] args) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] result = new Merge().merge(intervals);
        System.out.println(Arrays.deepToString(result));
        int[][] intervals2 = {{2, 3}, {2, 2}, {3, 3}, {1, 3}, {5, 7}, {2, 2}, {4, 6}};
        Merge merge = new Merge();
        System.out.println(Arrays.deepToString(merge.merge2(intervals2)));
    }

    public int[][] merge2(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) return intervals;
        // 1. intervals按int[][0]升序排序
        //Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        quickSort(intervals, 0, n - 1);
        int len = 0;
        int[][] res = new int[n][];
        // 2. 合并过程
        for (int[] interval : intervals) {
            if (len == 0 || res[len - 1][1] < interval[0]) {
                res[len++] = interval;
            } else {
                res[len - 1][1] = Math.max(res[len - 1][1], interval[1]);
            }
        }
        return Arrays.copyOf(res, len);
    }
}
