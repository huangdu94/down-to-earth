package date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * 程序起动后，要求用户输入自己的生日，
 * 格式如：1992-08-02
 * 然后经过程序计算，显示该用户到今天为止活了过少天.
 * @author Bean
 */
public class Program1 {
	public static void main(String[] args) throws ParseException {
		Scanner scan=new Scanner(System.in);
		System.out.println("请输入你的生日:（格式：1992-08-02）");
		String birthStr=scan.nextLine();
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date birthday=sdf.parse(birthStr);
		
		Date now=new Date();
		
		long day=(now.getTime()-birthday.getTime())/1000/60/60/24;
		System.out.println("你已经活了："+day+"天");
		
		//计算10000天的纪念日
		birthday.setTime(birthday.getTime()+10000L*24*60*60*1000);
		String str=sdf.format(birthday);
		System.out.println("你出生10000天的纪念日"+str);
		scan.close();
	}
}
