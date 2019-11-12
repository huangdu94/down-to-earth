package file_stream;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * java.io.BufferedReader
 * 缓冲字符输入流.特色：可以按行读取字符串
 * 由于有缓冲，读取字符时的效率好.
 *
 * @author Bean
 */
public class BufferedReader_readLine {
    public static void main(String[] args) throws IOException {
        /*
         * /src/main/java/file_stream
         */
        InputStream fis = new FileInputStream("src" + File.separator + "main" + File.separator + "java" + File.separator + "file_stream" + File.separator + "BufferedReader_readLine.java");
        Reader isw = new InputStreamReader(fis, StandardCharsets.UTF_8);
        BufferedReader br = new BufferedReader(isw);
        /*
         * BufferedReader提供了读取一行字符串的方法：
         * String readLine()
         * 该方法会连续读取若干字符，直到读取了换行符为止，
         * 然后将换行符之间读取到的所有字符以一个字符串形
         * 式返回.需要注意，返回的字符串中不包含最后的换行
         * 符.当该方法返回null时，表示末尾（不会再读取到任何数据）
         */
        String line = null;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
        }
        br.close();
    }
}