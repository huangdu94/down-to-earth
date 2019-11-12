package thread;

/**
 * synchronized的互斥性
 * <p>
 * synchronized修饰多段代码，但是这些同步块的同步监视器对象是同一个时，那么这些代码间就是互斥的.
 * 多个线程不能同时在这些代码中用运行.
 *
 * @author Bean
 */
public class SyncDemo4 {
    public static void main(String[] args) {
        Coo coo = new Coo();
        new Thread(coo::methodA).start();
        new Thread(coo::methodB).start();
    }
}

class Coo {
    public synchronized void methodA() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ":正在执行方法A.");
            Thread.sleep(5000);
            System.out.println(t.getName() + ":执行A方法完毕.");
        } catch (Exception e) {
        }
    }

    public synchronized void methodB() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ":正在执行方法B.");
            Thread.sleep(5000);
            System.out.println(t.getName() + ":执行B方法完毕.");
        } catch (Exception e) {
        }
    }
}