package work.huangdu.question_bank.medium;

import java.util.HashSet;
import java.util.Set;

/**
 * 947. 移除最多的同行或同列石头
 * n 块石头放置在二维平面中的一些整数坐标点上。每个坐标点上最多只能有一块石头。
 * 如果一块石头的 同行或者同列 上有其他石头存在，那么就可以移除这块石头。
 * 给你一个长度为 n 的数组 stones ，其中 stones[i] = [xi, yi] 表示第 i 块石头的位置，返回 可以移除的石子 的最大数量。
 * 示例 1：
 * 输入：stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
 * 输出：5
 * 解释：一种移除 5 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,1] 同行。
 * 2. 移除石头 [2,1] ，因为它和 [0,1] 同列。
 * 3. 移除石头 [1,2] ，因为它和 [1,0] 同行。
 * 4. 移除石头 [1,0] ，因为它和 [0,0] 同列。
 * 5. 移除石头 [0,1] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 不能移除，因为它没有与另一块石头同行/列。
 * 示例 2：
 * 输入：stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
 * 输出：3
 * 解释：一种移除 3 块石头的方法如下所示：
 * 1. 移除石头 [2,2] ，因为它和 [2,0] 同行。
 * 2. 移除石头 [2,0] ，因为它和 [0,0] 同列。
 * 3. 移除石头 [0,2] ，因为它和 [0,0] 同行。
 * 石头 [0,0] 和 [1,1] 不能移除，因为它们没有与另一块石头同行/列。
 * 示例 3：
 * 输入：stones = [[0,0]]
 * 输出：0
 * 解释：[0,0] 是平面上唯一一块石头，所以不可以移除它。
 * 提示：
 * 1 <= stones.length <= 1000
 * 0 <= xi, yi <= 10^4
 * 不会有两块石头放在同一个坐标点上
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2021/1/15
 */
public class RemoveStones {
    private int[] parent;

    private int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    private void union(int x, int y) {
        parent[find(x)] = find(y);
    }

    public int removeStones(int[][] stones) {
        parent = new int[20002];
        for (int i = 0; i < 20002; i++) {
            parent[i] = i;
        }
        for (int[] stone : stones) {
            union(stone[0], stone[1] + 10001);
        }
        Set<Integer> set = new HashSet<>();
        for (int[] stone : stones) {
            int x = stone[0];
            set.add(find(x));
        }
        return stones.length - set.size();
    }
/*
    public int removeStones(int[][] stones) {
        return Math.max(removeStones(stones, true), removeStones(stones, false));
    }

    private int removeStones(int[][] stones, boolean pn) {
        int deleteCount = 0;
        Map<Integer, Set<Integer>> rowMap = new HashMap<>(), cojMap = new HashMap<>();
        for (int[] stone : stones) {
            int row, coj;
            if (pn) {
                row = stone[0];
                coj = stone[1];
            } else {
                row = stone[1];
                coj = stone[0];
            }
            Set<Integer> cojSet = rowMap.computeIfAbsent(row, k -> new HashSet<>()),
                    rowSet = cojMap.computeIfAbsent(coj, k -> new HashSet<>());
            cojSet.add(coj);
            rowSet.add(row);
        }
        for (int row : rowMap.keySet()) {
            Set<Integer> cojSet = rowMap.get(row);
            boolean flag = false;
            for (int coj : cojSet) {
                Set<Integer> rowSet = cojMap.get(coj);
                if (!flag && rowSet.size() > 1) {
                    flag = true;
                }
                rowSet.remove(row);
            }
            if (flag) {
                deleteCount += cojSet.size();
            } else {
                deleteCount += (cojSet.size() - 1);
            }
        }
        return deleteCount;
    }*/

    public static void main(String[] args) {
        RemoveStones rs = new RemoveStones();
        int[][] stones = {{0, 1}, {0, 2}, {4, 3}, {2, 4}, {0, 3}, {1, 1}};
        System.out.println(rs.removeStones(stones));
    }
}
