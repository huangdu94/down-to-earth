package work.huangdu.exploration.primary_algorithms.dynamic;

/**
 * @author huangdu.hd@alibaba-inc.com
 * @date 2020/6/27 10:30
 */
public class Test {
    public static void main(String[] args) {
        ClimbStairs stairs = new ClimbStairs();
        System.out.println(stairs.climbStairs2(5));
        MaxProfit profit = new MaxProfit();
        System.out.println(profit.maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
