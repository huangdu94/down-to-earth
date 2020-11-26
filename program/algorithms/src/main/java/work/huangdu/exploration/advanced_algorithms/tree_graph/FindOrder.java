package work.huangdu.exploration.advanced_algorithms.tree_graph;

import java.util.*;

/**
 * 210. 课程表 II
 * 现在你总共有 n 门课需要选，记为0到n-1。
 * 在选修某些课程之前需要一些先修课程。例如，想要学习课程 0 ，你需要先完成课程1 ，我们用一个匹配来表示他们: [0,1]
 * 给定课程总量以及它们的先决条件，返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回一种就可以了。如果不可能完成所有课程，返回一个空数组。
 * 示例1:
 * 输入: 2, [[1,0]]
 * 输出: [0,1]
 * 解释:总共有 2 门课程。要学习课程 1，你需要先完成课程 0。因此，正确的课程顺序为 [0,1] 。
 * 示例2:
 * 输入: 4, [[1,0],[2,0],[3,1],[3,2]]
 * 输出: [0,1,2,3] or [0,2,1,3]
 * 解释:总共有 4 门课程。要学习课程 3，你应该先完成课程 1 和课程 2。并且课程 1 和课程 2 都应该排在课程 0 之后。
 * 因此，一个正确的课程顺序是[0,1,2,3] 。另一个正确的排序是[0,2,1,3] 。
 * 说明:
 * 输入的先决条件是由边缘列表表示的图形，而不是邻接矩阵。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 提示:
 * 这个问题相当于查找一个循环是否存在于有向图中。如果存在循环，则不存在拓扑排序，因此不可能选取所有课程进行学习。
 * 通过 DFS 进行拓扑排序 - 一个关于Coursera的精彩视频教程（21分钟），介绍拓扑排序的基本概念。
 * 拓扑排序也可以通过BFS完成。
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/9 13:29
 */
public class FindOrder {
    // 几点优化：
    // 1. map可以替换为list
    // 2. stack可以替换为数组

    // 深度优先搜索，基于出度
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 0. 数组模拟栈，并且从后往前输入省略最后一步倒转
        int[] res = new int[numCourses];
        index = numCourses - 1;
        // 1. visited有三种状态 0 未搜索 1 搜索中 2 已搜索完成
        int[] visited = new int[numCourses];
        // 2. 构建list，index:父节点 value:子节点列表
        List<List<Integer>> map = new ArrayList<>(numCourses);
        // 3. 初始化map
        for (int i = 0; i < numCourses; i++) map.add(new ArrayList<>());
        for (int[] prerequisite : prerequisites)
            map.get(prerequisite[1]).add(prerequisite[0]);
        // 4. dfs 结果为false表示遇到环了直接输出空数组
        for (int node = 0; node < numCourses; node++)
            if (visited[node] == 0)
                if (dfs(node, visited, map, res)) return new int[0];
        // 5. 输出结果
        return res;
    }

    // 广度优先搜索，基于入度
    private int[] bfs(int numCourses, int[][] prerequisites) {
        // 0. 构建结果数组
        int[] res = new int[numCourses];
        int len = 0;
        // 1. 构建入度数组
        int[] inDegree = new int[numCourses];
        // 2. 构建list，index:父节点 value:子节点列表
        List<List<Integer>> map = new ArrayList<>(numCourses);
        // 3. 初始化map
        for (int i = 0; i < numCourses; i++) map.add(new ArrayList<>());
        for (int[] prerequisite : prerequisites) {
            inDegree[prerequisite[0]]++;
            map.get(prerequisite[1]).add(prerequisite[0]);
        }
        Queue<Integer> queue = new ArrayDeque<>();
        // 4. 将入度为0的节点假如queue中，并输出结果
        for (int i = 0; i < numCourses; i++)
            if (inDegree[i] == 0) queue.offer(i);
        // 5. 广度遍历图，每次取出入度为0的节点(并加入结果)，并将其相邻节点入度-1，循环遍历
        while (!queue.isEmpty()) {
            int node = queue.poll();
            res[len++] = node;
            for (int subNode : map.get(node))
                if (--inDegree[subNode] == 0) queue.offer(subNode);
        }
        return len == numCourses ? res : new int[0];
    }

    // 深度优先搜索，基于出度，true表示有环
    private int index;

    private boolean dfs(int cur, int[] visited, List<List<Integer>> map, int[] res) {
        if (visited[cur] == 2) return false;
        List<Integer> subList = map.get(cur);
        if (subList != null) {
            visited[cur] = 1;
            for (int subNode : subList) {
                if (visited[subNode] == 1 || dfs(subNode, visited, map, res))
                    return true;
            }
        }
        visited[cur] = 2;
        res[index--] = cur;
        return false;
    }

    public static void main(String[] args) {
        FindOrder findOrder = new FindOrder();
        int numCourses = 2;
        int[][] prerequisites = {{0, 1}};
        System.out.println(Arrays.toString(findOrder.bfs(numCourses, prerequisites)));
    }
}
