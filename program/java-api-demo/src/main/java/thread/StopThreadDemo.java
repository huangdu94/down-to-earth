package thread;

/**
 * 优雅的停止一个线程
 * 2019/10/28 22:13
 *
 * @author DuHuang
 */
public class StopThreadDemo {
    private static boolean  flag = true;
    public static void main(String[] args){
        new Thread(() -> {
            long num = 0;
            while (flag) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(num++);
            }
        }).start();
        //如果想让线程停止只需要将flag设置为false
    }
}
