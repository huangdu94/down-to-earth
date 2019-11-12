package file_stream;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * java.id.FileInputStream
 * 文件输入流，用于从文件中读取字节
 *
 * @author Bean
 */
public class FileInputStream_read {
    public static void main(String[] args) throws IOException {
        InputStream fis = new FileInputStream("fos.txt");
        byte[] buffer = new byte[1024];
        int len = fis.read(buffer);
        String str = new String(buffer, 0, len, "utf-8");
        System.out.println(str);

        fis.close();
    }
}
