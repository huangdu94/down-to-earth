package file_stream.randomaccessfile;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 使用RAF复制文件
 *
 * @author Bean
 */
public class CopyDemo {
    public static void main(String[] args) throws IOException {
        long begin = System.currentTimeMillis();
        RandomAccessFile src = new RandomAccessFile("PPT.ppt", "r");
        RandomAccessFile desc = new RandomAccessFile("PPT_CP.ppt", "rw");
        int d = -1;
        while ((d = src.read()) != -1) {
            desc.write(d);
        }
        src.close();
        desc.close();
        long end = System.currentTimeMillis();
        System.out.println("复制完毕!耗费时间：" + (end - begin) / 1000.0 + "秒");
    }
}
