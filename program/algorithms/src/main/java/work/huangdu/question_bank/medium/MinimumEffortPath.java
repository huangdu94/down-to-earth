package work.huangdu.question_bank.medium;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 1631. 最小体力消耗路径
 * 你准备参加一场远足活动。给你一个二维 rows x columns 的地图 heights ，其中 heights[row][col] 表示格子 (row, col) 的高度。一开始你在最左上角的格子 (0, 0) ，且你希望去最右下角的格子 (rows-1, columns-1) （注意下标从 0 开始编号）。你每次可以往 上，下，左，右 四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
 * 一条路径耗费的 体力值 是路径上相邻格子之间 高度差绝对值 的 最大值 决定的。
 * 请你返回从左上角走到右下角的最小 体力消耗值 。
 * 示例 1：
 * 输入：heights = [[1,2,2],[3,8,2],[5,3,5]]
 * 输出：2
 * 解释：路径 [1,3,5,3,5] 连续格子的差值绝对值最大为 2 。
 * 这条路径比路径 [1,2,2,2,5] 更优，因为另一条路径差值最大值为 3 。
 * 示例 2：
 * 输入：heights = [[1,2,3],[3,8,4],[5,3,5]]
 * 输出：1
 * 解释：路径 [1,2,3,4,5] 的相邻格子差值绝对值最大为 1 ，比路径 [1,3,5,3,5] 更优。
 * 示例 3：
 * 输入：heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
 * 输出：0
 * 解释：上图所示路径不需要消耗任何体力。
 * 提示：
 * rows == heights.length
 * columns == heights[i].length
 * 1 <= rows, columns <= 100
 * 1 <= heights[i][j] <= 10^6
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/29
 */
public class MinimumEffortPath {

    public int minimumEffortPath(int[][] heights) {
        int row = heights.length, col = heights[0].length, n = row * col;
        if (n == 1) return 0;
        int size = (n << 1) - row - col;
        List<int[]> edges = new ArrayList<>(size);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int curId = i * col + j, curHeight = heights[i][j];
                if (i != row - 1) {
                    edges.add(new int[]{curId, (i + 1) * col + j, Math.abs(curHeight - heights[i + 1][j])});
                }
                if (j != col - 1) {
                    edges.add(new int[]{curId, i * col + j + 1, Math.abs(curHeight - heights[i][j + 1])});
                }
            }
        }
        edges.sort(Comparator.comparingInt(o -> o[2]));
        UnionFindSet ufs = new UnionFindSet(n);
        for (int i = 0; i < size; i++) {
            int[] edge = edges.get(i);
            ufs.union(edge[0], edge[1]);
            if (ufs.isConnect(0, n - 1)) {
                return edge[2];
            }
        }
        return -1;
    }

    private static class UnionFindSet {
        private final int[] parents;

        public UnionFindSet(int n) {
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        public int find(int x) {
            if (x == parents[x]) return x;
            return parents[x] = find(parents[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parents[rootX] = rootY;
            }
        }

        public boolean isConnect(int x, int y) {
            return find(x) == find(y);
        }
    }

    public static void main(String[] args) {
        MinimumEffortPath mep = new MinimumEffortPath();
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        int result = mep.minimumEffortPath(heights);
        System.out.println(result);
    }
}
