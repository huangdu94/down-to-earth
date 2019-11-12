package juc;

import java.util.concurrent.locks.StampedLock;

/**
 * @author duhuang@iflytek.com
 * @version 2019/11/12 19:24
 */
public class StampedLockDemo {
    public static void main(String[] args) {
        StampedLock stampedLock = new StampedLock();
        stampedLock.tryConvertToWriteLock(1);
        stampedLock.validate(1);
    }
}
