package work.huangdu.exploration.start_from_scratch.hashmap.statistics;

import java.util.*;

/**
 * 554. 砖墙
 * 你的面前有一堵矩形的、由多行砖块组成的砖墙。 这些砖块高度相同但是宽度不同。你现在要画一条自顶向下的、穿过最少砖块的垂线。
 * 砖墙由行的列表表示。 每一行都是一个代表从左至右每块砖的宽度的整数列表。
 * 如果你画的线只是从砖块的边缘经过，就不算穿过这块砖。你需要找出怎样画才能使这条线穿过的砖块数量最少，并且返回穿过的砖块数量。
 * 你不能沿着墙的两个垂直边缘之一画线，这样显然是没有穿过一块砖的。
 * 示例：
 * 输入: [[1,2,2,1],
 * *     [3,1,2],
 * *     [1,3,2],
 * *     [2,4],
 * *     [3,1,2],
 * *     [1,3,1,1]]
 * 输出: 2
 * 解释:
 * 提示：
 * 每一行砖块的宽度之和应该相等，并且不能超过 INT_MAX。
 * 每一行砖块的数量在 [1,10,000] 范围内， 墙的高度在 [1,10,000] 范围内， 总的砖块数量不超过 20,000。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/11/6 17:25
 */
public class LeastBricks {
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (List<Integer> row : wall) {
            int location = 0;
            for (int i = 0, n = row.size(); i < n - 1; i++) {
                counts.merge(location += row.get(i), 1, Integer::sum);
            }
        }
        int max = 0;
        for (int count : counts.values()) {
            if (max < count) {
                max = count;
            }
        }
        return wall.size() - max;
    }

    public static void main(String[] args) {
        LeastBricks lb = new LeastBricks();
        List<List<Integer>> list = new ArrayList<>();
        list.add(Arrays.asList(1, 2, 2, 1));
        list.add(Arrays.asList(3, 1, 2));
        list.add(Arrays.asList(1, 3, 2));
        list.add(Arrays.asList(2, 4));
        list.add(Arrays.asList(3, 1, 2));
        list.add(Arrays.asList(1, 3, 1, 1));
        System.out.println(lb.leastBricks(list));
    }
}
