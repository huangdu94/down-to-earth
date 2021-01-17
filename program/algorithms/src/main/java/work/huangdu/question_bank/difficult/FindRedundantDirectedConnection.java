package work.huangdu.question_bank.difficult;

import java.util.*;

/**
 * 685. 冗余连接 II
 * 在本问题中，有根树指满足以下条件的有向图。该树只有一个根节点，所有其他节点都是该根节点的后继。每一个节点只有一个父节点，除了根节点没有父节点。
 * 输入一个有向图，该图由一个有着N个节点 (节点值不重复1, 2, ..., N) 的树及一条附加的边构成。附加的边的两个顶点包含在1到N中间，这条附加的边不属于树中已存在的边。
 * 结果图是一个以边组成的二维数组。 每一个边 的元素是一对 [u, v]，用以表示有向图中连接顶点 u 和顶点 v 的边，其中 u 是 v 的一个父节点。
 * 返回一条能删除的边，使得剩下的图是有N个节点的有根树。若有多个答案，返回最后出现在给定二维数组的答案。
 * 示例 1:
 * 输入: [[1,2], [1,3], [2,3]]
 * 输出: [2,3]
 * 解释: 给定的有向图如下:
 * * 1
 * / \
 * v   v
 * 2-->3
 * 示例 2:
 * 输入: [[1,2], [2,3], [3,4], [4,1], [1,5]]
 * 输出: [4,1]
 * 解释: 给定的有向图如下:
 * 5 <- 1 -> 2
 * ^    |
 * |    v
 * 4 <- 3
 * 注意:
 * 二维数组大小的在3到1000范围内。
 * 二维数组中的每个整数在1到N之间，其中 N 是二维数组的大小。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/9/17 10:44
 */
public class FindRedundantDirectedConnection {
    // 官方解 并查集。
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        Map<Integer, List<Integer>> map = new HashMap<>(n);
        int[] parent = new int[n + 1];
        for (int[] edge : edges) {
            List<Integer> sonList = map.computeIfAbsent(edge[0], k -> new ArrayList<>());
            sonList.add(edge[1]);
        }
        for (int i = 0; i < n; i++) {
            int[] edge = edges[i];
            if (parent[edge[1]] == 0) {
                parent[edge[1]] = edge[0];
            } else {
                /* 该节点有两个parent
                   肯定要删除指向该点的一条边，该边满足以下两个条件:
                   1. 边的起始节点一定要有parent
                   2. 并且删除该边后，不能存在环 */
                boolean haveParentFlag = parent[edge[0]] != 0;
                if (!haveParentFlag) {
                    // edge[0]遍历到此处没有parent不代表它就没有parent，遍历完看看
                    for (int j = i + 1; j < n; j++) {
                        if (edges[j][1] == edge[0]) {
                            haveParentFlag = true;
                            break;
                        }
                    }
                }
                int[] another = {parent[edge[0]], edge[1]};
                 /* 1. 如果edge[0]没有parent，且edge[0]只有一个son，说明该边一定不能删，只能删另一条边
                    2. 如果edge[0]没有parent，且edge[0]有多个son，或edge[0]有parent
                     2.1 前一条边在一个环里，删除前一条边
                     2.2 前一条边不在一个环里，删除该条边 */
                if (!haveParentFlag && map.get(edge[0]).size() == 1) {
                    return another;
                } else {
                    // dfs判断有没有环
                    if (dfs(map, new boolean[n + 1], another[0], another[1])) {
                        return another;
                    }
                    return edge;
                }
            }
        }
        // 每个节点都有一个parent，从后往前遍历删除第一个指向有son节点的边即可
        for (int i = n - 1; i >= 0; i--) {
            if (map.containsKey(edges[i][1])) {
                return edges[i];
            }
        }
        return null;
    }
    // [[2,1],[3,1],[4,2],[1,4]]
    // [[4,2],[1,5],[5,2],[5,3],[2,4]]
    // [[1,2],[1,3],[2,3]]
    // [[5,2],[5,1],[3,1],[3,4],[3,5]]

    private boolean dfs(Map<Integer, List<Integer>> map, boolean[] visited, int start, int cur) {
        if (cur == start) return true;
        visited[cur] = true;
        List<Integer> nextList = map.get(cur);
        if (nextList != null) {
            for (int next : nextList) {
                if (visited[next]) continue;
                if (dfs(map, visited, start, next)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] edges = {{4, 1}, {1, 5}, {4, 2}, {5, 1}, {4, 3}};
        FindRedundantDirectedConnection findRedundantDirectedConnection = new FindRedundantDirectedConnection();
        System.out.println(Arrays.toString(findRedundantDirectedConnection.findRedundantDirectedConnection(edges)));
    }
}
