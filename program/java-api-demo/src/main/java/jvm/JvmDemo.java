package jvm;

/**
 * JVM参数
 * -client -server
 * -ea 开启assert断言机制
 * -verbose:gc 显示gc过程
 * -XX:+PrintGCDetails 显示gc详情
 *
 * -XX:+UseSpinning 开启自旋锁
 * -XX:PreBlockSpin 设置自旋锁的等待次数
 *
 * -XX:+DoEscapeAnalysis 逃逸分析
 * -XX:+EliminateLocks 锁消除
 * -XX:-DoEscapeAnalysis 关闭逃逸分析
 * -XX:-EliminateLocks 关闭锁消除
 *
 * 虚拟机栈大小设置
 * -Xmx 最大堆内存   例-Xmx20M
 * -Xms 最小堆内存
 * -Xmn 设置新生代的大小
 * (-XX:NewSize 新生代初始值 -XX:MaxNewSize 新生代最大值 例-XX:NewSize=10M)
 * -XX:PermSize 方法区初始大小 -XX:MaxPermSize 方法区最大值
 * -Xss 设置线程栈的大小
 *
 * -XX:SurvivorRatio 新生代中 eden/s0 或 eden/s1(s0 和 s1大小相等)
 * -XX:NewRatio 老年代/新生代
 *
 * -XX:MinHeapFreeRatio 设置堆空间最小空闲比例
 * -XX:MaxHeapFreeRatio 设置堆空间最大空闲比例
 * -XX:TargetSurvivorRatio 设置survivor区的可使用率(当survivor区的空间使用率达到这个数值时，会将对象送入老年代)
 *
 * @author duhuang@iflytek.com
 * @version 2019/12/14 11:43
 */
public class JvmDemo {
    public static void main(String[] args) {
        System.out.println("Hello World.");
    }
}

