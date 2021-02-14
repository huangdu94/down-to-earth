package work.huangdu.question_bank.difficult;

import java.util.*;

/**
 * 765. 情侣牵手
 * N 对情侣坐在连续排列的 2N 个座位上，想要牵到对方的手。 计算最少交换座位的次数，以便每对情侣可以并肩坐在一起。 一次交换可选择任意两人，让他们站起来交换座位。
 * 人和座位用 0 到 2N-1 的整数表示，情侣们按顺序编号，第一对是 (0, 1)，第二对是 (2, 3)，以此类推，最后一对是 (2N-2, 2N-1)。
 * 这些情侣的初始座位  row[i] 是由最初始坐在第 i 个座位上的人决定的。
 * 示例 1:
 * 输入: row = [0, 2, 1, 3]
 * 输出: 1
 * 解释: 我们只需要交换row[1]和row[2]的位置即可。
 * 示例 2:
 * 输入: row = [3, 2, 0, 1]
 * 输出: 0
 * 解释: 无需交换座位，所有的情侣都已经可以手牵手了。
 * 说明:
 * len(row) 是偶数且数值在 [4, 60]范围内。
 * 可以保证row 是序列 0...len(row)-1 的一个全排列。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/14
 */
public class MinSwapsCouples {
    // 1. 并查集
    public int minSwapsCouples2(int[] row) {
        int len = row.length, n = len / 2;
        UnionFindSet ufs = new UnionFindSet(n);
        for (int i = 0; i < len; i += 2) {
            int left = row[i] / 2;
            int right = row[i + 1] / 2;
            ufs.union(left, right);
        }
        return n - ufs.getCount();
    }

    private static class UnionFindSet {
        private final int[] parent;
        private int count;

        public UnionFindSet(int n) {
            parent = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (x == parent[x]) return x;
            return parent[x] = find(parent[x]);
        }

        public void union(int x1, int x2) {
            int rootX1 = find(x1);
            int rootX2 = find(x2);
            if (rootX1 == rootX2) return;
            count--;
            parent[rootX1] = rootX2;
        }

        public int getCount() {
            return count;
        }
    }

    // 2. bfs
    public int minSwapsCouples3(int[] row) {
        int len = row.length, n = len / 2;
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < len; i += 2) {
            int node1 = row[i] / 2;
            int node2 = row[i + 1] / 2;
            if (node1 != node2) {
                edges.get(node1).add(node2);
                edges.get(node2).add(node1);
            }
        }
        boolean[] visited = new boolean[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                Queue<Integer> queue = new ArrayDeque<>();
                queue.offer(i);
                visited[i] = true;
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    count++;
                    for (int k : edges.get(node)) {
                        if (!visited[k]) {
                            queue.offer(k);
                            visited[k] = true;
                        }
                    }
                }
                count--;
            }
        }
        return count;
    }

    private int count = 0;

    private void dfs(List<List<Integer>> edges, boolean[] visited, int node) {
        visited[node] = true;
        count++;
        for (int next : edges.get(node)) {
            if (!visited[next]) {
                dfs(edges, visited, next);
            }
        }
    }

    // 3. dfs
    public int minSwapsCouples(int[] row) {
        int len = row.length, n = len / 2;
        List<List<Integer>> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<>());
        }
        for (int i = 0; i < len; i += 2) {
            int node1 = row[i] / 2;
            int node2 = row[i + 1] / 2;
            if (node1 != node2) {
                edges.get(node1).add(node2);
                edges.get(node2).add(node1);
            }
        }
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(edges, visited, i);
                count--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        MinSwapsCouples msc = new MinSwapsCouples();
        int[] row = {0, 2, 1, 3};
        System.out.println(msc.minSwapsCouples(row));
    }
}

