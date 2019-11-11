package thread;
/**
 * Thread提供了一个静态方法：
 * static void sleep(long ms)
 * 该方法可以将运行该方法的线程阻塞指定毫秒，当超时
 * 以后该线程会自动回到RUNNABLE状态等待再次并发运行.
 * 
 * 常使用该方法做间隔时间等操作使用.
 * @author Bean
 */
public class Thread_sleep {
	public static void main(String[] args) {
		long start=System.currentTimeMillis();
		System.out.println("main方法执行了");
		
		try {
			Thread.sleep(5000);
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("main方法结束了");
		long end=System.currentTimeMillis();
		System.out.println("运行时间为（秒）："+(end-start)/1000.0);
	}
}
