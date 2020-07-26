package club.huangdu94.algorithm_middle.sortsearch;

import java.util.Arrays;

/**
 * 合并区间
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
        if (l >= r)
            return;
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
    }
}
