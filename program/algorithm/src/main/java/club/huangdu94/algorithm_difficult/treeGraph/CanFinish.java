package club.huangdu94.algorithm_difficult.treeGraph;

import java.util.Arrays;

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
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        int[] courses = new int[numCourses];
        boolean[] visited = new boolean[numCourses];
        Arrays.fill(courses, -1);
        for (int[] prerequisite : prerequisites) courses[prerequisite[0]] = prerequisite[1];
        for (int i = 0; i < numCourses; i++)
            if (!visited[i] && isRing(courses, i, visited)) return false;
        return true;
    }

    // 判断是否有环 有环true 没环false
    private boolean isRing(int[] courses, int start, boolean[] visited) {
        if (start == -1 || courses[start] == -1)
            return false;
        visited[start] = true;
        int slow = start;
        int fast = courses[start];
        while (fast != -1 && slow != fast) {
            slow = courses[slow];
            visited[fast] = true;
            fast = courses[fast];
            if (fast == -1) break;
            visited[fast] = true;
            fast = courses[fast];
        }
        return fast != -1;
    }

    public static void main(String[] args) {
        CanFinish canFinish = new CanFinish();
        int numCourses = 3;
        int[][] prerequisites = {{1, 0}, {1, 2}, {0, 1}};
        boolean result = canFinish.canFinish(numCourses, prerequisites);
        System.out.println(result);
    }
}
