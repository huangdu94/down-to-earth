package file_stream.randomaccessfile;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 读取一个字节
 * @author Bean
 *
 */
public class RandomAccessFile_read {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf=new RandomAccessFile("raf.dat","r");
		/*
		 * 读取一个字节，并以int形式返回
		 * 若返回值为-1表示读取到了文件末尾.
		 */
		int d=raf.read();
		System.out.println(d);
		raf.close();
	}
}
