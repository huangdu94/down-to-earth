package math_random_bignumber;

/**
 * Math数学计算
 *
 * @author duhuang@iflytek
 * @version 2019/11/11 14:47
 */
public class MathDemo {
    public static void main(String[] args) {
        //Math中的常数
        System.out.println("圆周率：" + Math.PI);
        System.out.println("natural logarithms: " + Math.E);
        //Math数学计算
        //生成大于等于0，小于1的double
        System.out.println(Math.random());
        //求两个数中的大数
        System.out.println(Math.max(1,2));
        //求绝对值
        System.out.println(Math.abs(-1));
        //三角函数 反三角函数
        System.out.println(Math.sin(Math.PI));
        System.out.println(Math.asin(-1));
        System.out.println(Math.cos(Math.PI));
        System.out.println(Math.acos(-1));
        //...
    }
}
