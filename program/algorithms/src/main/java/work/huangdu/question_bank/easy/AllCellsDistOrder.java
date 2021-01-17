package work.huangdu.question_bank.easy;

import java.util.Arrays;

/**
 * 1030. 距离顺序排列矩阵单元格
 * 给出 R 行 C 列的矩阵，其中的单元格的整数坐标为 (r, c)，满足 0 <= r < R 且 0 <= c < C。
 * 另外，我们在该矩阵中给出了一个坐标为 (r0, c0) 的单元格。
 * 返回矩阵中的所有单元格的坐标，并按到 (r0, c0) 的距离从最小到最大的顺序排，其中，两单元格(r1, c1) 和 (r2, c2) 之间的距离是曼哈顿距离，|r1 - r2| + |c1 - c2|。（你可以按任何满足此条件的顺序返回答案。）
 * 示例 1：
 * 输入：R = 1, C = 2, r0 = 0, c0 = 0
 * 输出：[[0,0],[0,1]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1]
 * 示例 2：
 * 输入：R = 2, C = 2, r0 = 0, c0 = 1
 * 输出：[[0,1],[0,0],[1,1],[1,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2]
 * [[0,1],[1,1],[0,0],[1,0]] 也会被视作正确答案。
 * 示例 3：
 * 输入：R = 2, C = 3, r0 = 1, c0 = 2
 * 输出：[[1,2],[0,2],[1,1],[0,1],[1,0],[0,0]]
 * 解释：从 (r0, c0) 到其他单元格的距离为：[0,1,1,2,2,3]
 * 其他满足题目要求的答案也会被视为正确，例如 [[1,2],[1,1],[0,2],[1,0],[0,1],[0,0]]。
 * 提示：
 * 1 <= R <= 100
 * 1 <= C <= 100
 * 0 <= r0 < R
 * 0 <= c0 < C
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/11/17 18:35
 */
public class AllCellsDistOrder {
    // 时间复杂度 nlog(n)
    public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
        // 1. 生成
        int[][] cells = new int[R * C][2];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                cells[i * C + j] = new int[]{i, j};
            }
        }
        // 2. 自定义排序
        Arrays.sort(cells, (cell1, cell2) -> {
            int distance1 = Math.abs(cell1[0] - r0) + Math.abs(cell1[1] - c0);
            int distance2 = Math.abs(cell2[0] - r0) + Math.abs(cell2[1] - c0);
            return distance1 - distance2;
        });
        return cells;
    }

    public int[][] allCellsDistOrder2(int R, int C, int r0, int c0) {
        int[][] cells = new int[R * C][2];
        cells[0] = new int[]{r0, c0};
        int i = 1, max = Math.max(R - 1 - r0, r0) + Math.max(C - 1 - c0, c0);
        for (int distance = 1; distance <= max; distance++) {
            for (int r = 0; r <= distance; r++) {
                int c = distance - r;
                boolean rmin = r0 - r >= 0, rmax = r0 + r < R;
                boolean cmin = c0 - c >= 0, cmax = c0 + c < C;
                if (rmin) {
                    if (cmin) {
                        cells[i++] = new int[]{r0 - r, c0 - c};
                    }
                    if (c != 0 && cmax) {
                        cells[i++] = new int[]{r0 - r, c0 + c};
                    }
                }
                if (r != 0 && rmax) {
                    if (cmin) {
                        cells[i++] = new int[]{r0 + r, c0 - c};
                    }
                    if (c != 0 && cmax) {
                        cells[i++] = new int[]{r0 + r, c0 + c};
                    }
                }
            }
        }
        return cells;
    }

    public static void main(String[] args) {
        AllCellsDistOrder acdo = new AllCellsDistOrder();
        System.out.println(Arrays.deepToString(acdo.allCellsDistOrder2(2, 2, 0, 1)));
    }
}
