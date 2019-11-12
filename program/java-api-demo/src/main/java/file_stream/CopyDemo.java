package file_stream;

import java.io.*;

/**
 * 使用文件流完成文件的复制操作
 *
 * @author Bean
 */
public class CopyDemo {
    public static void main(String[] args) throws IOException {
        InputStream fis = new FileInputStream("PPT_CP.ppt");
        OutputStream fos = new FileOutputStream("PPT_CP2.ppt");
        byte[] buffer = new byte[1024 * 10];
        int len = -1;//保存每次实际读取到的字节量
        while ((len = fis.read(buffer)) != -1) {
            fos.write(buffer, 0, len);
        }
        System.out.println("文件复制完毕!");
        fos.close();
        fis.close();
    }
}
