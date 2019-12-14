package jvm;

/**
 * JVM参数
 * -client -server
 * -ea 开启assert断言机制
 * -verbose:gc 显示gc过程
 * -XX:+PrintGCDetails
 * <p>
 * -XX:+UseSpinning 开启自旋锁
 * -XX:PreBlockSpin 设置自旋锁的等待次数
 * <p>
 * -XX:+DoEscapeAnalysis 逃逸分析
 * -XX:+EliminateLocks 锁消除
 * -XX:-DoEscapeAnalysis 关闭逃逸分析
 * -XX:-EliminateLocks 关闭锁消除
 * <p>
 * 虚拟机栈大小设置
 * -Xss1M
 *
 * @author duhuang@iflytek.com
 * @version 2019/12/14 11:43
 */
public class JvmDemo {
    public static void main(String[] args) {
        class Local {
            int i;
        }
        System.out.println(Local.class.getClassLoader());
        System.out.println(Local.class.getClassLoader().getParent());
        System.out.println(Local.class.getClassLoader().getParent().getParent());
    }
}

