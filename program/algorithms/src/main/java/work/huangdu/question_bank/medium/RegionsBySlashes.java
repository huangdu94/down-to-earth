package work.huangdu.question_bank.medium;

/**
 * 959. 由斜杠划分区域
 * 在由 1 x 1 方格组成的 N x N 网格 grid 中，每个 1 x 1 方块由 /、\ 或空格构成。这些字符会将方块划分为一些共边的区域。
 * （请注意，反斜杠字符是转义的，因此 \ 用 "\\" 表示。）。
 * 返回区域的数目。
 * 示例 1：
 * 输入：
 * [
 * " /",
 * "/ "
 * ]
 * 输出：2
 * 解释：2x2 网格如下：
 * 示例 2：
 * 输入：
 * [
 * " /",
 * "  "
 * ]
 * 输出：1
 * 解释：2x2 网格如下：
 * 示例 3：
 * 输入：
 * [
 * "\\/",
 * "/\\"
 * ]
 * 输出：4
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "\\/" 表示 \/，而 "/\\" 表示 /\。）
 * 2x2 网格如下：
 * 示例 4：
 * 输入：
 * [
 * "/\\",
 * "\\/"
 * ]
 * 输出：5
 * 解释：（回想一下，因为 \ 字符是转义的，所以 "/\\" 表示 /\，而 "\\/" 表示 \/。）
 * 2x2 网格如下：
 * 示例 5：
 * 输入：
 * [
 * "//",
 * "/ "
 * ]
 * 输出：3
 * 解释：2x2 网格如下：
 * 提示：
 * 1 <= grid.length == grid[0].length <= 30
 * grid[i][j] 是 '/'、'\'、或 ' '。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/1/25
 */
public class RegionsBySlashes {
    /**
     * \0/
     * 1X3
     * /2\
     * 思路很奇妙。
     */
    public int regionsBySlashes(String[] grid) {
        // 将每一格分成四小格，gridNo * 4 + number，对于gridNo的编号，gridNo = i * len + j
        int len = grid.length, n = len * len * 4;
        UnionFindSet ufs = new UnionFindSet(n);
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                char c = grid[i].charAt(j);
                int no = (i * len + j) << 2;
                if (c == '/') {
                    ufs.union(no, no + 1);
                    ufs.union(no + 2, no + 3);
                } else if (c == '\\') {
                    ufs.union(no, no + 3);
                    ufs.union(no + 1, no + 2);
                } else {
                    ufs.union(no, no + 1);
                    ufs.union(no, no + 2);
                    ufs.union(no, no + 3);
                }
                // 向下连
                if (i != len - 1) {
                    ufs.union(no + 2, no + (len << 2));
                }
                // 向右连
                if (j != len - 1) {
                    ufs.union(no + 3, no + (1 << 2) + 1);
                }
            }
        }
        return ufs.getCount();
    }

    private static class UnionFindSet {
        private final int[] parents;
        private final int[] rank;
        private int count;

        public UnionFindSet(int n) {
            parents = new int[n];
            rank = new int[n];
            count = n;
            for (int i = 0; i < n; i++) {
                parents[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (x == parents[x]) return x;
            return parents[x] = find(parents[x]);
        }

        public void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX == rootY) {
                return;
            }
            if (rank[rootX] > rank[rootY]) {
                parents[rootY] = rootX;
            } else {
                if (rank[rootX] == rank[rootY]) {
                    rank[rootY]++;
                }
                parents[rootX] = rootY;
            }
            count--;
        }

        public int getCount() {
            return count;
        }
    }

    public static void main(String[] args) {
        RegionsBySlashes rbs = new RegionsBySlashes();
        String[] grid = {" /", "/ "};
        System.out.println(rbs.regionsBySlashes(grid));
    }
}
