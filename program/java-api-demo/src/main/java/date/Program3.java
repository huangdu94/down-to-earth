package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 * 计算促销日期
 * 促销日期为商品过期日前两周的周三
 * 
 * 程序启动后，要求用户输入一个商品的生产日期，以及保质期的天数.
 * 然后经过处理后输出该商品的促销日期
 * 日期格式：yyyy-MM-dd
 * @author Bean
 */
public class Program3 {
	private Scanner scan=new Scanner(System.in);
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	
	private class Information{
		public static final String STR1="请输入商品的生产日期：";
		public static final String STR2="请输入商品的保质期天数：";
		public static final String STR3="促销日期是：";
	}
	
	private Calendar scanProduceDate()throws ParseException {
		System.out.println(Information.STR1);
		String dateStr=scan.nextLine();
		Date produceDate=sdf.parse(dateStr);
		
		Calendar produceCalendar=Calendar.getInstance();
		produceCalendar.setTime(produceDate);
		
		return produceCalendar;
	}
	
	private int scanExistDay() {
		System.out.println(Information.STR2);
		int existDay=scan.nextInt();
		return existDay;
	}
	
	private Calendar computer(Calendar date,int day) {
		date.add(Calendar.DATE,day);
		date.add(Calendar.WEEK_OF_YEAR,-2);
		date.set(Calendar.DAY_OF_WEEK,Calendar.WEDNESDAY);
		return date;
	}
	
	private void start() throws ParseException {
		Calendar produceCalendar=scanProduceDate();
		int existDay=scanExistDay();
		scan.close();
		
		Calendar resultCalendar=computer(produceCalendar,existDay);
		Date resultDate=resultCalendar.getTime();
		String result=sdf.format(resultDate);
		
		System.out.println(Information.STR3);
		System.out.println(result);
	}
	
	public static void main(String[] args) throws ParseException {
		new Program3().start();
	}
}
