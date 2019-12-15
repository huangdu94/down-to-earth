package jvm;

/**
 * JVM参数
 * -client -server
 * -ea 开启assert断言机制
 * -XX:+UnlockExperimentalVMOptions 允许使用实验性参数(使用G1回收器需开启)
 *
 * -XX:CompileThreshold JIT编译的阈值，当函数的调用次数超过该值，JIT就将字节码编译成本地机器码(client 默认1500 server 默认10000)
 *      -XX:+PrintCompilation 打印JIT编译过程
 *      -XX:+CITime 打印JIT编译的基本信息
 * -XX:+HeapDumpOnOutOfMemoryError OutOfMemory发生时导出堆快照到文件
 *      -XX:HeapDumpPath 导出地址
 * -XX:OnOutOfMemoryError OutOfMemory发生时运行一个脚本
 *
 * -XX:+TraceClassLoading
 * -XX:+TraceClassUnLoading 跟踪类的加载和卸载情况
 *
 * -XX:-UseSplitVerifier 指定使用旧类校验器
 * -XX:-FailOverToOldVerifier 关闭再次校验功能
 *
 * -XX:+UseCompressedOops 打开指针压缩
 *
 * Solaris下线程控制
 * -XX:+UseBoundThreads 绑定所有用户线程到内核线程，减少线程进入饥饿状态的次数
 * -XX:+UseLWPSynchronization 使用内核线程替换线程同步
 * -XX:+UseVMInterruptibleIO 允许运行时中断线程
 *
 * 多线程相关参数
 * -XX:+UseSpinning 开启自旋锁
 * -XX:PreBlockSpin 设置自旋锁的等待次数
 * -XX:+DoEscapeAnalysis 逃逸分析
 * -XX:+EliminateLocks 锁消除
 * -XX:-DoEscapeAnalysis 关闭逃逸分析
 * -XX:-EliminateLocks 关闭锁消除
 *
 * 内存相关设置
 * -Xmx 最大堆内存   例-Xmx20M
 * -Xms 最小堆内存
 * -Xmn 设置新生代的大小
 * (-XX:NewSize 新生代初始值 -XX:MaxNewSize 新生代最大值 例-XX:NewSize=10M)
 * -XX:PermSize 方法区初始大小 -XX:MaxPermSize 方法区最大值
 * -Xss 设置线程栈的大小(虚拟机栈)
 *      -XX:SurvivorRatio 新生代中 eden/s0 或 eden/s1(s0 和 s1大小相等)
 *      -XX:NewRatio 老年代/新生代
 *      -XX:MinHeapFreeRatio 设置堆空间最小空闲比例
 *      -XX:MaxHeapFreeRatio 设置堆空间最大空闲比例
 *      -XX:TargetSurvivorRatio 设置survivor区的可使用率(当survivor区的空间使用率达到这个数值时，会将对象送入老年代)
 *      -XX:PretenureSizeThreshold 设置大对象直接进入老年代的阈值(-XX:+PrintTenuringDistribution 查看新生对象晋升老年代的实际阈值)
 *      -XX:MaxTenuringThreshold 设置对象进入老年代的年龄的最大值
 *      -XX:+UseLargePages 启用大页
 *          -XX:LargePageSizeInBytes 设置大页的大小
 *
 * GC相关设置
 * -Xloggc 指定GC日志的输出位置
 * -verbose:gc
 * -XX:+PrintGC 显示GC过程
 * -XX:+PrintGCDetails 显示GC详情
 * -XX:+DisableExplicitGC 禁用显式GC
 * -XX:+PrintHeapAtGC 每次GC，打印堆的使用情况
 * -XX:+PrintGCApplicationStoppedTime GC发生停顿的时间
 *      -XX:+PrintGCApplicationConcurrentTime GC发生停顿时程序执行时间
 * -Xnoclassgc 不需要回收类
 *
 * -XX:+UseSerialGC 新生代，老年代都使用串行回收器
 *
 * -XX:+UseParNewGC 新生代使用并行收集器，老年代使用串行回收器
 *      -XX:ParallelGCThreads 指定并行收集器工作时的线程数量
 *
 * -XX:+UseConcMarkSweepGC 新生代使用并行收集器，老年代使用CMS
 *      -XX:CMSInitiatingOccupancyFraction 可以指定当老年代空间使用率达到多少时，进行一次CMS垃圾回收
 *      -XX:UseCMSCompactAtFullCollection 垃圾收集完后，进行一次内存碎片整理
 *      -XX:CMSFullGCsBeforeCompaction 多少次CMS后，进行内存压缩
 *      -XX:ParallelCMSThreads 设置CMS线程数
 *      -XX:+CMSClassUnloadingEnabled 允许对类元素数据进行回收
 *      -XX:+CMSParallelRemarkEnabled 启用并行重标记
 *      -XX:UseCMSInitiatingOccupancyOnly 表示只在到达阈值的时候，才进行CMS回收
 *      -XX:CMSInitiatingPermOccupancyFraction 当永久区占用率达到这一百分比时，启动CMS回收(开启了对类元素数据进行回收)
 *      -XX:+CMSIncrementalMode 使用增量模式，比较适合单CPU
 *
 * -XX:+UseParallelGC 新生代使用并行回收收集器，老年代使用串行回收器
 * -XX:+UseParallelOldGC 新生代和老年代都使用并行回收收集器
 *      -XX:MaxGCPauseMillis 设置最大垃圾收集停顿时间
 *      -XX:GCTimeRatio 设置吞吐量大小
 *      -XX:ParallelGCThreads 指定并行收集器工作时的线程数量
 *
 * -XX:+UseG1GC 使用G1收集器
 *      +XX:MaxGCPauseMillis    停顿时间
 *      +XX:GCPauseIntervalMillis 指定时间
 *
 * @author duhuang@iflytek.com
 * @version 2019/12/14 11:43
 */
public class JvmDemo {
    public static void main(String[] args) {
        System.out.println("Hello World.");
    }
}

