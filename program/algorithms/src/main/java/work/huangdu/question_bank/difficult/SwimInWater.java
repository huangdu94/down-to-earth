package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * 778. 水位上升的泳池中游泳
 * 在一个 N x N 的坐标方格 grid 中，每一个方格的值 grid[i][j] 表示在位置 (i,j) 的平台高度。
 * 现在开始下雨了。当时间为 t 时，此时雨水导致水池中任意位置的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。
 * 你从坐标方格的左上平台 (0，0) 出发。最少耗时多久你才能到达坐标方格的右下平台 (N-1, N-1)？
 * 示例 1:
 * 输入: [[0,2],[1,3]]
 * 输出: 3
 * 解释:
 * 时间为0时，你位于坐标方格的位置为 (0, 0)。
 * 此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
 * 等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
 * 示例2:
 * 输入: [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,9,8,7,6]]
 * 输出: 16
 * 解释:
 * 0  1  2  3  4
 * 24 23 22 21  5
 * 12 13 14 15 16
 * 11 17 18 19 20
 * 10  9  8  7  6
 * 最终的路线用加粗进行了标记。
 * 我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
 * 提示:
 * 2 <= N <= 50.
 * grid[i][j] 是 [0, ..., N*N - 1] 的排列。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2021/1/30
 */
public class SwimInWater {
    public int swimInWater2(int[][] grid) {
        int col = grid.length, n = col * col, edgeListSize = (n - col) << 1;
        List<int[]> edgeList = new ArrayList<>(edgeListSize);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < col; j++) {
                int curId = i * col + j, curVal = grid[i][j];
                if (i != col - 1) {
                    edgeList.add(new int[]{curId, curId + col, Math.max(curVal, grid[i + 1][j])});
                }
                if (j != col - 1) {
                    edgeList.add(new int[]{curId, curId + 1, Math.max(curVal, grid[i][j + 1])});
                }
            }
        }
        edgeList.sort(Comparator.comparingInt(o -> o[2]));
        UnionFindSet ufs = new UnionFindSet(n);
        for (int[] edge : edgeList) {
            ufs.union(edge[0], edge[1]);
            if (ufs.isConnected()) {
                return edge[2];
            }
        }
        return -1;
    }

    public int swimInWater(int[][] grid) {
        int col = grid.length, n = col * col;
        int[] index = new int[n];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < col; j++) {
                index[grid[i][j]] = i * col + j;
            }
        }
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        UnionFindSet ufs = new UnionFindSet(n);
        for (int t = 0; t < n; t++) {
            int id = index[t], i = index[t] / col, j = index[t] % col;
            for (int[] direction : directions) {
                int x = i + direction[0], y = j + direction[1];
                if (x < col && x >= 0 && y < col && y >= 0 && grid[x][y] < t) {
                    ufs.union(id, x * col + y);
                    if (ufs.isConnected()) {
                        return t;
                    }
                }
            }
        }
        return -1;
    }

    private static class UnionFindSet {
        private final int[] parent;
        private final int n;

        public UnionFindSet(int _n) {
            parent = new int[_n];
            n = _n;
            for (int i = 0; i < _n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                parent[rootX] = rootY;
            }
        }

        public boolean isConnected() {
            return find(0) == find(n - 1);
        }
    }
}
