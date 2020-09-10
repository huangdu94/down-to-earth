package club.huangdu94.exploration.advanced_algorithms.other;

import java.util.Arrays;

/**
 * 406. 根据身高重建队列
 * 假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来重建这个队列。
 * 注意：
 * 总人数少于1100人。
 * 示例
 * 输入:
 * [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
 * 输出:
 * [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
 *
 * @author duhuang@iflytek.com
 * @version 2020/9/8 11:38
 */
public class ReconstructQueue {
    public int[][] reconstructQueue1(int[][] people) {
        int len = people.length;
        Arrays.sort(people, (o1, o2) -> o1[1] == o2[1] ? o1[0] - o2[0] : o1[1] - o2[1]);
        for (int i = 1; i < len; i++) {
            if (people[i][1] == 0) continue;
            int[] p = people[i];
            int j = 0, count = 0, height = p[0], target = p[1];
            while (count <= target) {
                if (height <= people[j][0]) count++;
                j++;
            }
            System.arraycopy(people, j - 1, people, j, i + 1 - j);
            people[j - 1] = p;
        }
        return people;
    }

    public int[][] reconstructQueue(int[][] people) {
        int len = people.length, i = 0;
        Arrays.sort(people, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        while (i < len && people[i][0] == people[0][0]) i++;
        while (i < len) {
            if (people[i][1] != i) {
                int[] temp = people[i];
                System.arraycopy(people, temp[1], people, temp[1] + 1, i - temp[1]);
                people[temp[1]] = temp;
            }
            i++;
        }
        return people;
    }

    public static void main(String[] args) {
        ReconstructQueue reconstructQueue = new ReconstructQueue();
        int[][] people = {{6, 0}, {1, 1}, {8, 0}, {7, 1}, {9, 0}, {2, 4}, {2, 5}, {3, 4}, {7, 3}, {0, 6}};
        System.out.println(Arrays.deepToString(reconstructQueue.reconstructQueue(people)));
    }
}
