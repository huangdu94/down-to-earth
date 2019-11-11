package file_stream.randomaccessfile;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * java.io.RandomAccessFile
 * 用于读写文件数据的类
 * RAF读写文件数据总是在指针当前位置进行读或写，
 * 并且读写后指针会自动后移.
 * 指针是指向文件数据位置的标记（底层实现）.
 * @author Bean
 *
 */
public class RandomAccessFile_write {
	public static void main(String[] args) throws IOException {
		/*
		 * 第二个参数是读写模式，常用的有：
		 * "r"：只读模式,该模式要求读取的文件必须存在
		 * "rw"：读写模式，该模式若文件不存在会自动创建
		 * read write
		 */
		RandomAccessFile raf=new RandomAccessFile("raf.dat","rw");
		/*
		 * void write(int d)
		 * 向文件中写入1个字节，写的是给定int值
		 * 对应2进制的"低八位"
		 */
		raf.write(1);
		System.out.println("写出完毕");
		//读写完毕最终要close
		raf.close();
		
//		for(File sub:subs) {
//			System.out.println(sub);
//		}相当于
//		for(int i=0:i<subs.length;i++) {
//			File sub=subs[i];
//			System.out.println(sub);
//		}
	}
}
