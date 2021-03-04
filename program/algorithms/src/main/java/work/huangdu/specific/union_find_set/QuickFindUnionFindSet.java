package work.huangdu.specific.union_find_set;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/27
 */
public class QuickFindUnionFindSet extends AbstractUnionFindSet {
    public QuickFindUnionFindSet(int n) {
        super(n);
    }

    @Override
    public int find(int x) {
        return parent[x];
    }

    @Override
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX == rootY) { return false;}
        for (int i = 0, n = parent.length; i < n; i++) {
            if (parent[i] == rootX) {
                parent[i] = rootY;
            }
        }
        return true;
    }
}
