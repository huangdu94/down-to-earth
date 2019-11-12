package thread;

/**
 * 线程提供了一个静态方法
 * static Thread currentThread()
 * 该方法可以获取运行该方法的线程
 *
 * @author Bean
 */
public class Thread_currentThread {
    public static void main(String[] args) {
        /*
         * 获取运行main方法的线程
         */
        Thread main = Thread.currentThread();
        System.out.println(main);
        dosome();//main线程执行dosome方法
        new Thread() {
            public void run() {
                Thread t = Thread.currentThread();
                System.out.println(t);
                dosome();//自定义线程执行dosome方法
            }
        }.start();
    }

    public static void dosome() {
        Thread dosome = Thread.currentThread();
        System.out.println("运行dosome方法的线程是" + dosome);
    }
}
