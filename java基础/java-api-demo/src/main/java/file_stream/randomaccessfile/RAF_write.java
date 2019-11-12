package file_stream.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 使用RandomAccessFile写字符串
 *
 * @author Bean
 */
public class RAF_write {
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("raf.txt", "rw");
        String str = "简单点，说话的方式简单点，递进的情绪请省略，你又不是个演员，别设计那些情节。";
        byte[] data = str.getBytes("utf-8");
        raf.write(data);
        System.out.println("写出完毕");
        raf.close();
    }
}
