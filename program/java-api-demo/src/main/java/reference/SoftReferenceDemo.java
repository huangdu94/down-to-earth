package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;

/**
 * 一、强引用
 * 1.强引用可以直接访问目标对象
 * 2.强引用所指向的对象在任何时候都不会对系统回收
 * 3.强引用可能导致内存泄露
 * 二、软引用
 * 1.除了强引用外，最强的引用类型
 * 2.当堆使用率临近阈值时，才回去回收软引用的对象
 * 三、引用队列
 * 1.当对象被回收时，就会被加入到这个队列中
 *
 * @author duhuang@iflytek
 * @version 2019/11/20 15:07
 */
public class SoftReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        // 强引用
        MyObject obj = new MyObject();
        ReferenceQueue<MyObject> softQueue = new ReferenceQueue<>();
        SoftReference<MyObject> softRef = new SoftReference<>(obj, softQueue);
        new CheckRefQueue<>(softQueue).start();
        obj = null;
        System.gc();
        System.out.println("After GC:Soft Get= " + softRef.get());
        System.out.println("分配大块内存");
        byte[] b = new byte[400 * 1024 * 925];
        System.out.println("After new byte[]:Soft Get= " + softRef.get());
        Thread.sleep(1000);
        byte[] b1 = new byte[400 * 1024 * 925];
        Thread.sleep(1000);
        byte[] b2 = new byte[400 * 1024 * 925];
        Thread.sleep(1000);
        byte[] b3 = new byte[400 * 1024 * 925];
        Thread.sleep(1000);
    }
}
