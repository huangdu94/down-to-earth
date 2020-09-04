package club.huangdu94.data_structure;

/**
 * union-find sets 并查集
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/4 10:31
 */
public class UnionFindSets {
    private int count;
    private final int[] parent;
    private final int[] rank;

    // 初始化
    public UnionFindSets(int n) {
        parent = new int[n];
        rank = new int[n];
        count = n;
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }
    }

    // 查找(路径压缩)(迭代版)
    private int find(int a) {
        while (parent[a] != a) {
            a = parent[a];
        }
        return a;
    }

    // 查找(路径压缩)(递归版)
    /*public int find(int a) {
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }*/

    // 合并
    public void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);
        if (parentA == parentB) return;
        count--;
        if (rank[parentB] > rank[parentA]) {
            parent[parentA] = parentB;
        } else {
            if (rank[parentA] == rank[parentB]) {
                rank[parentA]++;
            }
            parent[parentB] = parentA;
        }
    }

    public int getCount() {
        return count;
    }
}
