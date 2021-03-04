package work.huangdu.specific.union_find_set;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/27
 */
public abstract class AbstractUnionFindSet {
    protected int[] parent;

    /**
     * 初始化
     */
    public AbstractUnionFindSet(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /**
     * 查找
     */
    public abstract int find(int x);

    /**
     * 合并
     */
    public abstract boolean union(int x, int y);
}