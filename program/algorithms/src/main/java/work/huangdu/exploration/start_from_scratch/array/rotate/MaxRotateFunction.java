package work.huangdu.exploration.start_from_scratch.array.rotate;

/**
 * 396. 旋转函数
 * 给定一个长度为 n 的整数数组 A 。
 * 假设 Bk 是数组 A 顺时针旋转 k 个位置后的数组，我们定义 A 的“旋转函数” F 为：
 * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]。
 * 计算F(0), F(1), ..., F(n-1)中的最大值。
 * 注意:
 * 可以认为 n 的值小于 105。
 * 示例:
 * A = [4, 3, 2, 6]
 * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
 * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
 * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
 * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
 * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/16 13:29
 */
public class MaxRotateFunction {
    public int maxRotateFunction(int[] A) {
        int n = A.length, sum = 0, f = 0;
        for (int i = 0; i < n; i++) {
            sum += A[i];
            f += i * A[i];
        }
        int maxF = f;
        for (int i = 1; i < n; i++) {
            // f = f - (n - 1) * A[n - i] + sum - A[n - i];
            f += sum - n * A[n - i];
            if (f > maxF) maxF = f;
        }
        return maxF;
    }

    public static void main(String[] args) {
        MaxRotateFunction function = new MaxRotateFunction();
        int[] A = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(function.maxRotateFunction(A));
    }
}
