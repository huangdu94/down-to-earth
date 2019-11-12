package thread;

/**
 * 有效缩小同步范围可以在保证并发安全的前提下提高并发执行效率.
 * 同步块，同步块可以更精确的控制需要同步的代码片段.
 *
 * @author Bean
 */
public class SyncDemo2 {
    public static void main(String[] args) {
        Shop shop = new Shop();
        new Thread(shop::buy, "张秀刚").start();
        new Thread(shop::buy, "于浩").start();
    }
}

class Shop {
    public void buy() {
        try {
            Thread t = Thread.currentThread();
            System.out.println(t.getName() + ":正在选衣服.");
            Thread.sleep(5000);
            /*
             * 同步块：
             * synchronized（同步监视器对象）{
             * 		需要同步执行的代码片段
             * }
             * 同步监视器对象可以是java中任何类的实例，
             * 只要保证多个线程看到的是"同一个"对象，
             * 那么这些线程在执行同步块中的代码就是同步执行的.
             *
             * 在一个方法上使用synchronized，那么同步监视器对象就是该方法所属对象，即：方法中的"this".
             */
            synchronized (this) {
                System.out.println(t.getName() + ":正在试衣服.");
                Thread.sleep(5000);
            }

            System.out.println(t.getName() + "：结帐离开.");
        } catch (Exception e) {
        }
    }
}