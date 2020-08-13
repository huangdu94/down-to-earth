package club.huangdu94.exploration.advanced_algorithms.treeGraph;

/**
 * 朋友圈
 * 班上有N名学生。其中有些人是朋友，有些则不是。他们的友谊具有是传递性。如果已知 A 是 B的朋友，B 是 C的朋友，那么我们可以认为 A 也是 C的朋友。所谓的朋友圈，是指所有朋友的集合。
 * 给定一个N * N的矩阵M，表示班级中学生之间的朋友关系。如果M[i][j] = 1，表示已知第 i 个和 j 个学生互为朋友关系，否则为不知道。你必须输出所有学生中的已知的朋友圈总数。
 * 示例 1:
 * 输入:
 * [[1,1,0],
 * [1,1,0],
 * [0,0,1]]
 * 输出: 2
 * 说明：已知学生0和学生1互为朋友，他们在一个朋友圈。
 * 第2个学生自己在一个朋友圈。所以返回2。
 * 示例 2:
 * 输入:
 * [[1,1,0],
 * [1,1,1],
 * [0,1,1]]
 * 输出: 1
 * 说明：已知学生0和学生1互为朋友，学生1和学生2互为朋友，所以学生0和学生2也是朋友，所以他们三个在一个朋友圈，返回1。
 * 注意：
 * N 在[1,200]的范围内。
 * 对于所有学生，有M[i][i] = 1。
 * 如果有M[i][j] = 1，则有M[j][i] = 1。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/9 10:47
 */
public class FindCircleNum {
    public int findCircleNum(int[][] M) {
        int n = M.length;
        UnionFindSets set = new UnionFindSets(n);
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (M[i][j] == 1)
                    set.union(i, j);
        return set.getCount();
    }

    // 并查集
    private static class UnionFindSets {
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
            while (parent[a] != parent[parent[a]]) parent[a] = parent[parent[a]];
            return parent[a];
        }

        // 查找(路径压缩)(递归版)
        /*private int find(int a) {
            if (a != parent[a]) parent[a] = find(parent[a]);
            return parent[a];
        }*/

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
            if (rank[parentB] > rank[parentA]) parent[parentA] = parentB;
            else {
                if (rank[parentA] == rank[parentB]) rank[parentA]++;
                parent[parentB] = parentA;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        FindCircleNum findCircleNum = new FindCircleNum();
        int[][] M = {
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        };
        int[][] M2 = {
                {1, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0},
                {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                {0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {1, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 1, 0, 0, 0, 0, 1, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 1}};
        int result = findCircleNum.findCircleNum(M2);
        System.out.println(result);
    }
}