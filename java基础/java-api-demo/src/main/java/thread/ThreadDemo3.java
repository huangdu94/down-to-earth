package thread;
/**
 * 使用匿名内部类完成两种方式线程的创建
 * @author Bean
 */
public class ThreadDemo3 {
	public static void main(String[] args) {
		/*
		 * 使用匿名内部类完成方式一创建线程
		 */
		new Thread() {
			public void run() {
				for(int i=0;i<1000;i++) {
					System.out.println("你喜欢吃什么？");
				}
			}
		}.start();
		/*
		 * 使用匿名内部类完成方式二创建Runnable
		 */
		new Thread(new Runnable() {
			public void run() {
				for(int i=0;i<1000;i++) {
					System.out.println("我喜欢吃饭.");
				}
			}
		}
				).start();
		/*
		 * 方式二，Lambda表达式版
		 */
		new Thread(()->{
			for(int i=0;i<1000;i++) {
				System.out.println("我喜欢吃饭.");
			}
		}
				).start();
	}
}
