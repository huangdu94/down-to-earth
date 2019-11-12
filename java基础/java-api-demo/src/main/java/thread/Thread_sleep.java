package thread;

import java.util.Scanner;

/**
 * Thread提供了一个静态方法：
 * static void sleep(long ms)
 * 该方法可以将运行该方法的线程阻塞指定毫秒，当超时
 * 以后该线程会自动回到RUNNABLE状态等待再次并发运行.
 * <p>
 * 常使用该方法做间隔时间等操作使用.
 *
 * @author Bean
 */
public class Thread_sleep {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        System.out.println("main方法执行了");

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main方法结束了");
        long end = System.currentTimeMillis();
        System.out.println("运行时间为（秒）：" + (end - start) / 1000.0);
    }
}

/**
 * 输入一个数字，从该数字开始倒数，直到0为止.
 * 没隔一秒倒数一次.
 *
 * @author Bean
 */
class SleepDemo {
    public static void main(String[] args) {
        System.out.println("Please input a number that is great than zero:");
        Scanner scan = new Scanner(System.in);
        try {
            int number = scan.nextInt();//int number=System.in.read();
            System.out.println("Start...");
            for (int i = number; i >= 0; i--) {
                Thread.sleep(1000);
                System.out.println(i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("End.");
    }
}