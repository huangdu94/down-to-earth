package work.huangdu.exploration.advanced_algorithms.tree_graph;

import java.util.*;

/**
 * 课程表
 * 你这个学期必须选修 numCourse 门课程，记为0到numCourse-1 。
 * 在选修某些课程之前需要一些先修课程。例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们：[0,1]
 * 给定课程总量以及它们的先决条件，请你判断是否可能完成所有课程的学习？
 * 示例 1:
 * 输入: 2, [[1,0]]
 * 输出: true
 * 解释:总共有 2 门课程。学习课程 1 之前，你需要完成课程 0。所以这是可能的。
 * 示例 2:
 * 输入: 2, [[1,0],[0,1]]
 * 输出: false
 * 解释:总共有 2 门课程。学习课程 1 之前，你需要先完成课程 0；并且学习课程 0 之前，你还应先完成课程 1。这是不可能的。
 * 提示：
 * 输入的先决条件是由 边缘列表 表示的图形，而不是 邻接矩阵 。详情请参见图的表示法。
 * 你可以假定输入的先决条件中没有重复的边。
 * 1 <=numCourses <= 10^5
 *
 * @author duhuang@iflytek.com
 * @version 2020/8/9 13:27
 */
public class CanFinish {

    // 广度优先搜索，基于入度
    private boolean bfs(int numCourses, int[][] prerequisites) {
        // 0. 入度为0的数量
        int count = 0;
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
            count++;
            for (int subNode : map.get(node))
                if (--inDegree[subNode] == 0) queue.offer(subNode);
        }
        return count == numCourses;
    }

    // 深度优先搜索，基于出度
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. visited有三种状态 0 未搜索 1 搜索中 2 已搜索完成
        int[] visited = new int[numCourses];
        // 2. 构建list，index:父节点 value:子节点列表
        List<List<Integer>> map = new ArrayList<>(numCourses);
        // 3. 初始化map
        for (int i = 0; i < numCourses; i++) map.add(new ArrayList<>());
        for (int[] prerequisite : prerequisites)
            map.get(prerequisite[1]).add(prerequisite[0]);
        // 4. dfs 结果为false表示遇到环了直接输出false
        for (int node = 0; node < numCourses; node++)
            if (visited[node] == 0)
                if (dfs(node, visited, map)) return false;
        // 5. 输出结果
        return true;
    }

    private boolean dfs(int cur, int[] visited, List<List<Integer>> map) {
        if (visited[cur] == 2) return false;
        List<Integer> subList = map.get(cur);
        if (subList != null) {
            visited[cur] = 1;
            for (int subNode : subList) {
                if (visited[subNode] == 1 || dfs(subNode, visited, map))
                    return true;
            }
        }
        visited[cur] = 2;
        return false;
    }

    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> courses = new ArrayList<>(numCourses);
        // 一门课对应的先决条件为-1表示这门课可以直接被修
        for (int i = 0; i < numCourses; i++) {
            List<Integer> defaultList = new ArrayList<>();
            defaultList.add(-1);
            courses.add(defaultList);
        }
        // 设置每门课的前置课程
        for (int[] prerequisite : prerequisites) {
            List<Integer> preList = courses.get(prerequisite[0]);
            if (preList.get(0) == -1)
                preList.set(0, prerequisite[1]);
            else
                preList.add(prerequisite[1]);
        }
        int threshold = numCourses + prerequisites.length;
        boolean[] checked = new boolean[numCourses];
        for (int i = 0; i < numCourses; i++)
            if (!checked[i] && isRing(courses, threshold, i, checked)) return false;
        return true;
    }

    // 判断是否有环 有环true 没环false 循环次数超过阈值还没结束肯定有环
    private boolean isRing(List<List<Integer>> courses, int threshold, int start, boolean[] checked) {
        if (start == -1) return false;
        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(start);
        int cur;
        while (!queue.isEmpty()) {
            if (threshold-- <= 0) return true;
            cur = queue.poll();
            checked[cur] = true;
            List<Integer> preList = courses.get(cur);
            if (preList.get(0) != -1) for (int pre : preList) queue.offer(pre);
        }
        return false;
    }

    public static void main(String[] args) {
        CanFinish canFinish = new CanFinish();
        /*int numCourses = 8;
        int[][] prerequisites = {{1, 0}, {2, 6}, {1, 7}, {6, 4}, {7, 0}, {0, 5}};*/
        int numCourses = 1;
        int[][] prerequisites = {};
        boolean result = canFinish.canFinish(numCourses, prerequisites);
        System.out.println(result);
    }
}
