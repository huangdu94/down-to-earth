package math_random_bignumber;

import java.util.Random;

/**
 * 生成随机数
 *
 * @author duhuang@iflytek
 * @version 2019/11/11 15:02
 */
public class RandomDemo {
    public static void main(String[] args) {
        Random random = new Random();
        //返回一个伪随机数，它是取自此随机数生成器序列的、在 0（包括）和指定值（不包括）之间均匀分布的 int 值
        System.out.println(random.nextInt(11) + 1); //1-10整数随机
    }
}
