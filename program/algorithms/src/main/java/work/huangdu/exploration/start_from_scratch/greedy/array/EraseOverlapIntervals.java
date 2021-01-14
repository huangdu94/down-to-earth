package work.huangdu.exploration.start_from_scratch.greedy.array;

import java.util.Arrays;

/**
 * 435. 无重叠区间
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 * 注意:
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * 输入: [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * 输入: [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/11/14 12:20
 */
public class EraseOverlapIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1) return 0;
        // 1. 排序，先按start升序排序，如果start相同再按end升序排序
        Arrays.sort(intervals, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]);
        int n = intervals.length, count = 0, start = intervals[0][0], end = intervals[0][1];
        // 2. 筛选
        for (int i = 1; i < n; i++) {
            if (start == intervals[i][0]) {
                // 2.1 如果start相同，则根据贪心没有任何理由保留后面的(cur的end更大或相等，因为排序过了)
                count++;
            } else if (intervals[i][0] < end && end < intervals[i][1]) {
                // 2.2 如过当前start更大，且小于之前end，但end又比之前更大，因为它们两必删除一个，所以选择删后面的
                count++;
            } else {
                // 2.3 如过当前start更大，且小于之前end，但end又小于等于之前，因为它们两必删除一个，所以选择删前面的
                if (intervals[i][0] < end) count++;
                // 2.4 如果当前start大于等于之前end，则不需要删除
                start = intervals[i][0];
                end = intervals[i][1];
            }
        }
        return count;
    }
}
