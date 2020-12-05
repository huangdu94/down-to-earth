package work.huangdu.exploration.intermediate_algorithms.other;

/**
 * 621. 任务调度器
 * 给定一个用字符数组表示的 CPU 需要执行的任务列表。其中包含使用大写的 A - Z 字母表示的26 种不同种类的任务。任务可以以任意顺序执行，并且每个任务都可以在 1 个单位时间内执行完。CPU 在任何一个单位时间内都可以执行一个任务，或者在待命状态。
 * 然而，两个相同种类的任务之间必须有长度为 n 的冷却时间，因此至少有连续 n 个单位时间内 CPU 在执行不同的任务，或者在待命状态。
 * 你需要计算完成所有任务所需要的最短时间。
 * 示例 ：
 * 输入：tasks = ['A','A','A','B','B','B'], n = 2
 * 输出：8
 * 解释：A -> B -> (待命) -> A -> B -> (待命) -> A -> B.
 * 在本示例中，两个相同类型任务之间必须间隔长度为 n = 2 的冷却时间，而执行一个任务只需要一个单位时间，所以中间出现了（待命）状态。
 * 提示：
 * 任务的总个数为 [1, 10000]。
 * n 的取值范围为 [0, 100]。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/22 0:25
 */
public class LeastInterval {
    /**
     * 字母数量为26
     */
    private static final int LETTER_COUNT = 26;

    public int leastInterval(char[] tasks, int n) {
        // 调整n值加1与我们下面的逻辑相符合
        if (n++ == 0)
            return tasks.length;
        // 剩余未完成任务种类数量
        int kinds = 0;
        // 各任务的数量统计
        int[] counts = new int[LETTER_COUNT];
        for (char task : tasks)
            if (counts[task - 'A']++ == 0)
                kinds++;
        int len = 0;
        while (kinds > n) {
            // 循环结束标识
            boolean stop = false;
            while (!stop) {
                // 降序排序(我们只在乎各种类任务的数量,它是ABC还是D对结果完全没有影响)
                // 总是先执行数量最多的前n个
                this.sortDesc(counts);
                for (int k = 0; k < n; k++) {
                    if (--counts[k] == 0) {
                        stop = true;
                        kinds--;
                    }
                }
                len += n;
            }
        }
        // 末尾补偿
        this.sortDesc(counts);
        if (counts[0] != 0) {
            int tail = 1;
            for (int k = 1; counts[k] == counts[k - 1]; k++, tail++) ;
            return len + counts[0] * n - n + tail;
        }
        return len;
    }

    /**
     * 数据量较小，使用插入排序
     *
     * @param nums 排序数组
     */
    private void sortDesc(int[] nums) {
        for (int i = 1, j = i; i < nums.length; j = ++i) {
            int numi = nums[i];
            while (numi > nums[j - 1]) {
                nums[j] = nums[j - 1];
                if (--j == 0)
                    break;
            }
            nums[j] = numi;
        }
    }

    public static void main(String[] args) {
        LeastInterval leastInterval = new LeastInterval();
        char[] tasks = {'A', 'A', 'A', 'A', 'A', 'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int n = 1;
        System.out.println(leastInterval.leastInterval(tasks, n));
    }

    // 贪心
    public int leastInterval2(char[] tasks, int n) {
        if (n == 0) return tasks.length;
        int[] counts = new int[128];
        int maxExec = 0, maxCount = 0;
        for (char task : tasks) {
            maxExec = Math.max(++counts[task], maxExec);
        }
        for (int i = 'A'; i <= 'Z'; i++) {
            if (counts[i] == maxExec) {
                maxCount++;
            }
        }
        return Math.max(tasks.length, (maxExec - 1) * (n + 1) + maxCount);
    }
}
