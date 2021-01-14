package work.huangdu.exploration.start_from_scratch.binary.number_bit_operation;

/**
 * 479. 最大回文数乘积
 * 你需要找到由两个 n 位数的乘积组成的最大回文数。
 * 由于结果会很大，你只需返回最大回文数 mod 1337得到的结果。
 * 示例:
 * 输入: 2
 * 输出: 987
 * 解释: 99 x 91 = 9009, 9009 % 1337 = 987
 * 说明:
 * n 的取值范围为 [1,8]。
 *
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/10/5 13:35
 */
public class LargestPalindrome {
    public int largestPalindrome(int n) {
        if (n == 1) return 9;
        int max = (int) Math.pow(10, n) - 1;
        int min = max / 10;
        for (int i = max - 1; i >= min; i--) {
            // 核心思想，构造回文串看看有没有可以相乘得到它的
            // 如果是乘完再判断是否是回文会超时
            String num = String.valueOf(i);
            long palindrome = Long.parseLong(num.concat(new StringBuilder(num).reverse().toString()));
            // 如果j*j已经小于palindrome 说明 如果有可以相乘得到palindrome的组合
            // 则palindrome=j(小)*j(大)，如果存在的话肯定已经返回结果了
            for (long j = max; j * j >= palindrome; j--) {
                if (palindrome % j == 0) {
                    return (int) (palindrome % 1337);
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        LargestPalindrome largest = new LargestPalindrome();
        System.out.println(largest.largestPalindrome(1));
    }
}
