package work.huangdu.question_bank.medium;

/**
 * 60. 第k个排列
 * 给出集合 [1,2,3,…,n]，其所有元素共有 n! 种排列。
 * 按大小顺序列出所有排列情况，并一一标记，当 n = 3 时, 所有排列如下：
 * "123"
 * "132"
 * "213"
 * "231"
 * "312"
 * "321"
 * 给定 n 和 k，返回第 k 个排列。
 * 说明：
 * 给定 n 的范围是 [1, 9]。
 * 给定 k 的范围是[1,  n!]。
 * 示例 1:
 * 输入: n = 3, k = 3
 * 输出: "213"
 * 示例 2:
 * 输入: n = 4, k = 9
 * 输出: "2314"
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/9/5 10:15
 */
public class GetPermutation {
    private static final int[] factorial = {1, 2, 6, 24, 120, 720, 5040, 40320};

    public String getPermutation(int n, int k) {
        if (n == 1) return "1";
        char[] chars = new char[n];
        boolean[] visited = new boolean[n];
        int i = 0, j = n - 2;
        k--;
        while (k != 0) {
            chars[i++] = (char) (getNumber(visited, k / factorial[j]) + '1');
            k = k % factorial[j--];
        }
        j = 0;
        while (i < n) {
            while (visited[j]) {
                j++;
            }
            visited[j] = true;
            chars[i++] = (char) (j + '1');
        }
        return new String(chars);
    }

    /**
     * 输出剩余数的第k个
     *
     * @param visited 已经输出过的数设为true
     * @param k       第k个
     * @return 剩余数的第k个
     */
    private int getNumber(boolean[] visited, int k) {
        int i = 0;
        while (true) {
            if (!visited[i] && k-- == 0) {
                visited[i] = true;
                return i;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        int n = 4, k = 9;
        System.out.println(new GetPermutation().getPermutation(n, k));
    }
}
