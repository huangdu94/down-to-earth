package file_stream.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 使用RAF读取字符串
 *
 * @author Bean
 */
public class RAF_read {
    /*
     * 将raf.txt文件中的内容读取出来
     */
    public static void main(String[] args) throws IOException {
        RandomAccessFile raf = new RandomAccessFile("raf.txt", "r");
        byte[] data = new byte[150];
        int len = raf.read(data);
        /*
         * String(byte[] data)
         * 将给定的字节数组中所有字节按照系统默认的
         * 字符集转换为对应的字符串
         *
         * String(byte[] data,int offset,int len)
         * 将给定的字节数组中从下标为offset处开始的连续
         * len个字节按照系统默认的字符集转换为对应的字符串
         */
        String str = new String(data, 0, len, "utf-8");
        System.out.println(str);
        raf.close();
    }
}
