package thread;

/**
 * 线程
 * 线程可以并发运行多段代码.
 * <p>
 * 创建线程有两种方式(其实有三种,Callable)
 * 方式一：继承Thread,重写run方法
 * run方法中的代码就是需要让线程并发执行的代码（任务）
 *
 * @author Bean
 */
public class ThreadCreateDemo1 {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        MyThread2 myThread2 = new MyThread2();
        MyThread3 myThread3 = new MyThread3();

        myThread1.start();
        myThread2.start();
        myThread3.start();
    }
}

/**
 * 第一种创建线程的方式有两个不足
 * 1：继承冲突
 * 由于java是单继承的，这就导致若继承了Thread当前类就无法继承其他类来复用方法，这在实际开发中是非常不方便的.
 * 2：由于在当前类内部重写run方法，这就导致当前线程与该线程要执行的任务有一个必然的耦合关系.不利于线程复用.
 *
 * @author Bean
 */
class MyThread1 extends Thread {
    public void run() {
        for (int i = 1; i < 1000; i++) {
            System.out.println("第" + i + "次运行线程一");
        }
    }
}

class MyThread2 extends Thread {
    public void run() {
        for (int i = 1; i < 1000; i++) {
            System.out.println("第" + i + "次运行线程二");
        }
    }
}

class MyThread3 extends Thread {
    public void run() {
        for (int i = 1; i < 1000; i++) {
            System.out.println("第" + i + "次运行线程三");
        }
    }
}