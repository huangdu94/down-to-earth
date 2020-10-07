package club.huangdu94.question_bank.difficult;

import java.util.ArrayList;
import java.util.List;

/**
 * 834. 树中距离之和
 * 给定一个无向、连通的树。树中有 N 个标记为 0...N-1 的节点以及 N-1 条边 。
 * 第 i 条边连接节点 edges[i][0] 和 edges[i][1] 。
 * 返回一个表示节点 i 与其他所有节点距离之和的列表 ans。
 * 示例 1:
 * 输入: N = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
 * 输出: [8,12,6,10,10,10]
 * 解释:
 * 如下为给定的树的示意图：
 * *0
 * / \
 * 1   2
 * *  /|\
 * * 3 4 5
 * 我们可以计算出 dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
 * 也就是 1 + 1 + 2 + 2 + 2 = 8。 因此，answer[0] = 8，以此类推。
 * 说明: 1 <= N <= 10000
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/6 13:40
 */
public class SumOfDistancesInTree {
    private List<List<Integer>> graph;
    private int[] ans;
    private int[] dp;
    private int[] nc;

    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        graph = new ArrayList<>();
        ans = new int[N];
        dp = new int[N];
        nc = new int[N];
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }
        // 之所以采用图而非树，是因为如果用树的话必须确定根节点
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        dfs(0, -1);
        dfs2(0, -1);
        return ans;
    }

    private void dfs(int u, int f) {
        dp[u] = 0;
        nc[u] = 1;
        for (int v : graph.get(u)) {
            if (v == f) continue;
            dfs(v, u);
            dp[u] += dp[v] + nc[v];
            nc[u] += nc[v];
        }
    }

    private void dfs2(int u, int f) {
        ans[u] = dp[u];
        for (int v : graph.get(u)) {
            if (v == f) continue;
            int dpu = dp[u], dpv = dp[v], ncu = nc[u], ncv = nc[v];
            dp[u] -= (dp[v] + nc[v]);
            nc[u] -= nc[v];
            dp[v] += (dp[u] + nc[u]);
            nc[v] += nc[u];
            dfs2(v, u);
            // 回溯
            dp[u] = dpu;
            nc[u] = ncu;
            dp[v] = dpv;
            nc[v] = ncv;
        }
    }
}
