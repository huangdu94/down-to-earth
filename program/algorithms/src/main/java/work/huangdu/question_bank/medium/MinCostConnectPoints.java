package work.huangdu.question_bank.medium;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1584. 连接所有点的最小费用
 * 给你一个points 数组，表示 2D 平面上的一些点，其中 points[i] = [xi, yi] 。
 * 连接点 [xi, yi] 和点 [xj, yj] 的费用为它们之间的 曼哈顿距离 ：|xi - xj| + |yi - yj| ，其中 |val| 表示 val 的绝对值。
 * 请你返回将所有点连接的最小总费用。只有任意两点之间 有且仅有 一条简单路径时，才认为所有点都已连接。
 * 示例 1：
 * 输入：points = [[0,0],[2,2],[3,10],[5,2],[7,0]]
 * 输出：20
 * 解释：
 * 我们可以按照上图所示连接所有点得到最小总费用，总费用为 20 。
 * 注意到任意两个点之间只有唯一一条路径互相到达。
 * 示例 2：
 * 输入：points = [[3,12],[-2,5],[-4,1]]
 * 输出：18
 * 示例 3：
 * 输入：points = [[0,0],[1,1],[1,0],[-1,1]]
 * 输出：4
 * 示例 4：
 * 输入：points = [[-1000000,-1000000],[1000000,1000000]]
 * 输出：4000000
 * 示例 5：
 * 输入：points = [[0,0]]
 * 输出：0
 * 提示：
 * 1 <= points.length <= 1000
 * -10^6 <= xi, yi <= 10^6
 * 所有点 (xi, yi) 两两不同。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/19
 */
public class MinCostConnectPoints {
    private int[] parents;

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        if (n == 1) return 0;
        if (n == 2) return getManhattanDistance(points[0], points[1]);
        int sum = 0, exist = 0;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        PriorityQueue<int[]> queue = new PriorityQueue<>(n, Comparator.comparingInt(o -> o[0]));
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                queue.offer(new int[]{getManhattanDistance(points[i], points[j]), i, j});
            }
        }
        while (exist < n - 1) {
            int[] element = queue.poll();
            if (union(element[1], element[2])) {
                sum += element[0];
                exist++;
            }
        }
        return sum;
    }

    private int getManhattanDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    private int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    private boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;
        parents[rootX] = rootY;
        return true;
    }

    public static void main(String[] args) {
        MinCostConnectPoints2 mccp = new MinCostConnectPoints2();
        System.out.println(mccp.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }
}

class MinCostConnectPoints2 {
    private int[] parents;

    public int minCostConnectPoints(int[][] points) {
        int n = points.length;
        if (n == 1) return 0;
        if (n == 2) return getManhattanDistance(points[0], points[1]);
        int sum = 0, exist = 0;
        parents = new int[n];
        for (int i = 0; i < n; i++) {
            parents[i] = i;
        }
        PriorityQueue<Pair<Integer, int[]>> queue = new PriorityQueue<>(n, Comparator.comparingInt(Pair::getKey));
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                queue.offer(new Pair<>(getManhattanDistance(points[i], points[j]), new int[]{i, j}));
            }
        }
        while (exist < n - 1) {
            Pair<Integer, int[]> pair = queue.poll();
            int[] pointPair = pair.getValue();
            if (union(pointPair[0], pointPair[1])) {
                sum += pair.getKey();
                exist++;
            }
        }
        return sum;
    }

    private int getManhattanDistance(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }

    private int find(int x) {
        if (x == parents[x]) return x;
        return parents[x] = find(parents[x]);
    }

    private boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) return false;
        parents[rootX] = rootY;
        return true;
    }

    public static void main(String[] args) {
        MinCostConnectPoints2 mccp = new MinCostConnectPoints2();
        System.out.println(mccp.minCostConnectPoints(new int[][]{{0, 0}, {2, 2}, {3, 10}, {5, 2}, {7, 0}}));
    }
}
