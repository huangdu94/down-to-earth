package work.huangdu.specific.union_find_set;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/27
 */
public class QuickUnionUnionFindSet extends AbstractUnionFindSet {
    public QuickUnionUnionFindSet(int n) {
        super(n);
    }

    @Override
    public int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }

    @Override
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) { return false;}
        parent[rootX] = rootY;
        return true;
    }
}
