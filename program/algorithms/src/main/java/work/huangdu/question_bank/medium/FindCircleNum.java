package work.huangdu.question_bank.medium;

/**
 * 547. 省份数量
 * 有 n 个城市，其中一些彼此相连，另一些没有相连。如果城市 a 与城市 b 直接相连，且城市 b 与城市 c 直接相连，那么城市 a 与城市 c 间接相连。
 * 省份 是一组直接或间接相连的城市，组内不含其他没有相连的城市。
 * 给你一个 n x n 的矩阵 isConnected ，其中 isConnected[i][j] = 1 表示第 i 个城市和第 j 个城市直接相连，而 isConnected[i][j] = 0 表示二者不直接相连。
 * 返回矩阵中 省份 的数量。
 * 示例 1：
 * 输入：isConnected = [[1,1,0],[1,1,0],[0,0,1]]
 * 输出：2
 * 示例 2：
 * 输入：isConnected = [[1,0,0],[0,1,0],[0,0,1]]
 * 输出：3
 * 提示：
 * 1 <= n <= 200
 * n == isConnected.length
 * n == isConnected[i].length
 * isConnected[i][j] 为 1 或 0
 * isConnected[i][i] == 1
 * isConnected[i][j] == isConnected[j][i]
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2021/1/7 13:00
 */
public class FindCircleNum {
    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        UnionFindSet ufs = new UnionFindSet(n);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (isConnected[i][j] == 1) {
                    ufs.union(i, j);
                }
            }
        }
        return ufs.getCount();
    }

    private static class UnionFindSet {
        private int count;
        private final int[] parents;

        UnionFindSet(int n) {
            count = n;
            parents = new int[n];
            for (int i = 0; i < n; i++) {
                parents[i] = i;
            }
        }

        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            parents[rootX] = rootY;
            count--;
        }

        int find(int x) {
            while (parents[x] != parents[parents[x]]) {
                parents[x] = parents[parents[x]];
            }
            return parents[x];
        }

        int getCount() {
            return count;
        }
    }
}
