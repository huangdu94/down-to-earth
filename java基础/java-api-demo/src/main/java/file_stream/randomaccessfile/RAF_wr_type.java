package file_stream.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * RAF读写基本类型数据以及RAF对于指针的操作
 * @author Bean
 *
 */
public class RAF_wr_type {
	public static void main(String[] args) throws IOException {
		RandomAccessFile raf=new RandomAccessFile("raf.dat","rw");
		/*
		 * long getFilePointer()
		 * 获取文件指针的位置
		 */
		long pos=raf.getFilePointer();
		System.out.println("pos："+pos);
		int imax=Integer.MAX_VALUE;
		/*
		 * int最大值的2进制形式：
		 * 01111111 11111111 111111111 11111111
		 */
		raf.write(imax>>>24);
		raf.write(imax>>>16);
		raf.write(imax>>>8);
		raf.write(imax);
		/*
		 * 一次性写出4个字节，将给定的int值写出
		 */
		raf.writeInt(imax);
		raf.seek(0);
		System.out.println(raf.readInt());
		System.out.println(raf.readInt());
		System.out.println(raf.read());
		raf.close();
	}
}
