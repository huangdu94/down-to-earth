package reference;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

/**
 * 五、虚引用
 * 1.虚引用是所有引用类型中最弱的一个
 * 2.随时会被GC回收
 * 3.get方法取得虚引用时总是会返回null
 * 4.和引用队列一起使用，作用在于跟踪垃圾回收过程
 *
 * @author duhuang@iflytek
 * @version 2019/11/20 15:46
 */
public class PhantomReferenceDemo {
    public static void main(String[] args) throws InterruptedException {
        MyObject obj = new MyObject();
        ReferenceQueue<MyObject> phantomQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> phantomRef = new PhantomReference<>(obj, phantomQueue);
        new CheckRefQueue<>(phantomQueue).start();
        obj = null;
        System.out.println("Before GC:Soft Get= " + phantomRef.get());
        System.gc();
        System.out.println("After GC:Soft Get= " + phantomRef.get());
        Thread.sleep(2000);
        System.gc();
    }
}
