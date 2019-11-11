package file_stream.randomaccessfile;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 提高每次读写的数据量减少读写次数可以提高读写效率
 * @author Bean
 *
 */
public class CopyDemo2 {
	public static void main(String[] args) throws IOException {
		long begin=System.currentTimeMillis();
		RandomAccessFile src=new RandomAccessFile("PPT.ppt","r");
		RandomAccessFile desc=new RandomAccessFile("PPT_CP2.ppt","rw");
		/*
		 * RAF提供了批量读写字节的方法：
		 * int read(byte[] data)
		 * 一次性读取给定字节数组总长度的字节量
		 * 并将读到的字节存入到该数组中，返回值
		 * 为实际读取到的字节量，若返回值为-1表
		 * 示本次没有读取到任何字节（文件末尾读取）
		 */
		byte[] data=new byte[1024*10];//10k
		int len=-1;//每次实际读取到的字节数
		while((len=src.read(data))!=-1){
			/*
			 * write(byte[] data)
			 * 一次性将给定字节数数组中的所有字节写出
			 * 
			 * write(byte[] data,int off,int len)
			 * 可以保证最后一次读写不会产生垃圾数据
			 */
			desc.write(data,0,len);
		}
		src.close();
		desc.close();
		long end=System.currentTimeMillis();
		System.out.println("复制完毕!耗费时间："+(end-begin)/1000.0+"秒");
	}
}
