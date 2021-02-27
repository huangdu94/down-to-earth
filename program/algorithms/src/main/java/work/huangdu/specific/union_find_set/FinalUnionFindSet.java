package work.huangdu.specific.union_find_set;

import java.util.Arrays;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/28
 */
public class FinalUnionFindSet extends AbstractUnionFindSet {
    private final int[] rank;

    public FinalUnionFindSet(int n) {
        super(n);
        rank = new int[n];
        Arrays.fill(rank, 1);
    }

    /**
     * 路径压缩
     */
    @Override
    public int find(int x) {
        while (parent[x] != parent[parent[x]]) {
            parent[x] = parent[parent[x]];
        }
        return parent[x];
    }

    @Override
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) { return false;}
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else {
            if (rank[rootX] == rank[rootY]) {
                rank[rootY]++;
            }
            parent[rootX] = rootY;
        }
        return true;
    }
}
