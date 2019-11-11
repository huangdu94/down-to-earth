package date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * java.text.SimpleDateFormat
 * 该类可以将Date与String之间按照一个指定的日期格式进行互相转换
 * @author Bean
 */
public class SimpleDateFormat_format {
	public static void main(String[] args) {
		Date now=new Date();
		System.out.println(now);
		
		/*
		 * 2017-10-26 10:25:55
		 * yyyy-MM-dd hh:mm:ss
		 */
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E a");
		
		/*
		 * String format(Date date)
		 * 将给定的Date表示的时间按照当前SDF指定的日期格式转换为字符串并返回.
		 */
		String str=sdf.format(now);
		System.out.println(str);
		
	}
}
