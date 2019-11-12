package thread;

/**
 * 守护线程
 * 守护线程又称为后天线程.默认创建出来的线程都是前台进程.
 * 使用上守护线程与前台线程没有区别.而区别在于结束实际上有一点不同，即进程结束.
 * 当一个进程中的所有前台进程都结束时，进程结束，无论该进程中的守护线程是否还在运行都要强制将它们结束.
 *
 * @author Bean
 */
public class Thread_setDaemon {
    public static void main(String[] args) {
        Thread rose = new Thread() {
            public void run() {
                for (int i = 0; i < 6; i++) {
                    System.out.println("rose:I will jump!Bye-bye world.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("rose:Ah...Ah...Ah...");
                System.out.println("end.");
            }
        };
        Thread jack = new Thread() {
            public void run() {
                while (true) {
                    System.out.println("jack:You jump!I jump!");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        jack.setDaemon(true);

        rose.start();
        jack.start();
        System.out.println("main方法执行完了.");
    }
}
