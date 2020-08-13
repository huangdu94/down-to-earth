package club.huangdu94.exploration.primary_algorithms.other;

/**
 * 汉明距离
 * 两个整数之间的汉明距离指的是这两个数字对应二进制位不同的位置的数目。
 * 给出两个整数 x 和 y，计算它们之间的汉明距离。
 * 注意：
 * 0 ≤ x, y < 2^31.
 * 示例:
 * 输入: x = 1, y = 4
 * 输出: 2
 * 解释:
 * 1   (0 0 0 1)
 * 4   (0 1 0 0)
 * ↑   ↑
 * 上面的箭头指出了对应二进制位不同的位置。
 *
 * @author duhuang@iflytek.com
 * @version 2020/7/1 17:51
 */
public class HammingDistance {
    public int hammingDistance(int x, int y) {
        int count = 0;
        int xor = x ^ y;
        while (xor != 0) {
            xor &= (xor - 1);
            count++;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(new HammingDistance().hammingDistance(1, 4));
    }
}