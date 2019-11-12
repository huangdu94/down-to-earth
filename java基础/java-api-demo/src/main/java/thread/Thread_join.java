package thread;

/**
 * 线程提供了一个方法：join
 * 该方法可以协调多个线程之间同步运行.
 * <p>
 * 同步与异步
 * 所谓同步执行指的执行有先后顺序.
 * 异步执行则执行没有顺序，各干各的.
 * <p>
 * 多线程运行本身的设计是异步运行的.但在某些业务逻辑
 * 中需要它们执行各自任务时要有先后，这时就需要协调
 * 这些线程之间同步运行.
 *
 * @author Bean
 */
public class Thread_join {
    //表示图片是否下载完毕
    private static boolean isFinish = false;

    public static void main(String[] args) {
        /*
         * JDK8之前，由于JVM内存设计问题，有一个要求：
         * 当一个方法的局部内部类中要使用这个方法的其他局部变量时，该变量必须是final的.
         */
        final Thread download = new Thread() {
            public void run() {
                System.out.println("down:开始下载图片...");
                for (int i = 0; i <= 100; i++) {
                    System.out.println("down:" + i + "%");
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                    }
                }
                System.out.println("down:图片下载完毕!");
                isFinish = true;
            }
        };

        Thread show = new Thread() {
            public void run() {
                System.out.println("show:开始显示图片...");
                /*
                 * 等待下载线程将图片下载完毕...
                 * 当show线程调用download的join方法时，
                 * show线程处于阻塞状态，直到download线程
                 * 将任务全部执行完毕后，show才会结束阻塞继续
                 * 执行join方法后续代码.
                 */
                try {
                    download.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (!isFinish) {
                    /*
                     * 将一个异常抛出到该线程的run方法之外意味着该线程就结束了
                     */
                    throw new RuntimeException("图片没有下载完毕.");
                }
                System.out.println("show:图片显示完毕!");
            }
        };

        download.start();
        show.start();
    }
}