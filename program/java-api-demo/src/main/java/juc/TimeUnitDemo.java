package juc;

import java.util.concurrent.TimeUnit;

/**
 * TimeUnit一个描述时间单元的枚举类
 *
 * @author duhuang@iflytek.com
 * @version 2019/11/11 20:05
 */
public class TimeUnitDemo {
    public static void main(String[] args) {
        TimeUnit nanoseconds = TimeUnit.NANOSECONDS;
        //TimeUnit microseconds = TimeUnit.MICROSECONDS;
        //TimeUnit milliseconds = TimeUnit.MILLISECONDS;
        //TimeUnit seconds = TimeUnit.SECONDS;
        //TimeUnit minutes = TimeUnit.MINUTES;
        //TimeUnit hours = TimeUnit.HOURS;
        TimeUnit days = TimeUnit.DAYS;

        //1.时间类型转换 convert
        System.out.println(nanoseconds.convert(1, days)); //天转换为纳秒
        System.out.println(nanoseconds.toDays(86400000000000L)); //纳秒转换为天
        //2.sleep(long timeout) 线程休眠
        //3.timedWait(Object obj, long timeout) 对象锁休眠
        //4.timedJoin(Thread thread, long timeout) 线程插队
        try {
            nanoseconds.sleep(1000000); //线程休眠一百万纳秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
