package club.huangdu94.exploration.advanced_algorithms.other;

import java.util.*;

/**
 * 天际线问题
 * 城市的天际线是从远处观看该城市中所有建筑物形成的轮廓的外部轮廓。现在，假设您获得了城市风光照片（图A）上显示的所有建筑物的位置和高度，请编写一个程序以输出由这些建筑物形成的天际线（图B）。
 * 每个建筑物的几何信息用三元组[Li，Ri，Hi] 表示，其中 Li 和 Ri 分别是第 i 座建筑物左右边缘的 x 坐标，Hi 是其高度。可以保证0 ≤ Li, Ri ≤ INT_MAX,0 < Hi ≤ INT_MAX 和 Ri - Li > 0。您可以假设所有建筑物都是在绝对平坦且高度为 0 的表面上的完美矩形。
 * 例如，图A中所有建筑物的尺寸记录为：[ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] 。
 * 输出是以[ [x1,y1], [x2, y2], [x3, y3], ... ] 格式的“关键点”（图B中的红点）的列表，它们唯一地定义了天际线。关键点是水平线段的左端点。请注意，最右侧建筑物的最后一个关键点仅用于标记天际线的终点，并始终为零高度。此外，任何两个相邻建筑物之间的地面都应被视为天际线轮廓的一部分。
 * 例如，图B中的天际线应该表示为：[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ]。
 * 说明:
 * 任何输入列表中的建筑物数量保证在 [0, 10000]范围内。
 * 输入列表已经按左x 坐标Li 进行升序排列。
 * 输出列表必须按 x 位排序。
 * 输出天际线中不得有连续的相同高度的水平线。例如 [...[2 3], [4 5], [7 5], [11 5], [12 7]...] 是不正确的答案；三条高度为 5 的线应该在最终输出中合并为一个：[...[2 3], [4 5], [12 7], ...]
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/8 12:44
 */
public class GetSkyline {
    private LinkedList<int[]> heightList;

    public List<List<Integer>> getSkyline(int[][] buildings) {
        this.heightList = new LinkedList<>();
        int len = buildings.length;
        if (len == 0) return new ArrayList<>();
        for (int[] building : buildings) add(building);
        List<List<Integer>> res = new ArrayList<>(heightList.size() + 1);
        int pre = -1;
        for (int[] element : heightList) {
            if (pre == element[2]) continue;
            pre = element[2];
            res.add(Arrays.asList(element[0], element[2]));
        }
        res.add(Arrays.asList(heightList.getLast()[1], 0));
        return res;
    }

    private void add(int[] building) {
        if (heightList.isEmpty()) {
            heightList.add(building);
        } else {
            ListIterator<int[]> iterator = heightList.listIterator(heightList.size());
            while (iterator.hasPrevious()) {
                if (building[0] >= iterator.previous()[1]) {
                    iterator.next();
                    break;
                }
            }
            while (iterator.hasNext()) {
                int[] next = iterator.next();
                if (building[2] > next[2]) {
                    iterator.remove();
                    if (building[0] != next[0]) {
                        iterator.add(new int[]{next[0], building[0], next[2]});
                    }
                    if (building[1] < next[1]) {
                        iterator.add(Arrays.copyOf(building, 3));
                        iterator.add(new int[]{building[1], next[1], next[2]});
                    } else {
                        iterator.add(new int[]{building[0], next[1], building[2]});
                    }
                }
                building[0] = next[1];
                if (building[1] <= next[1]) break;
            }
            if (!iterator.hasNext() && building[0] < building[1]) {
                int[] tail = heightList.getLast();
                if (tail[1] < building[0]) {
                    heightList.add(new int[]{tail[1], building[0], 0});
                }
                heightList.add(building);
            }
        }
    }

    public static void main(String[] args) {
        GetSkyline getSkyline = new GetSkyline();
        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        System.out.println(getSkyline.getSkyline(buildings));
    }
}
