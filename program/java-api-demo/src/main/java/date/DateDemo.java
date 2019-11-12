package date;
import java.util.Date;

/**
 * java.util.Date
 * Date的每一个实例用于表示一个确切的时间点.
 * 内部维护一个long值，该值表示自1970年1月1日 00：00：00
 * 到当前Date实例所表示的时间之间所经过的毫秒值.
 * 由于Date存在时区与千年虫问题，所以在JDK1.1版本起，大部分方法都被声明为过时的.
 * 为此，现在使用Date仅用来表示一个时间，并不对它做其他操作.
 * @author Bean
 */
public class DateDemo {
	public static void main(String[] args) {
		//默认创建出来即表示当前系统时间
		Date now=new Date();
		System.out.println(now);
		
		//获取当前Date内部维护的long值
		long time=now.getTime();
		System.out.println(time);
		
		time+=1000*60*60*24;
		
		now.setTime(time);
		System.out.println(now);
		
		
	}
}

