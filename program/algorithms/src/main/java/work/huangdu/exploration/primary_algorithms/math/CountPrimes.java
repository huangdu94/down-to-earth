package work.huangdu.exploration.primary_algorithms.math;

/**
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 * @author yiyun (huangdu.hd@alibaba-inc.com)
 * @date 2020/6/29 17:21
 */
public class CountPrimes {
    public int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime(i))
                count++;
        }
        return count;
    }

    /**
     * 判断n是否为质数
     *
     * @param n 参数
     * @return true 是质数
     */
    private boolean isPrime(int n) {
        if (n <= 3)
            return n > 1;
        if (n % 2 == 0)
            return false;
        /* 6X+1 6X+2 6X+3 6X+4 6X+5
           X=0 6X+1 6X+2 6X+3 6X+4 都排除了
           X>=1 6X+2 6X+4 为偶数排除 6X+3 = 3(2X+1) 排除
           故只需要考虑6X+1 6X+5
         */
        if (n % 6 != 1 && n % 6 != 5)
            return false;
        for (int i = 3; i <= Math.sqrt(n); i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println(new CountPrimes().countPrimes(1500000));
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - start) / 1000.0 + "s.");
    }
}
