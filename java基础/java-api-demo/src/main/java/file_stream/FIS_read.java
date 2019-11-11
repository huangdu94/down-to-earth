package file_stream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * java.id.FileInputStream
 * 文件输入流，用于从文件中读取字节
 * @author Bean
 *
 */
public class FIS_read {
	public static void main(String[] args) throws IOException {
		FileInputStream fis=new FileInputStream("fos.txt");
		byte[] buffer=new byte[1024];
		int len=fis.read(buffer);
		String str=new String(buffer,0,len,"utf-8");
		System.out.println(str);
		
		fis.close();
	}
}
