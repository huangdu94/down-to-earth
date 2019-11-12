package thread;

/**
 * 获取线程相关信息的方法
 *
 * @author Bean
 */
public class Thread_info {
    public static void main(String[] args) {
        new Thread("终结者") {//Thread创建时，传入字符串即为线程名字
            public void run() {
                System.out.println("线程执行了");

                //获取线程ID id:唯一标识
                long id = this.getId();
                System.out.println(id);

                //获取名字
                String name = this.getName();
                System.out.println(name);

                //获取线程优先级
                int priority = this.getPriority();
                System.out.println(priority);

                //查看当前线程是否为守护线程
                boolean isDaemon = this.isDaemon();
                System.out.println(isDaemon);

                //查看线程是否处于活动状态
                boolean isAlive = this.isAlive();
                System.out.println(isAlive);

                //查看线程是否被中断
                boolean isInterrupted = this.isInterrupted();
                System.out.println(isInterrupted);

                this.interrupt();
            }
        }.start();
    }
}
