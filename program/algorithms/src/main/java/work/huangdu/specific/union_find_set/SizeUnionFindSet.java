package work.huangdu.specific.union_find_set;

import java.util.Arrays;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/27
 */
public class SizeUnionFindSet extends AbstractUnionFindSet {
    private final int[] size;

    public SizeUnionFindSet(int n) {
        super(n);
        size = new int[n];
        Arrays.fill(size, 1);
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
        if (size[rootX] > size[rootY]) {
            parent[rootY] = rootX;
            size[rootX] += size[rootY];
        } else {
            parent[rootX] = rootY;
            size[rootY] += size[rootX];
        }
        return true;
    }
}
