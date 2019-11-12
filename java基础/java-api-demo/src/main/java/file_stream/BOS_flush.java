package file_stream;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 缓冲输出流的缓冲区问题
 * @author Bean
 *
 */
public class BOS_flush {
	public static void main(String[] args) throws IOException {
		FileOutputStream fos=new FileOutputStream("text.txt");
		BufferedOutputStream bos=new BufferedOutputStream(fos);
		
		String str="你在南方的艳阳里大雪纷飞.";
		byte[] data=str.getBytes(StandardCharsets.UTF_8);
		bos.write(data);
		/*
		 * void flush()
		 * 将缓冲区已经缓存的数据一次性写出.
		 * 频繁的调用flush会降低写出效率，但是可以
		 * 保证写出数据的即使性.
		 */
		bos.flush();
		bos.close();
		System.out.println("写出完毕!");
	}
}
