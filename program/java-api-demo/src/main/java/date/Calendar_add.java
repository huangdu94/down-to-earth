package date;

import java.util.Calendar;

/**
 * void add(int field,int amount)
 * 使当前Calendar表示的时间中指定时间分量的值加上给定的值.若amount是一个负数，则是减去.
 * @author Bean
 */
public class Calendar_add {
	public static void main(String[] args) {
		/*
		 * 查看3年2个月25天以后是哪天？
		 */
		Calendar calendar=Calendar.getInstance();
		
		//加3年
		calendar.add(Calendar.YEAR,3);
		//加2个月
		calendar.add(Calendar.MONTH,2);
		//加25天
		calendar.add(Calendar.DATE,25);
		System.out.println(calendar.getTime());
		
		//设置为周五(调整为当周的周五 星期日开始到星期六)
		calendar.set(Calendar.DAY_OF_WEEK,Calendar.FRIDAY);
		System.out.println(calendar.getTime());
		
		calendar.add(Calendar.DATE,-8);
		System.out.println(calendar.getTime());
	}
}