package file_stream;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 处理流，又称高级流，作用是使用它们可以简化我们的
 * 读写操作.
 * 
 * 缓冲流：提高读写效率
 * BufferedInputStream:缓冲字节输入流，提高读取效率
 * BufferedOutputStream:缓冲字节输入流，提高写出效率
 * @author Bean
 *
 */
public class CopyDemo2 {
	public static void main(String[] args) throws IOException {
		long begin=System.currentTimeMillis();
		FileInputStream fis=new FileInputStream("RAF_read.java");
		BufferedInputStream bis=new BufferedInputStream(fis);
		FileOutputStream fos=new FileOutputStream("RAF_read_cp.java");
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		int d=-1;
		while((d=bis.read())!=-1) {
			bos.write(d); 
		}
		bis.close();
		bos.close();
		long end=System.currentTimeMillis();
		System.out.println("文件复制完毕.耗时："+(end-begin)/1000.0+"秒.");
		
	}
}
