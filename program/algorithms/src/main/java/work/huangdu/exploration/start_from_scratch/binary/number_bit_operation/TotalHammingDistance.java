package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 477. 汉明距离总和
 * 两个整数的 汉明距离 指的是这两个数字的二进制数对应位不同的数量。
 * 计算一个数组中，任意两个数之间汉明距离的总和。
 * 示例:
 * 输入: 4, 14, 2
 * 输出: 6
 * 解释: 在二进制表示中，4表示为0100，14表示为1110，2表示为0010。（这样表示是为了体现后四位之间关系）
 * 所以答案为：
 * HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 注意:
 * 数组中元素的范围为从 0到 10^9。
 * 数组的长度不超过 10^4。
 *
 * @author duhuang@iflytek.com
 * @version 2020/10/15 12:41
 */
public class TotalHammingDistance {
    // 暴力超时
    public int totalHammingDistance(int[] nums) {
        int n = nums.length, total = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                total += hammingDistance(nums[i], nums[j]);
            }
        }
        return total;
    }

    private int hammingDistance(int x, int y) {
        x = x ^ y;
        int distance = 0;
        while (x != 0) {
            distance++;
            x &= (x - 1);
        }
        return distance;
    }

    public int totalHammingDistance2(int[] nums) {
        int n = nums.length, total = 0;
        for (int k = 0; k < 32; k++) {
            int t = 0;
            for (int i = 0; i < n; i++) {
                t += (nums[i] & 1);
                nums[i] >>>= 1;
            }
            total += t * (n - t);
        }
        return total;
    }

    public int totalHammingDistance3(int[] nums) {
        int n = nums.length, total = 0;
        int[] counts = new int[32];
        for (int num : nums) {
            int i = 0;
            while (num != 0) {
                counts[i++] += (num & 1);
                num >>>= 1;
            }
        }
        for (int count : counts) {
            total += (count * (n - count));
        }
        return total;
    }

    public int totalHammingDistance4(int[] nums) {
        int n = nums.length, total = 0;
        int[] counts = new int[32];
        for (int num : nums) {
            int i = 0;
            while (num != 0) {
                counts[i++] += (num & 1);
                num >>>= 1;
            }
        }
        for (int count : counts) {
            total += (count * (n - count));
        }
        return total;
    }
}
