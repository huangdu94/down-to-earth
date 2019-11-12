package thread;

/**
 * 静态方法使用synchronized修饰后，该方法一定具有同步效果
 * (相当于方法的同步锁由this变成了类,即由对象锁变成了类锁)
 *
 * @author Bean
 */
public class SyncDemo3 {
    public static void main(String[] args) {
        new Thread(Foo::dosome).start();
        new Thread(Foo::dosome).start();
    }
}

class Foo {
    public static synchronized void dosome() {
        Thread t = Thread.currentThread();
        System.out.println(t.getName() + ":正在运行");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getName() + ":运行dos");
    }
}