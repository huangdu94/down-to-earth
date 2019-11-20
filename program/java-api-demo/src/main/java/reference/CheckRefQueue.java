package com.iflytek.reference;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;

/**
 * 检查引用队列，监控对象回收情况
 *
 * @author duhuang@iflytek
 * @version 2019/11/20 15:18
 */
public class CheckRefQueue extends Thread {
    private ReferenceQueue<MyObject> softQueue;

    CheckRefQueue(ReferenceQueue<MyObject> softQueue) {
        this.softQueue = softQueue;
    }

    @Override
    public void run() {
        Reference<? extends MyObject> obj = null;
        try {
            obj = softQueue.remove();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("ReferenceQueue有值了.");
        if (obj != null) {
            System.out.println("Object for Reference is " + obj.get());
        }
    }
}
