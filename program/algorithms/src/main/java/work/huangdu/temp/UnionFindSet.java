package work.huangdu.temp;

/**
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/2/26
 */
public class UnionFindSet {
    private int[] parent;

    public UnionFindSet(int n) {
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    public int find(int x) {
        while (x != parent[x]) {
            x = parent[x];
        }
        return x;
    }
}
