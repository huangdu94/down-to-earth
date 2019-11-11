package date;

import java.util.Calendar;

/**
 * void set(int field,int value)
 * 设置当前Calendar表示的时间中指定时间分量为给定的值.
 * @author Bean
 */
public class Calendar_set {
	public static void main(String[] args) {
		Calendar calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
		/*
		 * 2008-08-08 20:08:08
		 */
		//设置年
		calendar.set(Calendar.YEAR,2008);
		calendar.set(Calendar.MONTH,Calendar.AUGUST);
		calendar.set(Calendar.DATE,8);
		calendar.set(Calendar.HOUR_OF_DAY,20);
		calendar.set(Calendar.MINUTE,8);
		calendar.set(Calendar.SECOND,8);
		System.out.println(calendar.getTime());
		
		/*
		 * 设置自己出生的那天
		 * 查看那天是星期几，以及那天是那一年的第几天？
		 */
		calendar.set(Calendar.YEAR,1994);
		calendar.set(Calendar.MONTH,Calendar.JANUARY);
		calendar.set(Calendar.DATE,23);
		char[] data= {'日','一','二','三','四','五','六'};
		System.out.println("我出生那天是星期"+data[calendar.get(Calendar.DAY_OF_WEEK)-1]+",那天是那一年的第"+calendar.get(Calendar.DAY_OF_YEAR)+"天.");
	}
} 