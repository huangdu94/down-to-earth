package date;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 实现一个电子表功能
 * 每秒钟在控制台输出当前系统时间.
 * 格式如：11：47：55
 * @author Bean
 */
public class Program2 {
	public static void main(String[] args) throws InterruptedException {
		while(true) {
			Thread.sleep(1000);
			showTime();
		}
	}
	
	public static void showTime() {
		Date now=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStr=sdf.format(now);
		System.out.println(timeStr);
		clear();
	}

	public static void clear() {
		for(int i=0;i<22;i++) {
			System.out.println();
		}
	}
}


