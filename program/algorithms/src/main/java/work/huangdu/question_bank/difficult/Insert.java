package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * 57. 插入区间
 * 给出一个无重叠的 ，按照区间起始端点排序的区间列表。
 * 在列表中插入一个新的区间，你需要确保列表中的区间仍然有序且不重叠（如果有必要的话，可以合并区间）。
 * 示例 1：
 * 输入：intervals = [[1,3],[6,9]], newInterval = [2,5]
 * 输出：[[1,5],[6,9]]
 * 示例 2：
 * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
 * 输出：[[1,2],[3,10],[12,16]]
 * 解释：这是因为新的区间 [4,8] 与 [3,5],[6,7],[8,10] 重叠。
 * 注意：输入类型已在 2019 年 4 月 15 日更改。请重置为默认代码定义以获取新的方法签名。
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/4 12:18
 */
public class Insert {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int i = 0, n = intervals.length;
        List<int[]> intervalList = new ArrayList<>(n);
        // 1. 跳过开头不需要合并的interval
        while (i < n && intervals[i][1] < newInterval[0]) {
            intervalList.add(intervals[i]);
            i++;
        }
        // 2. 合并阶段(考虑不需要合并的情况)
        if (i < n && newInterval[1] >= intervals[i][0]) {
            if (intervals[i][0] < newInterval[0]) {
                newInterval[0] = intervals[i][0];
            }
            // 如果待合并区间的start<新区间，则继续
            while (i + 1 < n && intervals[i + 1][0] <= newInterval[1]) {
                i++;
            }
            if (intervals[i][1] > newInterval[1]) {
                newInterval[1] = intervals[i][1];
            }
            i++;
        }
        intervalList.add(newInterval);
        // 3. 跳过末尾不需要合并的区间
        while (i < n) {
            intervalList.add(intervals[i]);
            i++;
        }
        // 4. 返回结果
        int[][] result = new int[intervalList.size()][2];
        for (i = 0; i < intervalList.size(); i++) {
            result[i] = intervalList.get(i);
        }
        return result;
    }
}
