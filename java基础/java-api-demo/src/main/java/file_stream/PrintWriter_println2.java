package file_stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * PrintWriter提供了常规的构造方法，允许传入
 * 一个字节流或者字符流完成流连接的创建形式
 * @author Bean
 *
 */
public class PrintWriter_println2 {
	public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException {
		FileOutputStream fos=new FileOutputStream("pw2.txt");
		/*
		 * 若希望指定字符集，需要自行连接转换流
		 * 因为转换流可以将字符按照指定的字符集
		 * 转换为字节
		 */
		OutputStreamWriter osw=new OutputStreamWriter(fos,"utf-8");
		PrintWriter pw=new PrintWriter(osw);
		
		pw.println("我在遥望，月亮之上，有多少梦想在自由的飞翔");
		pw.println("昨天已忘，风干了忧伤，热辣辣的情歌就唱到了天亮");
		System.out.println("写出完毕!");
		pw.close();
	}
}
