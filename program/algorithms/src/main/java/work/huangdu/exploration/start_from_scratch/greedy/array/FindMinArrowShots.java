package work.huangdu.exploration.start_from_scratch.greedy.array;

/**
 * 452. 用最少数量的箭引爆气球
 * 在二维空间中有许多球形的气球。对于每个气球，提供的输入是水平方向上，气球直径的开始和结束坐标。由于它是水平的，所以纵坐标并不重要，因此只要知道开始和结束的横坐标就足够了。开始坐标总是小于结束坐标。
 * 一支弓箭可以沿着 x 轴从不同点完全垂直地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被引爆。可以射出的弓箭的数量没有限制。 弓箭一旦被射出之后，可以无限地前进。我们想找到使得所有气球全部被引爆，所需的弓箭的最小数量。
 * 给你一个数组 points ，其中 points [i] = [xstart,xend] ，返回引爆所有气球所必须射出的最小弓箭数。
 * 示例 1：
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：对于该样例，x = 6 可以射爆 [2,8],[1,6] 两个气球，以及 x = 11 射爆另外两个气球
 * 示例 2：
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * 示例 3：
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 示例 4：
 * 输入：points = [[1,2]]
 * 输出：1
 * 示例 5：
 * 输入：points = [[2,3],[2,3]]
 * 输出：1
 * 提示：
 * 0 <= points.length <= 10^4
 * points[i].length == 2
 * -2^31 <= xstart < xend <= 2^31 - 1
 *
 * @author duhuang@iflytek.com
 * @version 2020/11/13 12:36
 */
public class FindMinArrowShots {
    public int findMinArrowShots(int[][] points) {
        int n = points.length;
        if (n <= 1) return n;
        int count = 1;
        // 按照end位置从小到大排序
        quickSort(points, 0, n - 1);
//        int[] cur = points[0];
//        for (int i = 1; i < n; i++) {
//            int[] res = intersection(cur, points[i]);
//            if (res == null) {
//                count++;
//                cur = points[i];
//            } else {
//                cur = res;
//            }
//        }
        int end = points[0][1];
        for (int i = 1; i < n; i++) {
            if (points[i][0] > end) {
                count++;
                end = points[i][1];
            }
        }
        return count;
    }

    /**
     * 如果没有交集返回null
     *
     * @param p1 point1
     * @param p2 point2
     * @return p1和p2的交集
     */
    private int[] intersection(int[] p1, int[] p2) {
        if (p1[1] < p2[0]) return null;
        return new int[]{Math.max(p1[0], p2[0]), Math.min(p1[1], p2[1])};
    }

    private void quickSort(int[][] points, int l, int r) {
        if (l >= r) return;
        int[] pivot = points[l];
        int i = l, j = r;
        while (i < j) {
            while (i < j && points[j][1] >= pivot[1])
                j--;
            while (i < j && points[i][1] <= pivot[1])
                i++;
            if (i < j) {
                int[] temp = points[j];
                points[j] = points[i];
                points[i] = temp;
            }
        }
        points[l] = points[i];
        points[i] = pivot;
        quickSort(points, l, i - 1);
        quickSort(points, i + 1, r);
    }
}
