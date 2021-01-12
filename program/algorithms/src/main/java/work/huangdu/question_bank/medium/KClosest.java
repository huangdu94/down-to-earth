package work.huangdu.question_bank.medium;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 973. 最接近原点的 K 个点
 * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
 * （这里，平面上两点之间的距离是欧几里德距离。）
 * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
 * 示例 1：
 * 输入：points = [[1,3],[-2,2]], K = 1
 * 输出：[[-2,2]]
 * 解释：
 * (1, 3) 和原点之间的距离为 sqrt(10)，
 * (-2, 2) 和原点之间的距离为 sqrt(8)，
 * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
 * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
 * 示例 2：
 * 输入：points = [[3,3],[5,-1],[-2,4]], K = 2
 * 输出：[[3,3],[-2,4]]
 * （答案 [[-2,4],[3,3]] 也会被接受。）
 * 提示：
 * 1 <= K <= points.length <= 10000
 * -10000 < points[i][0] < 10000
 * -10000 < points[i][1] < 10000
 *
 * @author huangdu.hd@alibaba-inc.com
 * @version 2020/11/9 16:13
 */
public class KClosest {
    // 堆，时间复杂度 o(nlog k)
    public int[][] kClosest2(int[][] points, int K) {
        Queue<int[]> closestOrigin =
                new PriorityQueue<>(K, (o1, o2) -> getDistanceSquare(o2) - getDistanceSquare(o1));
        for (int[] point : points) {
            closestOrigin.offer(point);
            if (closestOrigin.size() > K) {
                closestOrigin.poll();
            }
        }
        int n = closestOrigin.size();
        int[][] result = new int[n][];
        for (int i = 0; i < n; i++) {
            result[i] = closestOrigin.poll();
        }
        return result;
    }

    private int getDistanceSquare(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    // 特殊快速排序，时间复杂度 o(n)
    public int[][] kClosest(int[][] points, int K) {
        specialQuickSort(points, 0, points.length - 1, K);
        return Arrays.copyOf(points, K);
    }

    private void specialQuickSort(int[][] points, int l, int r, int k) {
        if (l >= r) return;
        int[] pivot = points[l];
        int pivotValue = getDistanceSquare(pivot);
        int i = l, j = r;
        while (i < j) {
            // 保证pivot右边都是比pivot大的
            while (i < j && getDistanceSquare(points[j]) > pivotValue) {
                j--;
            }
            while (i < j && getDistanceSquare(points[i]) <= pivotValue) {
                i++;
            }
            if (i < j) {
                int[] temp = points[i];
                points[i] = points[j];
                points[j] = temp;
            }
        }
        points[l] = points[i];
        points[i] = pivot;
        if (i > k)
            specialQuickSort(points, l, i - 1, k);
        else if (i < k)
            specialQuickSort(points, i + 1, r, k);
    }
}
