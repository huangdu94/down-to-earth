package work.huangdu.question_bank.difficult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 1489. 找到最小生成树里的关键边和伪关键边
 * 给你一个 n 个点的带权无向连通图，节点编号为 0 到 n-1 ，同时还有一个数组 edges ，其中 edges[i] = [fromi, toi, weighti] 表示在 fromi 和 toi 节点之间有一条带权无向边。最小生成树 (MST) 是给定图中边的一个子集，它连接了所有节点且没有环，而且这些边的权值和最小。
 * 请你找到给定图中最小生成树的所有关键边和伪关键边。如果从图中删去某条边，会导致最小生成树的权值和增加，那么我们就说它是一条关键边。伪关键边则是可能会出现在某些最小生成树中但不会出现在所有最小生成树中的边。
 * 请注意，你可以分别以任意顺序返回关键边的下标和伪关键边的下标。
 * 示例 1：
 * 输入：n = 5, edges = [[0,1,1],[1,2,1],[2,3,2],[0,3,2],[0,4,3],[3,4,3],[1,4,6]]
 * 输出：[[0,1],[2,3,4,5]]
 * 解释：上图描述了给定图。
 * 下图是所有的最小生成树。
 * 注意到第 0 条边和第 1 条边出现在了所有最小生成树中，所以它们是关键边，我们将这两个下标作为输出的第一个列表。
 * 边 2，3，4 和 5 是所有 MST 的剩余边，所以它们是伪关键边。我们将它们作为输出的第二个列表。
 * 示例 2 ：
 * 输入：n = 4, edges = [[0,1,1],[1,2,1],[2,3,1],[0,3,1]]
 * 输出：[[],[0,1,2,3]]
 * 解释：可以观察到 4 条边都有相同的权值，任选它们中的 3 条可以形成一棵 MST 。所以 4 条边都是伪关键边。
 * 提示：
 * 2 <= n <= 100
 * 1 <= edges.length <= min(200, n * (n - 1) / 2)
 * edges[i].length == 3
 * 0 <= from_i < to_i < n
 * 1 <= weight_i <= 1000
 * 所有 (from_i, to_i) 数对都是互不相同的。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/21
 */
public class FindCriticalAndPseudoCriticalEdges {

    private static class UnionFindSet {
        private final int[] parent;
        private final int[] rank;
        private int count;

        public UnionFindSet(int n) {
            parent = new int[n];
            rank = new int[n];
            init();
        }

        public void init() {
            int n = parent.length;
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        private int find(int x) {
            while (parent[x] != parent[parent[x]]) {
                parent[x] = parent[parent[x]];
            }
            return parent[x];
        }

        private boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return false;
            }
            if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                if (rank[rootX] == rank[rootY]) {
                    rank[rootY]++;
                }
                parent[rootX] = rootY;
            }
            count--;
            return true;
        }

        private int getCount() {
            return count;
        }
    }

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        int length = edges.length;
        UnionFindSet ufs = new UnionFindSet(n);
        int[][] edgesWithNumber = new int[edges.length][4];
        for (int i = 0; i < length; i++) {
            edgesWithNumber[i][0] = edges[i][0];
            edgesWithNumber[i][1] = edges[i][1];
            edgesWithNumber[i][2] = edges[i][2];
            edgesWithNumber[i][3] = i;
        }
        Arrays.sort(edgesWithNumber, Comparator.comparingInt(o -> o[2]));
        // 1. 计算最小生成树的值
        int min = 0;
        for (int[] edge : edgesWithNumber) {
            if (ufs.union(edge[0], edge[1])) {
                min += edge[2];
            }
        }

        List<List<Integer>> result = new ArrayList<>(2);
        result.add(new ArrayList<>());
        result.add(new ArrayList<>());
        for (int i = 0; i < length; i++) {
            ufs.init();
            // 2. 判断是否为关键边，跳过j，如果生成树的值变大或者无法得到则为关键边
            int sum = 0;
            for (int j = 0; j < length && ufs.getCount() > 1; j++) {
                if (i == j) continue;
                int[] edge = edgesWithNumber[j];
                if (ufs.union(edge[0], edge[1])) {
                    sum += edge[2];
                }
            }
            if (ufs.getCount() > 1 || sum > min) {
                result.get(0).add(edgesWithNumber[i][3]);
                continue;
            }
            // 3. 判断是否为非关键边
            ufs.init();
            sum = edgesWithNumber[i][2];
            ufs.union(edgesWithNumber[i][0], edgesWithNumber[i][1]);
            for (int j = 0; j < length && sum <= min && ufs.getCount() > 1; j++) {
                if (i == j) continue;
                int[] edge = edgesWithNumber[j];
                if (ufs.union(edge[0], edge[1])) {
                    sum += edge[2];
                }
            }
            if (ufs.getCount() == 1 && sum == min) {
                result.get(1).add(edgesWithNumber[i][3]);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        FindCriticalAndPseudoCriticalEdges solution = new FindCriticalAndPseudoCriticalEdges();
        int n = 6;
        int[][] edges = {{0, 1, 1}, {1, 2, 1}, {0, 2, 1}, {2, 3, 4}, {3, 4, 2}, {3, 5, 2}, {4, 5, 2}};
        System.out.println(solution.findCriticalAndPseudoCriticalEdges(n, edges));
    }
}
