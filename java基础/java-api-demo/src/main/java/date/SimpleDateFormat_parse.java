package date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 将字符串按照指定的日期格式解析为Date
 * @author Bean
 */
public class SimpleDateFormat_parse {
	public static void main(String[] args) throws ParseException {
		String str="2008-08-08 20:08:08";
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date=sdf.parse(str);
		
		System.out.println(date);
	}
}
