package work.huangdu.question_bank.medium;

/**
 * 338. 比特位计数
 * 给定一个非负整数 num。对于 0 ≤ i ≤ num 范围中的每个数字 i ，计算其二进制数中的 1 的数目并将它们作为数组返回。
 * 示例 1:
 * 输入: 2
 * 输出: [0,1,1]
 * 示例 2:
 * 输入: 5
 * 输出: [0,1,1,2,1,2]
 * 进阶:
 * 给出时间复杂度为O(n*sizeof(integer))的解答非常容易。但你可以在线性时间O(n)内用一趟扫描做到吗？
 * 要求算法的空间复杂度为O(n)。
 * 你能进一步完善解法吗？要求在C++或任何其他语言中不使用任何内置函数（如 C++ 中的 __builtin_popcount）来执行此操作。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2021/3/3
 */
public class CountBits {
    // 暴力
    public int[] countBits1(int num) {
        int n = num + 1;
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            int number = i;
            while (number != 0) {
                if ((number & 1) == 1) {
                    counts[i]++;
                }
                number >>= 1;
            }
        }
        return counts;
    }

    // 暴力 + 优化
    public int[] countBits2(int num) {
        int n = num + 1;
        int[] counts = new int[n];
        for (int i = 0; i < n; i++) {
            int number = i;
            while (number != 0) {
                counts[i]++;
                number &= (number - 1);
            }
        }
        return counts;
    }

    // 动态规划
    public int[] countBits(int num) {
        int n = num + 1;
        int[] count = new int[n];
        for (int i = 1; i < n; i++) {
            count[i] = count[i & (i - 1)] + 1;
        }
        return count;
    }
}
