package reference;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 四、弱引用
 * 1.弱引用是一种比软引用更弱的引用类型
 * 2.在系统GC时，只要发现弱引用，不管系统堆空间是否足够，都会将对象进行回收
 *
 * @author duhuang@iflytek
 * @version 2019/11/20 15:41
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
        MyObject obj = new MyObject();
        ReferenceQueue<MyObject> weakQueue = new ReferenceQueue<>();
        WeakReference<MyObject> weakRef = new WeakReference<>(obj, weakQueue);
        new CheckRefQueue<>(weakQueue).start();
        obj = null;
        System.out.println("Before GC:Soft Get= " + weakRef.get());
        System.gc();
        System.out.println("After GC:Soft Get= " + weakRef.get());
    }
}
