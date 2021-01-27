package work.huangdu.question_bank.difficult;

import java.util.Arrays;

/**
 * 1579. 保证图可完全遍历
 * Alice 和 Bob 共有一个无向图，其中包含 n 个节点和 3  种类型的边：
 * 类型 1：只能由 Alice 遍历。
 * 类型 2：只能由 Bob 遍历。
 * 类型 3：Alice 和 Bob 都可以遍历。
 * 给你一个数组 edges ，其中 edges[i] = [type_i, u_i, v_i] 表示节点 ui 和 vi 之间存在类型为 type_i 的双向边。请你在保证图仍能够被 Alice和 Bob 完全遍历的前提下，找出可以删除的最大边数。如果从任何节点开始，Alice 和 Bob 都可以到达所有其他节点，则认为图是可以完全遍历的。
 * 返回可以删除的最大边数，如果 Alice 和 Bob 无法完全遍历图，则返回 -1 。
 * 示例 1：
 * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,3],[1,2,4],[1,1,2],[2,3,4]]
 * 输出：2
 * 解释：如果删除 [1,1,2] 和 [1,1,3] 这两条边，Alice 和 Bob 仍然可以完全遍历这个图。再删除任何其他的边都无法保证图可以完全遍历。所以可以删除的最大边数是 2 。
 * 示例 2：
 * 输入：n = 4, edges = [[3,1,2],[3,2,3],[1,1,4],[2,1,4]]
 * 输出：0
 * 解释：注意，删除任何一条边都会使 Alice 和 Bob 无法完全遍历这个图。
 * 示例 3：
 * 输入：n = 4, edges = [[3,2,3],[1,1,2],[2,3,4]]
 * 输出：-1
 * 解释：在当前图中，Alice 无法从其他节点到达节点 4 。类似地，Bob 也不能达到节点 1 。因此，图无法完全遍历。
 * 提示：
 * 1 <= n <= 10^5
 * 1 <= edges.length <= min(10^5, 3 * n * (n-1) / 2)
 * edges[i].length == 3
 * 1 <= edges[i][0] <= 3
 * 1 <= edges[i][1] < edges[i][2] <= n
 * 所有元组 (type_i, u_i, v_i) 互不相同
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/27
 */
public class MaxNumEdgesToRemove {
    public int maxNumEdgesToRemove(int n, int[][] edges) {
        int count = 0;
        UnionFindSet ufs = new UnionFindSet(n + 1);
        // 优先使用两个都可以用的，即type为3
        for (int[] edge : edges) {
            if (edge[0] == 3) {
                if (!ufs.union(edge[1], edge[2])) {
                    count++;
                }
            }
        }
        // 仅用公共的就可以连通
        if (ufs.getCount() == 1) return edges.length - n + 1;
        UnionFindSet ufs2 = ufs.copy();
        for (int[] edge : edges) {
            if (edge[0] == 1) {
                if (!ufs.union(edge[1], edge[2])) {
                    count++;
                }
            }
        }
        if (ufs.getCount() != 1) return -1;
        for (int[] edge : edges) {
            if (edge[0] == 2) {
                if (!ufs2.union(edge[1], edge[2])) {
                    count++;
                }
            }
        }
        if (ufs2.getCount() != 1) return -1;
        return count;
    }

    private static class UnionFindSet {
        private final int[] parent;
        private int count;

        public UnionFindSet(int n) {
            parent = new int[n];
            count = n - 1;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        private UnionFindSet(int[] _parent, int _count) {
            parent = _parent;
            count = _count;
        }

        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) return false;
            parent[rootX] = rootY;
            count--;
            return true;
        }

        public int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }

        public int getCount() {
            return count;
        }

        public UnionFindSet copy() {
            return new UnionFindSet(Arrays.copyOf(parent, parent.length), count);
        }
    }

    public static void main(String[] args) {
        MaxNumEdgesToRemove mnetr = new MaxNumEdgesToRemove();
        int[][] edges = {{3, 1, 2}, {3, 2, 3}, {1, 1, 3}, {1, 2, 4}, {1, 1, 2}, {2, 3, 4}};
        System.out.println(mnetr.maxNumEdgesToRemove(4, edges));
    }
}
