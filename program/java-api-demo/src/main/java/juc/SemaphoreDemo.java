package juc;

import java.util.concurrent.Semaphore;

/**
 * @author duhuang@iflytek.com
 * @version 2019/11/12 19:44
 */
public class SemaphoreDemo {
    public static void main(String[] args) throws InterruptedException {
        Semaphore semaphore = new Semaphore(2);
        System.out.println(semaphore.availablePermits());
        semaphore.acquire();
        System.out.println(semaphore.availablePermits());
        new Thread(()->{
            while (true) {
                System.out.println(semaphore.availablePermits());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        semaphore.acquire();
        System.out.println(semaphore.availablePermits());
        semaphore.acquire();
        System.out.println(semaphore.availablePermits());
        semaphore.release();
        System.out.println(semaphore.availablePermits());
    }
}
