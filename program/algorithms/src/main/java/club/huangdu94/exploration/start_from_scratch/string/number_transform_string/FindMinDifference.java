package club.huangdu94.exploration.start_from_scratch.string.number_transform_string;

import java.util.List;

/**
 * 539. 最小时间差
 * 给定一个 24 小时制（小时:分钟）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 * 示例 1：
 * 输入: ["23:59","00:00"]
 * 输出: 1
 * 备注:
 * 列表中时间数在 2~20000 之间。
 * 每个时间取值在 00:00~23:59 之间。
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/26 18:08
 */
public class FindMinDifference {
    public int findMinDifference(List<String> timePoints) {
        int min = 720, first = -1, start = -1;
        boolean[] have = new boolean[1440];
        for (String timePoint : timePoints) {
            int time = Integer.parseInt(timePoint.substring(0, 2)) * 60 + Integer.parseInt(timePoint.substring(3));
            if (have[time]) return 0;
            have[time] = true;
        }
        for (int i = 0; i < 1440; i++) {
            if (have[i]) {
                if (start != -1) {
                    int interval = i - start;
                    if (interval > 720) {
                        interval = 1440 - interval;
                    }
                    if (min > interval) {
                        min = interval;
                    }
                } else {
                    first = i;
                }
                start = i;
            }
        }
        int interval = first + 1440 - start;
        if (min > interval) {
            min = interval;
        }
        return min;
    }
}
